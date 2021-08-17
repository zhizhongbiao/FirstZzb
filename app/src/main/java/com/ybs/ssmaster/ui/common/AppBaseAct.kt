package com.ybs.ssmaster.ui.common

import android.content.Intent
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.ybs.base.http.request.AppInterceptor
import com.ybs.base.util.clearAllSpData
import com.ybs.base.view.act.BaseVmAct
import com.ybs.base.vm.BaseVm
import com.ybs.ssmaster.ui.splash.SplashAct
import d


/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.ssmaster.ui.common
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/29 11:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/29 11:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
abstract class AppBaseAct<B : ViewBinding, VM : BaseVm> : BaseVmAct<B, VM>() {


    override fun onCreate(savedInstanceState: Bundle?) {
       AppInterceptor.onTokenExpire={
           d("  startActivity(Intent(this, SplashAct::class.java))")
           clearAllSpData()
           startActivity(Intent(this, SplashAct::class.java))
       }
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        AppInterceptor.onTokenExpire=null
        super.onDestroy()
    }


}