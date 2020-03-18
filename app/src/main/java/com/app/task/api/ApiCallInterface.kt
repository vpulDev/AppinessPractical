package com.app.task.api

import com.app.task.api.responsemodel.EnterpriseListResponse
import io.reactivex.Observable
import retrofit2.http.GET


interface ApiCallInterface {
    @GET(NetworkHelper.API_JSON)
    fun callEnterPriseListAPI(): Observable<ArrayList<EnterpriseListResponse>>
}