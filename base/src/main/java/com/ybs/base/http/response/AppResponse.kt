package com.ybs.base.http.response

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
 data class AppResponse<D>(
    val code: String?,
    val count: Int?,
    val message: String?,
    val data: D?
){

    fun isOk() = with(code ?: "") {
        return@with ( code== "200")
    }
}