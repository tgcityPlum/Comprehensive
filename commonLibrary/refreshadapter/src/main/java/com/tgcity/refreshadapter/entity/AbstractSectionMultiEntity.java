package com.tgcity.refreshadapter.entity;

import java.io.Serializable;

/**
 * @author TGCity
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public abstract class AbstractSectionMultiEntity<T> implements Serializable, MultiItemEntity {

    public boolean isHeader;
    public T t;
    public String header;

    public AbstractSectionMultiEntity(boolean isHeader, String header) {
        this.isHeader = isHeader;
        this.header = header;
        this.t = null;
    }

    public AbstractSectionMultiEntity(T t) {
        this.isHeader = false;
        this.header = null;
        this.t = t;
    }
}
