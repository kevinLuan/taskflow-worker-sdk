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
package cn.feiliu.taskflow.worker;

import cn.feiliu.taskflow.worker.dto.ApiException;
import cn.feiliu.taskflow.worker.dto.req.YjyTransferTaskReq;
import cn.feiliu.taskflow.worker.dto.resp.YjyTransferResp;
import org.junit.Test;

import java.util.UUID;

/**
 * @author kevin.luan
 * @since 2025-05-22
 */
public class YjyClientTests {
    /*开发自由的公钥*/
    static String         devPublicKey      = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCFjUJkN0dr1O4CnrQtnAkabOoG0wCh4NT7qwYBUUxsPQIk9RGA+kHiePnVIg79JbvTor6qSU3aW19jpMgG/dLK+4LLMDB/O1VtgtyGqI3qAOkkfI3nsL5DGPml0ZSeBLmGhINqdo8Hme+FlQF5QXxtE6Ls4sNCeW1DQ/Gu0oVcFQIDAQAB";
    /*开发者自有的私钥*/
    static String         devPrivateKey     = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIWNQmQ3R2vU7gKetC2cCRps6gbTAKHg1PurBgFRTGw9AiT1EYD6QeJ4+dUiDv0lu9OivqpJTdpbX2OkyAb90sr7gsswMH87VW2C3IaojeoA6SR8jeewvkMY+aXRlJ4EuYaEg2p2jweZ74WVAXlBfG0Touziw0J5bUND8a7ShVwVAgMBAAECgYBFxLcDXbGNDq7MZZZhIVvMF0vnBsIUzzzUwNKhS5RuEy0Um4ZqZ9KVenehtWo5K0ZFWIbctPDb+tIJXyM0K9O7f0WLwYxcMflx910LRsXOyRJqZ2s959Aup2r7A0FyrK0wYAGsjIWVOUNzNdmWyYlJLRAve62avtI+vozRkyAkQQJBAMQEbBWVwyrO8cnK6wLfR8hWtE7iKor1OkN77ma1N71aLz0na4tcyox92xyfWDrvk4jUsOR/dOD5xHfqHNeBToUCQQCua2yApWoDhHy/rbqia+Mf8LkocmaLkq+iAz4iUSrCNdtGHiWepnW9L2nfJxdN3jhE9wqTqv54rDilhljFrrRRAkEAkusBnBgZJV/g6C/Yf9mc8vLRyIJKAscVOXmNUZwoQBt1Zq/CMr2E71JxxX1FYz8oZJmOUGWb0/hBIuPtZDV+BQJAGjelgJTcoZi5k6pOgnmLE1MA0faYOWScJ9Mq9MeUkMq9ogWIDBfjXMOSu2tJ0neTp7ImkdnDFGp2vVK7Z0OCcQI/F+nAbf5swfafEhBfJRr34/N1Q70k/e/av/VX/AVbPRI/MiY7+GdVabdzvzPlFyf9ev1iwThp5erhAxkgNoMd";
    /*平台公钥*/
    static String         platformPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCVbSRBzLDaaf/EqIHLYz/+X+mprzHBNuptkfQ60uIc603gKNpvVjHW+dkW/YgQ9ud9nzL4tCFrnh5Py3wGKdiv5LHTxI+hHAj4doPnoze/nqaxLMPpFVoDhQQc5px+0yXzNm0v2hef0m1zhS+9h/TdKLI8ScZg6VkKlyNa+p5MqwIDAQAB";
    static TaskflowClient taskflowClient    = new TaskflowClient("http://localhost:8083/api", "197300d502f",
                                                "0c0fb021b6f04bbeaae4d3d414423b4a", devPrivateKey, platformPublicKey);

    @Test
    public void transferTask() throws ApiException {
        YjyTransferTaskReq request = new YjyTransferTaskReq();
        request.setWorkflowName("lg_transfer_workflow");
        request.setWorkflowVersion(1);
        request.setBizNo("order-" + UUID.randomUUID().toString());
        request.setBankType(1);
        request.setPayMethod(1);
        YjyTransferTaskReq.TransferData task = new YjyTransferTaskReq.TransferData();
        task.setUsername("吴2阳");
        task.setAccountNo("62234258676607631");
        task.setIdCard("111429196605291810");
        task.setPhone("13188711521");
        task.setAmount("1.00");
        task.setRemark("给他钱");
        task.setPayAccountType(1);
        task.setRemark("20250516代发");
        request.setData(task);
        YjyTransferResp resp = taskflowClient.getPayClient().yjyTransfer(request);
        System.out.println("交易流水号:" + resp.getTradeId());
    }
}
