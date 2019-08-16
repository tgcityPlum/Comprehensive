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

import com.tgcity.common.bean.response.result.HttpResult;

/**
 * 作者：TGCity by Administrator on 2018/7/23
 *
 * 提供回调代理
 * 主要用于可以自定义ApiResult
 */
public abstract class CallBackProxy<T extends HttpResult<R>, R> extends CallBackPrototypeProxy {
    CallBack<R> mCallBack;

    public CallBackProxy(CallBack<R> callBack) {
        super(callBack);
        mCallBack = callBack;
    }

    public CallBack getCallBack() {
        return mCallBack;
    }
}
