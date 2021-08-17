package com.ybs.ssmaster.ui.count

import androidx.lifecycle.MutableLiveData
import com.ybs.ssmaster.ui.common.AppVm
import com.ybs.ssmaster.ui.main.recommend.PossessFilterBean

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
class CountDetailVm : AppVm() {


    val filter: MutableLiveData<PossessFilterBean> = MutableLiveData(PossessFilterBean())

    fun getCountDetail(id: String) = baseRequest2 {
        repos.getCountDetail(id)

    }


    fun getPossessList(
        id: String,
        code: String,
        storageLocation: String,
        checkStatus: String,
        name: String
    ) = baseRequest2 {
        repos.getPossessList(
            id,
            code,
            storageLocation,
            checkStatus,
            name
        )
    }


}