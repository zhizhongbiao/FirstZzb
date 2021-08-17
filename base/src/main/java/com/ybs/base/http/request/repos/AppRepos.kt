package com.ybs.base.http.request.repos

import com.ybs.base.data.repos.MainRepos
import com.ybs.base.data.repos.SplashRepos
import com.ybs.base.http.request.api.AppService

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.ssmaster.ui.splash
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/20 17:26
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/20 17:26
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class AppRepos private constructor() : BaseRepos<AppService>(AppService::class.java)
    , SplashRepos, MainRepos {

    companion object {
        val inst = AppRepos()
    }

    override fun getService()=requestSer
}