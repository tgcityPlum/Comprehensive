package com.tgcity.base.network.bean.response;

import java.util.List;

public class QueryMajorChooseSubjectOutput {

    private MajorBean major;//选科查询返回的实体
    private String subject;//选科科目
    private CollegeBean college;//
    private List<ContainMajorsBean> containMajors;//

    public MajorBean getMajor() {
        return major;
    }

    public void setMajor(MajorBean major) {
        this.major = major;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public CollegeBean getCollege() {
        return college;
    }

    public void setCollege(CollegeBean college) {
        this.college = college;
    }

    public List<ContainMajorsBean> getContainMajors() {
        return containMajors;
    }

    public void setContainMajors(List<ContainMajorsBean> containMajors) {
        this.containMajors = containMajors;
    }

    public static class MajorBean {

        private String professionName;//专业名称，从基础表来
        private String majorCode;//专业国标
        private String majorPath;//专业路径
        private int majorMale;//专业男生所占比列
        private int majorFeMale;//专业女生所占比列
        private boolean isBen;//是否本科 1 本科 0 专科
        private int collegeCount;//选科院校数

        public String getProfessionName() {
            return professionName;
        }

        public void setProfessionName(String professionName) {
            this.professionName = professionName;
        }

        public String getMajorCode() {
            return majorCode;
        }

        public void setMajorCode(String majorCode) {
            this.majorCode = majorCode;
        }

        public String getMajorPath() {
            return majorPath;
        }

        public void setMajorPath(String majorPath) {
            this.majorPath = majorPath;
        }

        public int getMajorMale() {
            return majorMale;
        }

        public void setMajorMale(int majorMale) {
            this.majorMale = majorMale;
        }

        public int getMajorFeMale() {
            return majorFeMale;
        }

        public void setMajorFeMale(int majorFeMale) {
            this.majorFeMale = majorFeMale;
        }

        public boolean isIsBen() {
            return isBen;
        }

        public void setIsBen(boolean isBen) {
            this.isBen = isBen;
        }

        public int getCollegeCount() {
            return collegeCount;
        }

        public void setCollegeCount(int collegeCount) {
            this.collegeCount = collegeCount;
        }
    }

    public static class CollegeBean {

        private int collegeId;//院校ID
        private String collegeName;//院校名称
        private String uCode;//院校招生代码
        private String logoUrl;//log picutre url
        private String level;//办学层次，如：211、985
        private int is985;//是否985高校
        private int is211;//是否211高校
        private int isKey;//是否全国重点
        private int isProvincial;//是否省重点高校
        private String firstClass;//双一流（一流大学建设）
        private int rankOfCn;//排名
        private String subject;//科目
        private boolean isBen;//是否本科
        private boolean isChooseSubjectCollege;//本大学针对指定的省份是否有选科

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

        public String getUCode() {
            return uCode;
        }

        public void setUCode(String uCode) {
            this.uCode = uCode;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public int getIs985() {
            return is985;
        }

        public void setIs985(int is985) {
            this.is985 = is985;
        }

        public int getIs211() {
            return is211;
        }

        public void setIs211(int is211) {
            this.is211 = is211;
        }

        public int getIsKey() {
            return isKey;
        }

        public void setIsKey(int isKey) {
            this.isKey = isKey;
        }

        public int getIsProvincial() {
            return isProvincial;
        }

        public void setIsProvincial(int isProvincial) {
            this.isProvincial = isProvincial;
        }

        public String getFirstClass() {
            return firstClass;
        }

        public void setFirstClass(String firstClass) {
            this.firstClass = firstClass;
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

        public boolean isIsBen() {
            return isBen;
        }

        public void setIsBen(boolean isBen) {
            this.isBen = isBen;
        }

        public boolean isIsChooseSubjectCollege() {
            return isChooseSubjectCollege;
        }

        public void setIsChooseSubjectCollege(boolean isChooseSubjectCollege) {
            this.isChooseSubjectCollege = isChooseSubjectCollege;
        }
    }

    public static class ContainMajorsBean {

        private String professionName;//专业名称，从基础表来
        private String majorCode;//专业国标
        private String majorPath;//专业路径
        private int majorMale;//专业男生所占比列
        private int majorFeMale;//专业女生所占比列
        private boolean isBen;//是否本科 1 本科 0 专科
        private int collegeCount;//选科院校数

        public String getProfessionName() {
            return professionName;
        }

        public void setProfessionName(String professionName) {
            this.professionName = professionName;
        }

        public String getMajorCode() {
            return majorCode;
        }

        public void setMajorCode(String majorCode) {
            this.majorCode = majorCode;
        }

        public String getMajorPath() {
            return majorPath;
        }

        public void setMajorPath(String majorPath) {
            this.majorPath = majorPath;
        }

        public int getMajorMale() {
            return majorMale;
        }

        public void setMajorMale(int majorMale) {
            this.majorMale = majorMale;
        }

        public int getMajorFeMale() {
            return majorFeMale;
        }

        public void setMajorFeMale(int majorFeMale) {
            this.majorFeMale = majorFeMale;
        }

        public boolean isIsBen() {
            return isBen;
        }

        public void setIsBen(boolean isBen) {
            this.isBen = isBen;
        }

        public int getCollegeCount() {
            return collegeCount;
        }

        public void setCollegeCount(int collegeCount) {
            this.collegeCount = collegeCount;
        }
    }
}
