package com.ybs.base.view.act

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter

/**
 *
 * @ProjectName: DistributeNetTool
 * @Package: com.ybs.distributenettool
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/13 17:44
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/13 17:44
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

abstract class ARouterBaseAct<B : ViewBinding> : BaseAct<B>() {



    protected var router: ARouter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        router = ARouter.getInstance()
        router?.inject(this)
        super.onCreate(savedInstanceState)

    }




    override fun onDestroy() {
        router = null
        super.onDestroy()
    }



}