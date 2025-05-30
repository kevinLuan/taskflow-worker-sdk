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

/**
 * @author kevin.luan
 * @since 2025-05-22
 */
public class ApiException extends RuntimeException {
    private int code;

    public ApiException(String message) {
        super(message);
        this.code = -1;
    }

    public ApiException(String message, Exception ex) {
        super(message, ex);
        this.code = -1;
    }

    public ApiException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
