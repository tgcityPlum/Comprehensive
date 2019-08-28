package com.tgcity.base.network.bean.response;

/**
 * @author TGCity
 */

public class PictureDto {

    private int FileId;
    private String FileUrl;

    public int getFileId() {
        return FileId;
    }

    public void setFileId(int FileId) {
        this.FileId = FileId;
    }

    public String getFileUrl() {
        return FileUrl;
    }

    public void setFileUrl(String FileUrl) {
        this.FileUrl = FileUrl;
    }

    @Override
    public String toString() {
        return "PictureDto{" +
                "FileId=" + FileId +
                ", FileUrl='" + FileUrl + '\'' +
                '}';
    }
}
