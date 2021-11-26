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
data class UploadedBean(
    var id:Int,
    var timeConsumingFormat:String,
    var username:String,
    var scanTime:String,
    var rfid:String,
    var startTime:String
)
