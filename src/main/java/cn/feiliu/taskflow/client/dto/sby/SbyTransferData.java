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
package cn.feiliu.taskflow.client.dto.sby;

import lombok.Data;

/**
 * 转账数据
 *
 * @author kevin.luan
 * @since 2025-05-28
 */
@Data
public class SbyTransferData {
    /*收款人姓名*/
    private String  payeeName;
    /*收款人手机号*/
    private String  mobile;
    /*转账金额（单位：分）*/
    private Long    transAmount;
    /*身份证号*/
    private String  idCard;
    /*分款方式 0：银行卡，1：支付宝，2：微信*/
    private Integer paymentType;
    /*收款人账号(根据付款方式:银行卡号/支付宝(账号、ID)/微信 openid)*/
    private String  payeeAcc;
    /*用户备注*/
    private String  remark;
}
