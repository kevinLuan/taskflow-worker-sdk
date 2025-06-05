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

import cn.feiliu.taskflow.worker.dto.resp.SbyTransferResp;
import cn.feiliu.taskflow.worker.dto.req.SbyTransferTaskReq;

/**
 * @author kevin.luan
 * @since 2025-06-03
 */
public class WorkerTests {
    /*飞流云平台公钥*/
    static String feiliu_pub_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCtJDSk7AQEDsw9acWOmM/0ryH0ENOOpgFvkHQIDRjNN4MRY4CWxrLj1DYBiIjCedNL0Seio+l7l2ZU4nwmMfIyuOBYd8geIW/JADSZdjHMb4QlLAQZ+p8MrVI8F2x38XubyjG78CqiYcFQKqI4q6fWFnjd4PzG8E823gH3pgda+wIDAQAB";
    /*开发者私钥*/
    static String dev_private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAILtdFgGNULXtQlFWB0jREkQ850etapN07n7UGBv7Y0/v+qDSjGtgaCBkZNaKMH+GhpzIpD06CtVKCFi9HGeLE+vS7whZC6TgHmtjz5ES7JAeI/cxy8uTam1cahYZjSLhbsemOZknova7K8qj+BZDRbH6CrMGI+8XRGAL3ZupZj7AgMBAAECgYA1gAp2nbLN6Y8Uhr2Gyo5wvPiPT6k0OjE2Yjdp8NLFUTN/BL8gpaG+kLOH3uOgwdCw1jNdwHXOnzGS0vjIJLY+EBY3pzX8uF6bi7d5NhOKCQXNgQK/IbnqgFBiTGtrJ4FmJzElVxFRMeqlS9kpZHEp4kUck5kBPaJRpdWuVu8LYQJBAOVjMAMqzf1GkANbZu/PbULpRXGuXUzgXCFWmUZ1XqBJUHXwCs2fhGxBoXGm/qvDrh1hGBpl8kYDAIhfM6qvG10CQQCSHgF+VmZz7wGeFzE/8dlFJFD34o86ZUVf2IArPNFNbLHZ9FVfy0qL4hW6dDUE8N3oXgRZKa256/L9BS4I7hg3AkEA5HlSzRTJb0flETLs6Fb2QPsPjBdcHsIpgZs7t44X3han6ZBZck08HIeSQh1O885HkkOvOjvogAtlcG9CivM2kQJACBEEABeA/HF7Y2DgZSlS11BT7/tf8NdaZxoqqThoTZ/siZ3FgM5WRTasAGkd7DHn7kYSc2Grp5HxDirVCcUrLQJBANOX9cqUxArZhSSHZez0072jcZ+h0oxAUX8po1d8uUnLjBv+y98ICrG4mY0NV4cY/7KQmb6LKlTYvOaJzgcO5sg=";

    public static void main(String[] args) {
        TaskflowClient taskflowClient = new TaskflowClient("https://worker.taskflow.cn/api", "19734380827",
            "81f1d6c7a3d74207bf51229f64aa421e", dev_private_key, feiliu_pub_key);
        SbyTransferTaskReq req = new SbyTransferTaskReq();
        req.setAppName("test_sby_app");
        req.setWorkflowName("test_sby_transfer_workflow");
        req.setWorkflowVersion(1);
        req.setBizNo("order-" + System.currentTimeMillis());
        SbyTransferTaskReq.TransferData data = new SbyTransferTaskReq.TransferData();
        data.setPayeeName("余海霞");
        data.setMobile("18914346422");
        data.setIdCard("11011619940303150X");
        data.setPayeeAcc("6213905012014648566");
        data.setPaymentType(0);//支付类型:
        data.setTransAmount(1000L);//转账金额(单位：分)
        data.setRemark("test_sby");
        req.setData(data);
        SbyTransferResp resp = taskflowClient.getPayClient().sbyTransfer(req);
        System.out.println("交易流水号: " + resp.getTradeId());
    }
}
