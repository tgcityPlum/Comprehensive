package com.tgcity.resource.bean.response;

import java.util.List;

public class QueryMatchAndMismatchCollegeOutput {

    private int collegeTotal;//匹配院校总数
    private int mismatchCollegeTotal;//不匹配的院校数
    private List<CollegeBean> college;//匹配院校的
    private List<CollegeBean> mismatchCollege;//不匹配的院校

    public int getCollegeTotal() {
        return collegeTotal;
    }

    public void setCollegeTotal(int collegeTotal) {
        this.collegeTotal = collegeTotal;
    }

    public int getMismatchCollegeTotal() {
        return mismatchCollegeTotal;
    }

    public void setMismatchCollegeTotal(int mismatchCollegeTotal) {
        this.mismatchCollegeTotal = mismatchCollegeTotal;
    }

    public List<CollegeBean> getCollege() {
        return college;
    }

    public void setCollege(List<CollegeBean> college) {
        this.college = college;
    }

    public List<CollegeBean> getMismatchCollege() {
        return mismatchCollege;
    }

    public void setMismatchCollege(List<CollegeBean> mismatchCollege) {
        this.mismatchCollege = mismatchCollege;
    }

    public static class CollegeBean {

        private int collegeId;//院校ID
        private String collegeName;//院校名称
        private String logoUrl;//log picutre url
        private int rankOfCn;//排名
        private String subject;//选科要求

        public int getCollegeId() {
            return collegeId;
        }

        public void setCollegeId(int collegeId) {
            this.collegeId = collegeId;
        }

        public String getCollegeName() {
            return collegeName;
        }

        public void setCollegeName(String collegeName) {
            this.collegeName = collegeName;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }

        public int getRankOfCn() {
            return rankOfCn;
        }

        public void setRankOfCn(int rankOfCn) {
            this.rankOfCn = rankOfCn;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }
    }

}
