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

package com.tgcity.network.subsciber;

import android.content.Context;
import android.support.annotation.NonNull;

import com.tgcity.network.retrofit.ApiException;

import java.lang.ref.WeakReference;

import io.reactivex.observers.DisposableObserver;

import static com.tgcity.network.utils.CallBackUtils.isNetworkAvailable;

/**
 * 作者：TGCity by Administrator on 2018/7/23
 * 订阅的基类
 * 1.可以防止内存泄露。
 * 2.在onStart()没有网络时直接onCompleted();
 * 3.统一处理了异常
 */
public abstract class BaseSubscriber<T> extends DisposableObserver<T> {

    public WeakReference<Context> contextWeakReference;
    
    public BaseSubscriber() {
    }

    @Override
    public void onStart() {
        if (contextWeakReference != null && contextWeakReference.get() != null && !isNetworkAvailable(contextWeakReference.get())) {
            //Toast.makeText(context, "无网络，读取缓存数据", Toast.LENGTH_SHORT).show();
            onComplete();
        }
    }


    public BaseSubscriber(Context context) {
        if (context != null) {
            contextWeakReference = new WeakReference<>(context);
        }
    }

    @Override
    public void onNext(@NonNull T t) {

    }

    @Override
    public final void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof ApiException) {
            onError((ApiException) e);
        } else {
            onError(ApiException.handleException(e));
        }
    }

    @Override
    public void onComplete() {

    }


    public abstract void onError(ApiException e);

}
