package com.ybs.base.data.repos

import com.ybs.base.data.bean.counter.EndCountReq
import com.ybs.base.http.request.repos.IRepos

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.base.http.request.repos
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/21 15:54
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/21 15:54
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
interface MainRepos : IRepos {

    suspend fun getAllFoodList(
        merchantId: String
    ) = getService().getAllFoodList(merchantId)


    suspend fun requestFoodProcess(
        merchantId: String,
        deviceSn: String
    ) = getService().requestFoodProcess(merchantId, deviceSn)


    suspend fun queryAdvertisement(
        merchantId: String
    ) = getService().queryAdvertisement(merchantId)

    suspend fun queryRestaurantService(
        merchantId: String
    ) = getService().queryRestaurantService(merchantId)

    suspend fun getCountedData(
        checkYear: String,
        checkPerson: String,
        checkNo: String,
        checkDate: String,
        checkOrderStatus: String
    ) = getService().getCountedData(
        checkYear,
        checkPerson,
        checkNo,
        checkDate,
        checkOrderStatus
    )

    suspend fun endCount(
        id: String
    ) = getService().endCount(EndCountReq(id))
}