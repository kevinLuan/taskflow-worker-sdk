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

import cn.feiliu.taskflow.client.dto.*;
import cn.feiliu.taskflow.client.dto.alipay.TransferTaskRequest;
import cn.feiliu.taskflow.client.dto.sby.SbyTransferResp;
import cn.feiliu.taskflow.client.dto.sby.SbyTransferTaskReq;
import cn.feiliu.taskflow.common.encoder.EncoderFactory;
import cn.feiliu.taskflow.common.encoder.JsonEncoder;

import java.io.IOException;
import java.util.Map;

/**
 * @author kevin.luan
 * @since 2025-05-22
 */
public class PayClient {
    private final TaskflowClient client;
    private final JsonEncoder    jsonEncoder = EncoderFactory.getJsonEncoder();

    public PayClient(TaskflowClient client) {
        this.client = client;
    }

    /**
     * 支付宝转账操作
     */
    public String alipayTransfer(TransferTaskRequest req) throws ApiException {
        try {
            FeiliuRouteRequest request = client.createRequest(req);
            Map<String, String> map = client.execute("alipay.transfer", request, Map.class);
            return map.get("tradeId");
        } catch (IOException e) {
            throw new ApiException(-1, e.getMessage());
        }
    }

    /**
     * 身边云灵活用工平台转账
     *
     * @param req
     * @return
     * @throws ApiException
     */
    public SbyTransferResp sbyTransfer(SbyTransferTaskReq req) throws ApiException {
        try {
            FeiliuRouteRequest request = client.createRequest(req);
            return client.execute("sby.transfer", request, SbyTransferResp.class);
        } catch (IOException e) {
            throw new ApiException(-1, e.getMessage());
        }
    }
}
