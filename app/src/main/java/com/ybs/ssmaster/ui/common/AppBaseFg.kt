package com.ybs.ssmaster.ui.common

import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import com.ybs.base.view.fg.BaseVmActFg
import com.ybs.base.vm.BaseVm

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.ssmaster.ui.main.process
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/25 19:36
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/25 19:36
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
abstract class AppBaseFg<B : ViewBinding, VM : BaseVm, A : FragmentActivity> :
    BaseVmActFg<B, VM, A>() {

    override fun onHiddenChanged(hidden: Boolean) {
        setupVm()
        super.onHiddenChanged(hidden)

    }

}