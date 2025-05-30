/*
 * Copyright 2025 Taskflow, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.feiliu.taskflow.client;

import cn.feiliu.common.api.crypto.CryptoUtil;
import cn.feiliu.common.api.crypto.RSAUtil;
import cn.feiliu.common.api.model.resp.DataResult;
import cn.feiliu.taskflow.client.dto.ApiException;
import cn.feiliu.taskflow.client.dto.AuthResponse;
import cn.feiliu.taskflow.client.dto.FeiliuRouteRequest;
import cn.feiliu.taskflow.client.dto.FeiliuRouteResult;
import cn.feiliu.taskflow.client.utils.TaskflowDataResult;
import cn.feiliu.taskflow.common.encoder.EncoderFactory;
import cn.feiliu.taskflow.common.encoder.JsonEncoder;
import lombok.SneakyThrows;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author kevin.luan
 * @since 2025-05-22
 */
public class TaskflowClient {
    static final Logger        logger      = LoggerFactory.getLogger(TaskflowClient.class);
    private final String       basePath;
    private String             keyId;
    private String             keySecret;
    /*开发者私钥*/
    private String             devPrivateKey;
    private String             devPublicKey;
    /*平台公钥*/
    private String             platformPublicKey;
    private final OkHttpClient client      = new OkHttpClient();
    private final JsonEncoder  jsonEncoder = EncoderFactory.getJsonEncoder();

    public TaskflowClient(String basePath, String keyId, String keySecret, String devPrivateKey, String devPublicKey,
                          String platformPublicKey) {
        this.basePath = basePath;
        this.keyId = keyId;
        this.keySecret = keySecret;
        this.devPrivateKey = devPrivateKey;
        this.devPublicKey = devPublicKey;
        this.platformPublicKey = platformPublicKey;
    }

    private String buildPath(String path) {
        if (basePath.endsWith("/")) {
            return basePath + path;
        } else {
            return basePath + "/" + path;
        }
    }

    public String getKeySecret() {
        return keySecret;
    }

    private final AtomicReference<AuthResponse> authResponse = new AtomicReference<>();

    public AuthResponse getToken() throws IOException {
        if (authResponse.get() == null) {
            synchronized (this) {
                if (authResponse.get() == null) {
                    try {
                        String token = Base64.getEncoder().encodeToString((keyId + ":" + keySecret).getBytes());
                        Request request = new Request.Builder().url(buildPath("auth/token"))
                            .addHeader("Authorization", "Basic " + token).addHeader("Accept", "application/json")
                            .addHeader("content-type", "application/json").build();
                        ResponseBody responseBody = client.newCall(request).execute().body();
                        String json = responseBody.string();
                        Type type = TaskflowDataResult.makeType(AuthResponse.class);
                        DataResult<AuthResponse> dataResult = EncoderFactory.getJsonEncoder().decode(json, type);
                        if (dataResult.getCode() == 200) {
                            authResponse.set(dataResult.getData());
                        } else {
                            throw new ApiException(dataResult.getCode(), dataResult.getMsg());
                        }

                    } catch (ApiException e) {
                        throw e;
                    } catch (Exception e) {
                        throw new ApiException("获取 token 出错", e);
                    }
                }
            }
        }
        return Objects.requireNonNull(authResponse.get(), "获取 token 失败");
    }

    @SneakyThrows
    public String encryptData(String reqData) {
        byte[] cipherText = CryptoUtil.encrypt(reqData.getBytes(StandardCharsets.UTF_8), keySecret);
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public PayClient getPayClient() {
        return new PayClient(this);
    }

    public ResponseBody post(String path, Object data) throws IOException, ApiException {
        path = buildPath(path);
        String token = getToken().getAccessToken();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonEncoder.encode(data));
        Request request = new Request.Builder().url(path)//
            .method("POST", body).addHeader("Authorization", "Bearer " + token)//
            .addHeader("Accept", "application/json")//
            .addHeader("content-type", "application/json").build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new ApiException(response.code(), response.message());
        }

    }

    /**
     * 创建飞流请求对象
     *
     * @param req
     * @return
     */
    @SneakyThrows
    public FeiliuRouteRequest createRequest(Object req) {
        String jsonData = jsonEncoder.encode(req);
        String cipherData = CryptoUtil.encryptAsBase64(jsonData, keySecret);
        Long timestamp = System.currentTimeMillis();
        FeiliuRouteRequest request = new FeiliuRouteRequest(cipherData, timestamp);
        String content = request.getSignStr(keySecret);
        String sign = RSAUtil.sign(content, devPrivateKey);
        request.setSign(sign);
        return request;
    }

    public void verifySign(FeiliuRouteResult resp) {
        try {
            RSAUtil.verify(resp.getSignStr(keySecret), resp.getSign(), platformPublicKey);
        } catch (Exception e) {
            logger.info("verify sign fail, data:{}", resp.getSignStr(keySecret));
            throw new ApiException("验证签名失败");
        }
    }

    public String getDecryptData(FeiliuRouteResult resp) {
        try {
            return CryptoUtil.decryptToString(resp.getData(), keySecret);
        } catch (Exception e) {
            logger.info("get decrypt data fail, data:{}", resp.getData());
            throw new ApiException("数据解密失败");
        }
    }

    public <T> T execute(String method, FeiliuRouteRequest request, Type respType) throws IOException {
        ResponseBody body = post("route/v1.0/" + method, request);
        Type type = TaskflowDataResult.makeType(FeiliuRouteResult.class);
        String rawJson = body.string();
        DataResult<FeiliuRouteResult> dataResult = jsonEncoder.decode(rawJson, type);
        if (dataResult.hasError()) {
            throw new ApiException(dataResult.getCode(), dataResult.getMsg());
        }
        verifySign(dataResult.getData());
        String plaintext = getDecryptData(dataResult.getData());
        return jsonEncoder.decode(plaintext, respType);
    }
}
