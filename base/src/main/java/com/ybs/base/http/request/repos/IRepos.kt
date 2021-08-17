package com.ybs.base.http.request.repos

import com.ybs.base.http.request.api.AppService

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.base.http.request.repos
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/21 15:56
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/21 15:56
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
interface IRepos {

    fun getService(): AppService
}