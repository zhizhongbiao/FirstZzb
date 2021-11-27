package com.ybs.base.data.bean.scan

/**
 *
 * @ProjectName: FirstZzb
 * @Package: com.ybs.ssmaster.ui.scan
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/11/26 22:09
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/11/26 22:09
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
data class RecordBean(
    val code: Int? = 0,
    val data: MutableList<Data>? = mutableListOf(),
    val msg: String? = ""
)
{
    fun isOk() = with(code ?: "") {
        return@with ( code== 200)
    }
}

data class Data(
    val createBy: String? = "",
    val createTime: String? = "",
    val delFlag: String? = "",
    val heatId: String? = String(),
    val id: String? = "",
    val params: Params? = Params(),
    val remark: String? = String(),
    val rfid: String? = "",
    val scanTime: String? = "",
    val searchValue: String? = String(),
    val startTime: String? = "",
    val timeConsuming: Int? = 0,
    val timeConsumingFormat: String? = "",
    val updateBy: String? = "",
    val updateTime: String? = "",
    val username: String? = ""
)

class Params(
)