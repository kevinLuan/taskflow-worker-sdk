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
package cn.feiliu.taskflow.worker.dto;

import lombok.Data;

/**
 * 授权响应数据
 *
 * @author kevin.luan
 * @since 2025-05-22
 */
@Data
public class AuthResponse {
    private String  accessToken;
    private String  type;
    /*过期时间 单位:秒*/
    private Integer expire;     // Expiration time
}
