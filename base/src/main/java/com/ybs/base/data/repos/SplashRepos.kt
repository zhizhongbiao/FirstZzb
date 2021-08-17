package com.ybs.base.data.repos

import com.ybs.base.data.bean.LoginReq
import com.ybs.base.data.bean.counter.CountRecordReq
import com.ybs.base.http.request.repos.IRepos

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.base.http.request.repos
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/21 15:54
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/21 15:54
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
interface SplashRepos : IRepos {


    suspend fun login(name:String,psw:String) = getService().login(
        LoginReq().apply {
            this.username = name
            this.password = psw
        })

    suspend fun postCountedRecord(req: CountRecordReq) = getService().postCountedRecord(req)

    suspend fun getCountDetail(id: String) = getService().getCountDetail(id)


    suspend fun getPossessList(
        checkId: String,
        code: String,
        storageLocation: String,
        checkStatus: String,
        name: String
    ) = getService().getPossessList(
        checkId,
        code,
        storageLocation,
        checkStatus,
        name
    )


}