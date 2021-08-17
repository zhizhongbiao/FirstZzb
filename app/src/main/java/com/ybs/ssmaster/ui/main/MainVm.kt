package com.ybs.ssmaster.ui.main

import androidx.lifecycle.MutableLiveData
import com.ybs.base.data.AppDataManager
import com.ybs.base.data.bean.DishesProcessEntity
import com.ybs.ssmaster.ui.common.AppVm
import com.ybs.ssmaster.ui.main.recommend.FilterBean

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.ssmaster.ui
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/20 17:05
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/20 17:05
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

const val FROM_MANUAL = 1
const val FROM_AI = 2

class MainVm : AppVm() {

    companion object{
        val refreshData: MutableLiveData<Int> = MutableLiveData(0)
    }


    val filter: MutableLiveData<FilterBean> = MutableLiveData(FilterBean())

    val foodProcessList: MutableLiveData<MutableList<DishesProcessEntity.Status>> =
        MutableLiveData(mutableListOf())


    fun requestFoodProcess() = baseRequest {
        repos.requestFoodProcess(
            AppDataManager.inst.accessTokenResp?.merchantId!!,
            AppDataManager.inst.accessTokenResp?.deviceSn!!
        )
    }


    fun queryAdvertisement() = baseRequest {
        repos.queryAdvertisement(AppDataManager.inst.accessTokenResp?.merchantId!!)
    }

    fun queryRestaurantService() = baseRequest {
        repos.queryRestaurantService(AppDataManager.inst.accessTokenResp?.merchantId!!)
    }

    fun getCountedData(
        checkYear: String="",
        checkPerson: String="",
        checkNo: String="",
        checkDate: String="",
        checkOrderStatus: String=""
    ) = baseRequest2 {
        repos.getCountedData(
            checkYear,
            checkPerson,
            checkNo,
            checkDate,
            checkOrderStatus
        )
    }



    fun handleProcessData(data: String) = ""



    fun endCount(checkNo: String) = baseRequest2 {
        repos.endCount(checkNo)
    }

    val countNum:MutableLiveData<Int> = MutableLiveData(0)
}