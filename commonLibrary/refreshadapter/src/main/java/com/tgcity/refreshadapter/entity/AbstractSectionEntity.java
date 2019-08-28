package com.tgcity.refreshadapter.entity;

import java.io.Serializable;

/**
 * @author TGCity
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public abstract class AbstractSectionEntity<T> implements Serializable {
    public boolean isHeader;
    public T t;
    public String header;

    public AbstractSectionEntity(boolean isHeader, String header) {
        this.isHeader = isHeader;
        this.header = header;
        this.t = null;
    }

    public AbstractSectionEntity(T t) {
        this.isHeader = false;
        this.header = null;
        this.t = t;
    }
}
