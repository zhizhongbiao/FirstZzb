package com.ybs.base.data.api

import com.ybs.base.data.bean.QueryCarouselResp
import com.ybs.base.data.bean.QueryRestaurantCommResp
import com.ybs.base.data.bean.QuerySpecialDishResp
import com.ybs.base.data.bean.counter.CounterBean
import com.ybs.base.data.bean.counter.EndCountBean
import com.ybs.base.data.bean.counter.EndCountReq
import com.ybs.base.http.response.AppResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

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
interface MainApi {


    @GET("/api/yuanyuan/device/specialdish/list")
    suspend fun getAllFoodList(@Query("merchantId") merchantId: String)
    : AppResponse<MutableList<QuerySpecialDishResp>>

    @GET("/api/restaurant/ypsQueryTableDishesProcess")
    suspend  fun requestFoodProcess(
        @Query("merchantId") merchantId: String,
        @Query("deviceSn") deviceSn: String
    ): AppResponse<String>

    //获取广告轮播
    @GET("/api/yuanyuan/device/carousel/listByMerchantId")
    suspend fun queryAdvertisement(@Query("merchantId") merchantId: String)
    : AppResponse<MutableList<QueryCarouselResp>>

    @GET("/api/yuanyuan/device/foodService/list")
    suspend  fun queryRestaurantService(@Query("merchantId") merchantId: String)
    : AppResponse<MutableList<QueryRestaurantCommResp>> //商户ID


    //提任务

    @PUT("/town/api/ass/endCheck")
    suspend  fun endCount(@Body req: EndCountReq): EndCountBean

    @GET("/town/api/ass/list")
    suspend  fun getCountedData(
        @Query("checkYear") checkYear: String,
        @Query("checkPerson") checkPerson: String,
        @Query("checkNo") checkNo: String,
        @Query("checkDate") checkDate: String,
        @Query("checkOrderStatus") checkOrderStatus: String
    ): CounterBean //商户ID


}