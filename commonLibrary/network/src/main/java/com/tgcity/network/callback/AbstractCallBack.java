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


import com.tgcity.network.utils.CallBackUtils;

import java.lang.reflect.Type;

/**
 * @author TGCity
 * 网络请求回调
 */
public abstract class AbstractCallBack<T> implements IType<T> {

    /**
     * start
     */
    public abstract void onStart();

    /**
     * complete
     */
    public abstract void onCompleted();

    /**
     * error
     *
     * @param e Throwable
     */
    public abstract void onError(Throwable e);

    /**
     * next
     *
     * @param t T
     */
    public abstract void onNext(T t);

    /**
     * 获取需要解析的泛型T类型
     *
     * @return Type
     */
    @Override
    public Type getType() {
        return CallBackUtils.findNeedClass(getClass());
    }

    /**
     * 获取需要解析的泛型T raw类型
     *
     * @return Type
     */
    public Type getRawType() {
        return CallBackUtils.findRawType(getClass());
    }
}
