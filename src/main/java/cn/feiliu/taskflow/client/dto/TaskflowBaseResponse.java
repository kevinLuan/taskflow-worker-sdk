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
package cn.feiliu.taskflow.client.dto;

import lombok.Data;

import java.util.Objects;

/**
 * Taskflow 基础响应数据类
 */
@Data
public class TaskflowBaseResponse {
    /*请求DTO原始数据*/
    private String data;
    /*当前系统时间戳*/
    private Long   timestamp;
    /*接口参数签名*/
    private String sign;

    public String getSignStr(String secret) {
        Objects.requireNonNull(data, "data is null");
        Objects.requireNonNull(timestamp, "secret is null");
        Objects.requireNonNull(secret, "secret is null");
        return data + "|" + timestamp + "|" + secret;
    }

}