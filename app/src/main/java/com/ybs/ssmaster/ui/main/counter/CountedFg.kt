package com.ybs.ssmaster.ui.main.counter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.ybs.reslib.databinding.FgCountRvBinding
import com.ybs.ssmaster.ui.common.AppBaseFg
import com.ybs.ssmaster.ui.count.CountDetailAct
import com.ybs.ssmaster.ui.main.MainAct
import com.ybs.ssmaster.ui.main.MainVm
import com.ybs.ssmaster.ui.main.recommend.FilterBean
import d

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
class CountedFg : AppBaseFg<FgCountRvBinding, MainVm, MainAct>() {

    override fun getVmClz() = MainVm::class.java

    companion object {
        fun newInst() = CountedFg()
    }

    private val adapt by lazy {
        CountedAdapt() { _, _, data, _ ->
            d("  data = $data")
            CountDetailAct.startAct(getAct(), "${data.id}")
        }
    }


    override fun getLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FgCountRvBinding.inflate(inflater, container, false)

    override fun initView() {
        binding!!.rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapt
        }

        vm.filter.observe(this, {
            it?.apply {
                initData(this)
            }
        })

        binding?.srf?.setOnRefreshListener {
            initData(vm.filter.value!!)
        }
    }

    private fun initData(p: FilterBean) = vm.getCountedData(
        p.y,
        p.p,
        p.n,
        p.d,
        checkOrderStatus = "3"
    ).observe(this@CountedFg, {
        binding?.srf?.isRefreshing=false
        if (it?.data?.isOk() == true) {
            adapt.updateAll(it.data?.rows ?: mutableListOf())
        } else {
            ToastUtils.showLong(it.data?.msg ?: "获取数据失败！")
        }
    })


}