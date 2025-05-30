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

import cn.feiliu.taskflow.client.dto.ApiException;
import cn.feiliu.taskflow.client.dto.sby.SbyTransferData;
import cn.feiliu.taskflow.client.dto.sby.SbyTransferTaskReq;
import cn.feiliu.taskflow.client.dto.alipay.TransferTaskRequest;
import cn.feiliu.taskflow.common.encoder.EncoderFactory;
import cn.feiliu.taskflow.common.encoder.JsonEncoder;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author kevin.luan
 * @since 2025-05-22
 */
public class LocalClient {
    /*开发自由的公钥*/
    static String         devPublicKey      = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCFjUJkN0dr1O4CnrQtnAkabOoG0wCh4NT7qwYBUUxsPQIk9RGA+kHiePnVIg79JbvTor6qSU3aW19jpMgG/dLK+4LLMDB/O1VtgtyGqI3qAOkkfI3nsL5DGPml0ZSeBLmGhINqdo8Hme+FlQF5QXxtE6Ls4sNCeW1DQ/Gu0oVcFQIDAQAB";
    /*开发者自有的私钥*/
    static String         devPrivateKey     = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIWNQmQ3R2vU7gKetC2cCRps6gbTAKHg1PurBgFRTGw9AiT1EYD6QeJ4+dUiDv0lu9OivqpJTdpbX2OkyAb90sr7gsswMH87VW2C3IaojeoA6SR8jeewvkMY+aXRlJ4EuYaEg2p2jweZ74WVAXlBfG0Touziw0J5bUND8a7ShVwVAgMBAAECgYBFxLcDXbGNDq7MZZZhIVvMF0vnBsIUzzzUwNKhS5RuEy0Um4ZqZ9KVenehtWo5K0ZFWIbctPDb+tIJXyM0K9O7f0WLwYxcMflx910LRsXOyRJqZ2s959Aup2r7A0FyrK0wYAGsjIWVOUNzNdmWyYlJLRAve62avtI+vozRkyAkQQJBAMQEbBWVwyrO8cnK6wLfR8hWtE7iKor1OkN77ma1N71aLz0na4tcyox92xyfWDrvk4jUsOR/dOD5xHfqHNeBToUCQQCua2yApWoDhHy/rbqia+Mf8LkocmaLkq+iAz4iUSrCNdtGHiWepnW9L2nfJxdN3jhE9wqTqv54rDilhljFrrRRAkEAkusBnBgZJV/g6C/Yf9mc8vLRyIJKAscVOXmNUZwoQBt1Zq/CMr2E71JxxX1FYz8oZJmOUGWb0/hBIuPtZDV+BQJAGjelgJTcoZi5k6pOgnmLE1MA0faYOWScJ9Mq9MeUkMq9ogWIDBfjXMOSu2tJ0neTp7ImkdnDFGp2vVK7Z0OCcQI/F+nAbf5swfafEhBfJRr34/N1Q70k/e/av/VX/AVbPRI/MiY7+GdVabdzvzPlFyf9ev1iwThp5erhAxkgNoMd";
    /*平台公钥*/
    static String         platformPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDJK2UKjAwKi9LcC5bmyL/Noehyq7rrXUqhH67icikhCTCqKu1OogC9qXbm03W1f7RYxaR+/Enu8Hat3zQfBKSeQtQspWz4iTHUuxDEPQV55chbwpWhABNdlkvyljlbRtYmvzG9duNQQOJaexthC1aLzc8shjrujjvyLVZ1LACmHwIDAQAB";
    static TaskflowClient taskflowClient    = new TaskflowClient("http://localhost:8083/api/", "196d77fc138",
                                                "7ea2affbfe384cd7a8dd841a02f011ff", devPrivateKey, devPublicKey,
                                                platformPublicKey);

    public static void alipayTransferTask() throws ApiException {
        JsonEncoder jsonEncoder = EncoderFactory.getJsonEncoder();
        TransferTaskRequest req = jsonEncoder.convert(
            "{\n" + "            \"appName\": \"alipay\",\n"
                    + "                \"workflowName\": \"alipay_workflow\",\n"
                    + "                \"workflowVersion\": 1,\n" + "                \"data\": {\n"
                    + "            \"certType\":\"IDENTITY_CARD\",\n" + "                    \"bizNo\": \"103\",\n"
                    + "                    \"transAmount\": 1,\n" + "                    \"transferTime\": \"100\",\n"
                    + "                    \"orderTitle\": \"测试转账\",\n"
                    + "                    \"certNo\": \"053070194504089221\",\n"
                    + "                    \"realName\": \"cayjgd1666\",\n"
                    + "                    \"identityType\":\"ALIPAY_LOGON_ID\",\n"
                    + "                    \"identity\": \"cayjgd1666@sandbox.com\",\n"
                    + "                    \"remark\": \"20250516代发\"\n" + "        }\n" + "        }",
            TransferTaskRequest.class);
        try {
            System.out.println("交易流水号:" + taskflowClient.getPayClient().alipayTransfer(req));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        SbyTransferTaskReq req = new SbyTransferTaskReq();
        req.setAppName("sby_test");
        req.setWorkflowName("sby_transfer_workflow");
        req.setWorkflowVersion(1);
        req.setBizNo("order-" + System.currentTimeMillis());
        SbyTransferData data = new SbyTransferData();
        data.setPayeeName("余海霞");
        data.setMobile("18914346422");
        data.setIdCard("11011619940303150X");
        data.setPayeeAcc("6213905012014648566");
        data.setPaymentType(0);
        data.setTransAmount(ThreadLocalRandom.current().nextLong(1, 10000));
        data.setRemark("test_sby");
        req.setData(data);
        System.out.println(taskflowClient.getPayClient().sbyTransfer(req).getTradeId());
    }
}
