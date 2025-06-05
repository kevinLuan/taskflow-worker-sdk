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
package cn.feiliu.taskflow.worker.dto.req;

import lombok.Data;

/**
 * @author kevin.luan
 * @since 2025-05-28
 */
@Data
public class YjyTransferTaskReq {
    /*工作流名称*/
    private String       workflowName;
    /*工作流版本号*/
    private Integer      workflowVersion;
    /*业务单号*/
    private String       bizNo;
    /*打款通道 1银行卡 2支付宝 3安全发 6微信*/
    private Integer      bankType;
    /*收款方式 1银行卡 2支付宝 4银行卡/支付宝 6微信*/
    private Integer      payMethod;

    /**
     * 转账数据
     */
    private TransferData data;

    @Data
    public static class TransferData {
        /*收款姓名*/
        private String  username;
        /*收款账号，如果是微信则传开户时appId所对应的openid*/
        private String  accountNo;
        /*收款身份证*/
        private String  idCard;
        /*手机号*/
        private String  phone;
        /*订单金额*/
        private String  amount;
        /*打款备注*/
        private String  remark;
        /*收款类型 1银行卡 2支付宝 6微信*/
        private Integer payAccountType;
        /*顺丰业务中用到的版本号，存入了employ_id字段*/
        private Integer frequency;
        /*微信appId,与openid对应*/
        private String  weixinAppId;
    }
}