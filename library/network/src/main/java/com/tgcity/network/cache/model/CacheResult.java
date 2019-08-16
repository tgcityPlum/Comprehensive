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


import java.io.Serializable;

/**
 * 作者：TGCity by Administrator on 2018/7/23
 * 缓存对象
 * 暂不支持拓展
 * 如需修改拓展
 * 请先与我确认
 */
public class CacheResult<T> implements Serializable {

    //是否使用的缓存
    public boolean isFromCache;

    //接口名称
    public String apiName;

    //请求数据
    public String requestData;

    public T data;

    public CacheResult() {
    }

    public CacheResult(boolean isFromCache) {
        this.isFromCache = isFromCache;
    }

    public CacheResult(boolean isFromCache, String apiName, String requestData, T data) {
        this.isFromCache = isFromCache;
        this.apiName=apiName;
        this.requestData=requestData;
        this.data = data;
    }

    public boolean isCache() {
        return isFromCache;
    }

    public void setCache(boolean cache) {
        isFromCache = cache;
    }

    @Override
    public String toString() {
        return "CacheResult{" +
                "isFromCache=" + isFromCache +
                ", apiName='" + apiName + '\'' +
                ", requestData='" + requestData + '\'' +
                ", data=" + data +
                '}';
    }
}
