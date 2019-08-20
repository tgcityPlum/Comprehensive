package com.tgcity.network.api;

import com.tgcity.base.network.bean.request.QueryMatchAndMismatchCollegeInput;
import com.tgcity.base.network.bean.response.E360QuestionModel;
import com.tgcity.base.network.bean.response.QueryMajorChooseSubjectOutput;
import com.tgcity.base.network.bean.response.QueryMatchAndMismatchCollegeOutput;
import com.tgcity.network.bean.result.HttpResultTZY;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService5001 {

    /**
     * 测评类型(1:自我控制能力测评,2:学习倦怠测评,3:考试心里和行为测评,4:学习能力(非智力因素)自我诊断测评,5:学业拖延测评,6:家庭教养方式测评)
     *
     * @param type
     * @return
     */
    @POST("Evaluation/Complex/Get")
    Observable<HttpResultTZY<E360QuestionModel>> getE360Question(@Query("type") int type);

    //选科--获得匹配和不匹配院校
    @POST("TZY/ChooseSubject/QueryMatchAndMismatchCollege")
    Observable<HttpResultTZY<QueryMatchAndMismatchCollegeOutput>> queryMatchAndMismatchCollege(@Body QueryMatchAndMismatchCollegeInput input);

    //选科--查询选科推荐专业小类专业
    @POST("TZY/ChooseSubject/QueryMajorChooseSubject")
    Observable<HttpResultTZY<List<QueryMajorChooseSubjectOutput>>> queryMajorChooseSubject(@Query("provinceId") int provinceId, @Query("MajorCode") String MajorCode, @Query("year") int year);

}
