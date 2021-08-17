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
data class CounterBean(
    val code: Int? = 0,
    val msg: String? = "",
    val rows: MutableList<Row>? = mutableListOf(),
    val total: Int? = 0
){


    fun isOk() = with(code ?: "") {
        return@with ( code== 200)
    }
}

data class Row(
    val checkDate: String? = "",
    val checkDept: String? = String(),
    val checkDetails: String? = String(),
    val checkNo: String? = "",
    val checkNum: String? = String(),
    val checkOrderStatus: String? = "",
    val checkPerson: String? = "",
    val checkYear: String? = "",
    val createBy: String? = "",
    val createTime: String? = "",
    val delFlag: String? = "",
    val cleanNum: String? = "",
    val finishTime: String? = String(),
    val id: Int? = 0,
    val noCheckNum: String? = String(),
    val params: Params? = Params(),
    val remark: String? = String(),
    val searchValue: String? = String(),
    val totalNum: String? = String(),
    val updateBy: String? = "",
    val updateTime: String? = ""
)

class Params(
)