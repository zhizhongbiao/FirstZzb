package com.ybs.base.data.bean.scan

/**
 *
 * @ProjectName: FirstZzb
 * @Package: com.ybs.base.data.bean.scan
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/11/27 17:00
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/11/27 17:00
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
data class DeleteBean(
    val code: Int? = 0,
    val msg: String? = ""
){
    fun isOk() = with(code ?: "") {
        return@with ( code== 200)
    }
}