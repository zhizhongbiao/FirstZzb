package com.ybs.base.view.fg

import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import com.ybs.base.view.IActFg
import com.ybs.base.vm.BaseVm

/**
 *
 * @ProjectName: DistributeNetTool
 * @Package: com.ybs.distributenettool.base.view
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/13 20:03
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/13 20:03
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
abstract class BaseVmActFg<B : ViewBinding, VM : BaseVm, A : FragmentActivity> : BaseVmFg<B,VM>(), IActFg<A> {

    override fun getAct() = requireActivity() as A

}