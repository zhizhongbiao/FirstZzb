package com.ybs.base.view.act

import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.ybs.base.view.loading.DLG_LOAD_LOADING
import com.ybs.base.view.loading.DLG_LOAD_SUCCEEDED
import com.ybs.base.view.loading.DlgStateBean
import com.ybs.base.view.loading.LoadingFg
import com.ybs.base.vm.BaseVm

/**
 *
 * @ProjectName: DistributeNetTool
 * @Package: com.ybs.distributenettool.base.view
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/13 19:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/13 19:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */



abstract class BaseVmAct<B : ViewBinding, VM : BaseVm> : BaseAct<B>() {

    protected open var isLoadingNeed = true

    protected lateinit var vm: VM

    private val loadingFg = LoadingFg()

    override fun setupVm() {
        vm = retrieveVm(getVmClz())
        vm.requestCounter.observe(this, {
            if (!isLoadingNeed || it == null) return@observe

            if (it > 0) {
                loadingFg.setUIState(DlgStateBean(DLG_LOAD_LOADING, "数据加载中。。。"))
                    .showDlg(supportFragmentManager)
            } else {
                loadingFg.setUIState(DlgStateBean(DLG_LOAD_SUCCEEDED, "请求完成"))
                isLoadingNeed = true
            }

        })
    }

    protected abstract fun getVmClz(): Class<VM>

    private fun retrieveVm(
        vmClz: Class<VM>,
        factory: ViewModelProvider.Factory = ViewModelProvider.NewInstanceFactory()
    ): VM = ViewModelProvider(this, factory).get(vmClz)

    override fun onPause() {
        loadingFg.dismiss()
        super.onPause()
    }
}