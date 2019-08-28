package com.tgcity.network.api;

import com.tgcity.base.network.bean.response.QueryMajorChooseSubjectOutput;
import com.tgcity.network.bean.result.HttpCommonResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author TGCity
 */
public interface ApiService5001 {

    /**
     * 选科--查询选科推荐专业小类专业
     * @param provinceId int
     * @param majorCode String
     * @param year int
     * @return HttpCommonResult<List<QueryMajorChooseSubjectOutput>>
     */
    @POST("TZY/ChooseSubject/QueryMajorChooseSubject")
    Observable<HttpCommonResult<List<QueryMajorChooseSubjectOutput>>> queryMajorChooseSubject(@Query("provinceId") int provinceId, @Query("MajorCode") String majorCode, @Query("year") int year);

}
