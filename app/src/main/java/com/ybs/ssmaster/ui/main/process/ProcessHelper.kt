package com.ybs.ssmaster.ui.main.process

import com.ybs.base.data.AppDataManager
import com.ybs.base.data.bean.DishesProcessEntity
import com.ybs.base.manager.TaskManger

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.ssmaster.ui.main.process
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/26 10:20
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/26 10:20
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class ProcessHelper private constructor() {

    companion object {
        val inst = ProcessHelper()
    }

    fun handleProcessList(
        newData: MutableList<DishesProcessEntity.Status>,
        cb: ((list: MutableList<DishesProcessEntity.Status>) -> Unit)?=null
    ) = TaskManger.inst.execInBg {
        val allFoodList = AppDataManager.inst.allFoodList
        val list = mutableListOf<DishesProcessEntity.Status>()
        for (status in newData) {
            if ("自助酱料" == status.dishesName) continue
            list.add(status)
            allFoodList.find {
                status.dishesName?.contains(it.specialDishTitle ?: "") ?: false
            }?.apply {
                status.specialDishImgUrl = this.specialDishImgUrl
            }
        }
        cb?.invoke(list)
    }

}