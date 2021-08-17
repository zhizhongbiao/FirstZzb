package com.ybs.base.data.bean.counter

/**
 *
 * @ProjectName: RetrieveIdTool
 * @Package: com.ybs.base.base.http
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/20 21:12
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/20 21:12
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
 data class CountRecordBean(
    val code: Int?,
    val msg: String?
){

    fun isOk() = with(code ?: "") {
        return@with ( code== 200)
    }
}