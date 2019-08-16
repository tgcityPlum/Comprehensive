package com.tgcity.common.bean.response;


import java.util.List;

public class PagedListResultDto<T> {

    private int totalCount;
    private List<T> items;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
