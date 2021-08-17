package com.ybs.base.data.bean.counter

/**
 *
 * @ProjectName: FirstZzb
 * @Package: com.ybs.base.data.bean.counter
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/8/7 13:25
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/7 13:25
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
data class PossessListBean(
    val code: Int? = 0,
    val `data`: MutableList<CheckDetail>? = mutableListOf(),
    val msg: String? = ""
){

    fun isOk() = with(code ?: "") {
        return@with ( code== 200)
    }

}









