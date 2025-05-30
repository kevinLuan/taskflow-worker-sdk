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

@Data
public class FeiliuRouteRequest {
    private String reqData;
    private Long   timestamp;
    private String sign;

    public FeiliuRouteRequest(String reqData, Long timestamp) {
        this.reqData = reqData;
        this.timestamp = timestamp;
    }

    public String getSignStr(String keySecret) {
        Objects.requireNonNull(keySecret, "keySecret is null");
        Objects.requireNonNull(reqData, "reqData is null");
        Objects.requireNonNull(timestamp, "timestamp is null");
        return reqData + "|" + timestamp + "|" + keySecret;
    }
}