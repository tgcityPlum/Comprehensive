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

import com.tgcity.network.bean.result.HttpResultTZY;

/**
 * 作者：TGCity by Administrator on 2018/7/23
 *
 * 提供回调代理
 * 主要用于可以自定义ApiResult
 /**
 * 因为#{@link com.eagersoft.youzy.youzy.constants.AppConstant.TZY_URL}地址下的接口返回参数是由isSuccess来判断，
 * 所以新增一套回调代理，与原来的互不影响，区分的时候只需要在
 * #{@link BaseHttpData.Builder.extraRemark}参数填入
 * #{@link com.eagersoft.youzy.youzy.constants.AppConstant.API_SERVICE_TZY}即可
 */
@SuppressWarnings("ALL")
public abstract class CallBackProxyTZY<T extends HttpResultTZY<R>, R> extends CallBackPrototypeProxy {
    CallBack<R> mCallBack;

    public CallBackProxyTZY(CallBack<R> callBack) {
        super(callBack);
        mCallBack = callBack;
    }

    public CallBack getCallBack() {
        return mCallBack;
    }
}
