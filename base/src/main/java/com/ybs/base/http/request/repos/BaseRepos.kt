package com.ybs.base.http.request.repos

import com.ybs.base.http.RetrofitManger

/**
 *
 * @ProjectName: RetrieveIdTool
 * @Package: com.ybs.base.base.http
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/20 19:14
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/20 19:14
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
open class BaseRepos<S>
constructor(private val service: Class<S>, protected val requestSer: S = RetrofitManger.inst.createService(service))