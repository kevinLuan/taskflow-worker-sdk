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
package cn.feiliu.taskflow.client.dto.alipay;

import cn.feiliu.taskflow.client.enums.AlipayCertType;
import cn.feiliu.taskflow.client.enums.AlipayIdentityType;
import lombok.Data;

/**
 * 支付宝转账任务
 *
 * @author kevin.luan
 * @since 2025-05-20
 */
@Data
public class AlipayTransferTask {
    /*自定义业务单号（全局唯一）*/
    private String             bizNo;
    /*转账金额(单位:分)*/
    private long               transAmount;
    /*期望转账时间*/
    private Long               transferTime;
    /*支付转账订单标题*/
    private String             orderTitle;
    /*证件类型*/
    private AlipayCertType     certType;
    /*证件号码*/
    private String             certNo;
    /*身份类型*/
    private AlipayIdentityType identityType;
    /*身份*/
    private String             identity;
    /*真实姓名*/
    private String             realName;
    /*备注*/
    private String             remark;
}
