package com.ybs.ssmaster.ui.splash

import com.ybs.ssmaster.ui.common.AppVm

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.ssmaster.ui
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/20 17:05
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/20 17:05
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class SplashVm : AppVm() {

    fun login(name:String= "admin",psw:String= "admin123")=baseRequest2{
        repos.login(name, psw)
    }











}