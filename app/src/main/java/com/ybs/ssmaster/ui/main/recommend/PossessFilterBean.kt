package com.ybs.ssmaster.ui.main.recommend

import android.text.TextUtils

/**
 *
 * @ProjectName: FirstZzb
 * @Package: com.ybs.ssmaster.ui.main.recommend
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/8/8 16:03
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/8 16:03
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
data class PossessFilterBean(val no:String="", val name:String="", val loc:String="", val state:String=""){

    fun isEmpty()= TextUtils.isEmpty(no)&& TextUtils.isEmpty(name)&& TextUtils.isEmpty(loc)&& TextUtils.isEmpty(state)
}