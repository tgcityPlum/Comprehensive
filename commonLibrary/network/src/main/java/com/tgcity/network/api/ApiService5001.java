package com.tgcity.network.api;

import com.tgcity.base.network.bean.response.QueryMajorChooseSubjectOutput;
import com.tgcity.network.bean.result.HttpResultTZY;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService5001 {

    //选科--查询选科推荐专业小类专业
    @POST("TZY/ChooseSubject/QueryMajorChooseSubject")
    Observable<HttpResultTZY<List<QueryMajorChooseSubjectOutput>>> queryMajorChooseSubject(@Query("provinceId") int provinceId, @Query("MajorCode") String MajorCode, @Query("year") int year);

}
