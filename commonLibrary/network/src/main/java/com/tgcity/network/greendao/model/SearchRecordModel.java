package com.tgcity.network.greendao.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;

/**
 * @author TGCity
 */
@Entity
public class SearchRecordModel {
    /**
     * 主键
     */
    @Id(autoincrement = true)
    private Long id;
    private int type;
    private String body;

    @Keep
    public SearchRecordModel() {
    }

    @Keep
    public SearchRecordModel(Long id, int type, String body) {
        this.id = id;
        this.type = type;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
