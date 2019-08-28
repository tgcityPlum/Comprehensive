/*
 * Copyright (C) 2017 zhouyou(478319399@qq.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tgcity.network.callback;

import com.google.gson.internal.$Gson$Types;
import com.tgcity.network.cache.model.CacheResult;
import com.tgcity.network.utils.CallBackUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * @author TGCity
 * 回调代理
 */
public abstract class AbstractCallBackPrototypeProxy<T extends R, R> implements IType<T> {

    AbstractCallBack<R> mCallBack;

    public AbstractCallBackPrototypeProxy(AbstractCallBack<R> callBack) {
        mCallBack = callBack;
    }

    public AbstractCallBack getCallBack() {
        return mCallBack;
    }

    /**
     * CallBack代理方式，获取需要解析的Type
     * @return Type
     */
    @Override
    public Type getType() {
        Type typeArguments = null;
        if (mCallBack != null) {
            //如果用户的信息是返回List需单独处理
            Type rawType = mCallBack.getRawType();
            if (List.class.isAssignableFrom(CallBackUtils.getClass(rawType, 0)) || Map.class.isAssignableFrom(CallBackUtils.getClass(rawType, 0))) {
                typeArguments = mCallBack.getType();
            } else if (CacheResult.class.isAssignableFrom(CallBackUtils.getClass(rawType, 0))) {
                Type type = mCallBack.getType();
                typeArguments = CallBackUtils.getParameterizedType(type, 0);
            } else {
                Type type = mCallBack.getType();
                typeArguments = CallBackUtils.getClass(type, 0);
            }
        }
        if (typeArguments == null) {
            typeArguments = ResponseBody.class;
        }
        Type rawType = CallBackUtils.findNeedType(getClass());
        if (rawType instanceof ParameterizedType) {
            rawType = ((ParameterizedType) rawType).getRawType();
        }
        return $Gson$Types.newParameterizedTypeWithOwner(null, rawType, typeArguments);
    }
}
