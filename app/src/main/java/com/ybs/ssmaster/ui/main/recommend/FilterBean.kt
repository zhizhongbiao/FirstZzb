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
data class FilterBean(val d:String="",val y:String="",val p:String="",val n:String=""){
    fun isEmpty()=TextUtils.isEmpty(d)&&TextUtils.isEmpty(y)&&TextUtils.isEmpty(p)&&TextUtils.isEmpty(n)

    fun copy()=FilterBean(d,y,p,n)
}
