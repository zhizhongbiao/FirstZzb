package com.ybs.base.data.api

import com.ybs.base.data.bean.LoginReq
import com.ybs.base.data.bean.counter.*
import retrofit2.http.*

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.base.http.request.api
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/21 15:45
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/21 15:45
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
interface SplashApi {


    @Headers("download:false")
    @POST("/town/api/login")
    suspend fun login(@Body req: LoginReq): LoginBean


    @POST("/town/api/ass/postCheckList")
    suspend fun postCountedRecord(@Body req: CountRecordReq): CountRecordBean


    @GET("/town/api/ass/getCheckDetail")
    suspend fun getCountDetail(
        @Query("id") id: String
    ): CountDetailBean //商户ID


    @GET("/town/api/ass/screenCheckDetail")
    suspend fun getPossessList(
        @Query("checkId") checkId: String,
        @Query("code") code: String,
        @Query("storageLocation") storageLocation: String,
        @Query("checkStatus") checkStatus: String,
        @Query("name") name: String
    ): PossessListBean //商户ID


}