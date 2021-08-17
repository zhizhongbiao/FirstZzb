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
data class CountDetailBean(
    val code: Int? = 0,
    val `data`: Data? = Data(),
    val msg: String? = ""
) {

    fun isOk() = with(code ?: "") {
        return@with (code == 200)
    }

}

data class Data(
    val checkDate: String? = "",
    val checkDept: String? = "",
    val checkDetails: MutableList<CheckDetail>? = mutableListOf(),
    val checkNo: String? = "",
    val checkNum: Int? = 0,
    val checkOrderStatus: String? = "",
    val checkPerson: String? = "",
    val checkYear: String? = "",
    val createBy: String? = "",
    val createTime: String? = "",
    val delFlag: String? = "",
    val finishTime: String? = String(),
    val id: Int? = 0,
    val noCheckNum: Int? = 0,
    val params: Params? = Params(),
    val remark: String? = String(),
    val searchValue: String? = String(),
    val totalNum: Int? = 0,
    val updateBy: String? = "",
    val updateTime: String? = ""
)

data class CheckDetail(
    val adminDept: String? = "",
    val checkId: Int? = 0,
    val checkNum: String? = String(),
    val checkStatus: String? = "",
    val code: String? = "",
    val hexCode: String? = "",
    val createBy: String? = "",
    val createTime: String? = String(),
    val delFlag: String? = "",
    val difference: String? = String(),
    val fixedId: Int? = 0,
    val fixedNumber: String? = String(),
    val groupName: String? = "",
    val id: Int? = 0,
    val measureUnit: String? = String(),
    val model: String? = "",
    val name: String? = "",
    val params: Params? = Params(),
    val remark: String? = String(),
    val searchValue: String? = String(),
    val status: String? = "",
    val storageLocation: String? = "",
    val type: String? = "",
    val updateBy: String? = "",
    val updateTime: String? = "",
    val useStatus: String? = String()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CheckDetail

        if (checkId != other.checkId) return false
        if (code != other.code) return false

        return true
    }

    override fun hashCode(): Int {
        var result = checkId ?: 0
        result = 31 * result + (code?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "CheckDetail(adminDept=$adminDept, checkId=$checkId, checkNum=$checkNum, checkStatus=$checkStatus, code=$code, createBy=$createBy, createTime=$createTime, delFlag=$delFlag, difference=$difference, fixedId=$fixedId, fixedNumber=$fixedNumber, groupName=$groupName, id=$id, measureUnit=$measureUnit, model=$model, name=$name, params=$params, remark=$remark, searchValue=$searchValue, status=$status, storageLocation=$storageLocation, type=$type, updateBy=$updateBy, updateTime=$updateTime, useStatus=$useStatus)"
    }


}








