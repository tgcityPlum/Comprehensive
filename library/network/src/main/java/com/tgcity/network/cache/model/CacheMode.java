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

package com.tgcity.network.cache.model;

/**
 * 作者：TGCity by Administrator on 2018/7/23
 * 网络请求策略
 */
public enum CacheMode {
    /**
     * 不使用缓存,该模式下,cacheKey,cacheMaxAge 参数均无效
     **/
    NO_CACHE("NoStrategy"),
    /**
     * 先请求网络，请求网络失败后再加载缓存
     */
    FIRSTREMOTE("FirstRemoteStrategy"),

    /**
     * 先加载缓存，缓存没有再去请求网络
     */
    FIRSTCACHE("FirstCacheStategy"),

    /**
     * 先使用缓存，不管是否存在，仍然请求网络，会先把缓存回调给你，
     * 等网络请求回来发现数据是一样的就不会再返回，否则再返回
     * （这样做的目的是防止数据是一样的你也需要刷新界面）
     */
    CACHEANDREMOTEDISTINCT("CacheAndRemoteDistinctStrategy");

    private final String className;

    CacheMode(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
