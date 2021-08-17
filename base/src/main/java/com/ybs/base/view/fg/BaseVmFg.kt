package com.ybs.base.view.fg

import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
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
abstract class BaseVmFg<B : ViewBinding, VM : BaseVm> : BaseFg<B>() {


    protected lateinit var vm: VM

    protected open fun hasOwnVm()=false

    protected abstract fun getVmClz(): Class<VM>

    override fun setupVm() {
        vm = if (hasOwnVm())retrieveVm(getVmClz()) else retrieveActVm(getVmClz())
    }

   private fun <VM : BaseVm> retrieveVm(
        vmClz: Class<VM>,
        factory: ViewModelProvider.Factory = ViewModelProvider.NewInstanceFactory()
    ) = ViewModelProvider(this, factory).get(vmClz)

  private  fun <VM : BaseVm> retrieveActVm(
        vmClz: Class<VM>,
        factory: ViewModelProvider.Factory = ViewModelProvider.NewInstanceFactory()
    ) = ViewModelProvider(requireActivity(), factory).get(vmClz)



}