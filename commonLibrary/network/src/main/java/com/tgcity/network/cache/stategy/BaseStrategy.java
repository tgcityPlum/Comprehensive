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

package com.tgcity.network.cache.stategy;



import android.support.annotation.NonNull;

import com.tgcity.network.cache.RxCache;
import com.tgcity.network.cache.model.CacheResult;
import com.tgcity.base.network.cache.model.ErrorMode;
import com.tgcity.base.network.retrofit.ApiException;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * 作者：TGCity by Administrator on 2018/7/23
 * 描述：实现缓存策略的基类
 */
public abstract class BaseStrategy implements IStrategy {

    <T> Observable<CacheResult<T>> loadCache(final RxCache rxCache, Type type, final String apiName, final String requestData, final long time, final boolean needEmpty) {
        Observable<CacheResult<T>> observable = rxCache.<T>load(type, apiName+requestData, time).flatMap(new Function<T, ObservableSource<CacheResult<T>>>() {

            public ObservableSource<CacheResult<T>> apply(T t) throws Exception {
                if (t == null) {
                    return Observable.error(new ApiException(ErrorMode.NO_CACHE));
                }
                return Observable.just(new CacheResult<T>(true,apiName,requestData, t));
            }
        });
        if (needEmpty) {
            observable = observable
                    .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends CacheResult<T>>>() {
                        @Override
                        public ObservableSource<? extends CacheResult<T>> apply(Throwable throwable) throws Exception {
                            return Observable.empty();
                        }
                    });
        }
        return observable;
    }

    /*
    * 因策略修改 改方法暂时不使用  先保留 后续迭代酌情考虑
    * */
    <T> Observable<CacheResult<T>> loadRemote2(final RxCache rxCache, final String apiName, final String requestData, Observable<T> source, final boolean needEmpty) {
        Observable<CacheResult<T>> observable = source
                .map(new Function<T, CacheResult<T>>() {
                    public CacheResult<T> apply(T t) throws Exception {
                        rxCache.save(apiName+requestData, t).subscribeOn(Schedulers.io())
                                .subscribe(new Consumer<Boolean>() {
                                    @Override
                                    public void accept(@NonNull Boolean status) throws Exception {
                                    }
                                }, new Consumer<Throwable>() {
                                    @Override
                                    public void accept(@NonNull Throwable throwable) throws Exception {
                                        throwable.printStackTrace();
                                    }
                                });
                        return new CacheResult<T>(false,apiName,requestData, t);
                    }
                });
        if (needEmpty) {
            observable = observable
                    .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends CacheResult<T>>>() {
                        @Override
                        public ObservableSource<? extends CacheResult<T>> apply(Throwable throwable) throws Exception {
                            return Observable.empty();
                        }
                    });
        }
        return observable;
    }

    //请求成功后：同步保存
    <T> Observable<CacheResult<T>> loadRemote(final RxCache rxCache, final String apiName, final String requestData, Observable<T> source, final boolean needEmpty) {

        Observable<CacheResult<T>> observable = source
                .flatMap(new Function<T, ObservableSource<CacheResult<T>>>() {
                    public ObservableSource<CacheResult<T>> apply(final T t) throws Exception {
                        return  rxCache.save(apiName+requestData, t).map(new Function<Boolean, CacheResult<T>>() {
                            @Override
                            public CacheResult<T> apply(@NonNull Boolean aBoolean) throws Exception {
                                return new CacheResult<T>(false,apiName,requestData, t);
                            }
                        }).onErrorReturn(new Function<Throwable, CacheResult<T>>() {
                            @Override
                            public CacheResult<T> apply(@NonNull Throwable throwable) throws Exception {

                                return new CacheResult<T>(false,apiName,requestData, t);
                            }
                        });
                    }
                });
        if (needEmpty) {
            observable = observable
                    .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends CacheResult<T>>>() {
                        @Override
                        public ObservableSource<? extends CacheResult<T>> apply(Throwable throwable) throws Exception {
                            return Observable.empty();
                        }
                    });
        }
        return observable;
    }
}
