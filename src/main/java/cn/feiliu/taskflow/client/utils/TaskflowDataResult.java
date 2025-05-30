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
package cn.feiliu.taskflow.client.utils;

import cn.feiliu.common.api.model.resp.DataResult;
import cn.feiliu.taskflow.client.dto.ApiException;
import com.google.inject.util.Types;

import java.lang.reflect.Type;

/**
 * @author shoushen.luan
 * @since 2022-09-02
 */
public class TaskflowDataResult<T> {
    private Integer code;
    private String  msg;
    private T       data;

    public boolean isSuccess() {
        return this.code != null && this.code == 0;
    }

    public static Type makeType(Type paramType) {
        return Types.newParameterizedType(DataResult.class, paramType);
    }

    public T getData() throws ApiException {
        if (code.intValue() != 0) {
            throw new ApiException(code, msg);
        }
        return this.data;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
