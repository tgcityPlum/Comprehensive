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

import com.tgcity.network.cache.RxCache;
import com.tgcity.network.cache.model.CacheResult;
import com.tgcity.network.greendao.helper.HttpKeyOperationHelper;

import java.lang.reflect.Type;

import io.reactivex.Observable;


/**
 * @author TGCity
 * 先显示缓存，缓存不存在，再请求网络
 * 如果次数超出规定限制 直接读取缓存
 */
final public class FirstCacheStrategy extends BaseStrategy {

    @Override
    public <T> Observable<CacheResult<T>> execute(RxCache rxCache, String apiName, String requestData, long time, Observable<T> source, Type type) {

        //如果次数超出限制 直接读取缓存
        if (HttpKeyOperationHelper.getInstance().Effective(apiName, requestData)) {
            return loadCache(rxCache, type, apiName, requestData, time, false);

        } else {
            Observable<CacheResult<T>> cache = loadCache(rxCache, type, apiName, requestData, time, true);
            Observable<CacheResult<T>> remote = loadRemote(rxCache, apiName, requestData, source, false);
            return cache.switchIfEmpty(remote);
        }
    }
}
