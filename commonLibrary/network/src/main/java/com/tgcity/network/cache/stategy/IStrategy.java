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

import java.lang.reflect.Type;

import io.reactivex.Observable;


/**
 * @author TGCity
 * 实现缓存策略的接口，可以自定义缓存实现方式，只要实现该接口就可以了
 */
public interface IStrategy {

    /**
     * 执行缓存
     *
     * @param rxCache   缓存管理对象
     * @param apiName  api名称
     * @param requestData  请求数据
     * @param cacheTime 缓存时间
     * @param source    网络请求对象
     * @param type     要转换的目标对象
     * @return 返回带缓存的Observable流对象
     */
    <T> Observable<CacheResult<T>> execute(RxCache rxCache, String apiName, String requestData, long cacheTime, Observable<T> source, Type type);

}
