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

package com.tgcity.network.cache.converter;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;

/**
 * @author TGCity
 * 通用转换器接口
 * 1.实现该接口可以实现一大波的磁盘存储操作。
 * 2.可以实现Serializable、Gson,Parcelable、fastjson、xml、kryo等等
 * 目前只实现了：GsonDiskConverter和SerializableDiskConverter转换器，
 */
public interface IDiskConverter {

    /**
     * 读取
     *
     * @param source 输入流
     * @param type  读取数据后要转换的数据类型
     *               这里没有用泛型T或者Tyepe来做，是因为本框架决定的一些问题，泛型会丢失
     * @return T
     */
    <T> T load(InputStream source, Type type);

    /**
     * 写入
     *
     * @param sink OutputStream
     * @param data 保存的数据
     * @return boolean
     */
    boolean writer(OutputStream sink, Object data);

}
