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
import com.tgcity.base.network.cache.model.ErrorMode;
import com.tgcity.network.greendao.helper.HttpKeyOperationHelper;
import com.tgcity.base.network.retrofit.ApiException;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


/**
 * @author TGCity
 * 网络加载，不缓存
 * 如果次数超出规定限制 直接报错
 */
public class NoStrategy implements IStrategy {

    @Override
    public <T> Observable<CacheResult<T>> execute(RxCache rxCache, final String apiName, final String requestData, long cacheTime, Observable<T> source, Type type) {

        if (HttpKeyOperationHelper.getInstance().Effective(apiName, requestData)) {

            return source.error(new ApiException(ErrorMode.OVERLOAD));

        } else {
            return source.map(new Function<T, CacheResult<T>>() {

                @Override
                public CacheResult<T> apply(T t) throws Exception {
                    return new CacheResult<T>(false,apiName,requestData, t);
                }
            });
        }
    }
}
