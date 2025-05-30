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

import cn.feiliu.taskflow.worker.dto.*;
import cn.feiliu.taskflow.worker.dto.alipay.AlipayTransferResp;
import cn.feiliu.taskflow.worker.dto.alipay.TransferTaskRequest;
import cn.feiliu.taskflow.worker.dto.sby.SbyTransferResp;
import cn.feiliu.taskflow.worker.dto.sby.SbyTransferTaskReq;

import java.io.IOException;

/**
 * 支付客户端
 *
 * @author kevin.luan
 * @since 2025-05-22
 */
public class PayClient {
    private final TaskflowClient client;

    public PayClient(TaskflowClient client) {
        this.client = client;
    }

    /**
     * 支付宝转账操作
     */
    public AlipayTransferResp alipayTransfer(TransferTaskRequest req) throws ApiException {
        try {
            TaskflowBaseRequest request = client.createRequest(req);
            return client.execute("alipay.transfer", request, AlipayTransferResp.class);
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
            TaskflowBaseRequest request = client.createRequest(req);
            return client.execute("sby.transfer", request, SbyTransferResp.class);
        } catch (IOException e) {
            throw new ApiException(-1, e.getMessage());
        }
    }
}
