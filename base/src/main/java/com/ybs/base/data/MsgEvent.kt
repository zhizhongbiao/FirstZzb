package com.ybs.base.data

import java.util.*

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.ssmaster.mqtt
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/29 21:01
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/29 21:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
data class MsgEvent(
    val eventType:String,
    val data:String,
    val bData:Boolean=false,
    val extra:String="",
    val eventTime:String=Calendar.getInstance().time.toString()
)
