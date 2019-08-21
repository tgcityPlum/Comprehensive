package com.tgcity.network.greendao.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;

/**
 * 院校详情_点击院校对比弹出的页面列表模型
 * Created by Administrator on 2018/11/20.
 */
@Entity
public class CollegeDetailsCollegeContrastSchoolDto {
    @Id(autoincrement = true)
    private Long id;//主键
    private String schoolName;//院校名称
    private int schoolId;//院校ID
    private String ranking;//排名
    private String schoolProvince;//院校归属地
    private long time;//时间
    private int isCheck;//是否被选中

    @Keep
    public CollegeDetailsCollegeContrastSchoolDto(Long id, String schoolName, int schoolId, String ranking, String schoolProvince, long time, int isCheck) {
        this.id = id;
        this.schoolName = schoolName;
        this.schoolId = schoolId;
        this.ranking = ranking;
        this.schoolProvince = schoolProvince;
        this.time = time;
        this.isCheck = isCheck;
    }
    @Keep
    public CollegeDetailsCollegeContrastSchoolDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getSchoolProvince() {
        return schoolProvince;
    }

    public void setSchoolProvince(String schoolProvince) {
        this.schoolProvince = schoolProvince;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int isCheck() {
        return isCheck;
    }

    public void setCheck(int check) {
        isCheck = check;
    }
    public int getIsCheck() {
        return this.isCheck;
    }
    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
    }

    @Override
    public String toString() {
        return "CollegeDetailsCollegeContrastSchoolDto{" +
                "id=" + id +
                ", schoolName='" + schoolName + '\'' +
                ", schoolId=" + schoolId +
                ", ranking='" + ranking + '\'' +
                ", schoolProvince='" + schoolProvince + '\'' +
                ", time=" + time +
                ", isCheck=" + isCheck +
                '}';
    }
}
