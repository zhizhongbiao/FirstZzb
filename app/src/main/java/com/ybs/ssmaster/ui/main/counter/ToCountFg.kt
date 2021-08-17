package com.ybs.ssmaster.ui.main.counter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.ybs.base.data.bean.counter.Row
import com.ybs.reslib.R
import com.ybs.reslib.databinding.FgCountRvBinding
import com.ybs.ssmaster.ui.common.AppBaseFg
import com.ybs.ssmaster.ui.count.CountAct
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
class ToCountFg : AppBaseFg<FgCountRvBinding, MainVm, MainAct>() {

    override fun getVmClz() = MainVm::class.java

    companion object {
        fun newInst() = ToCountFg()
    }

    private val adapt by lazy {
        ToCAdapt() { v, a, data, pos ->
            d("  data = $data")
            val id = v?.id
            if (id == R.id.btnCount) {
                toCountAct("${data.id}")
            } else if (id == R.id.tvName) {
                CountDetailAct.startAct(getAct(), "${data.id}")
            } else {
//                ServiceConfirmDlg.newInst("确认后不可撤销，是否确认结束盘点？").apply {
//                    if (!isAdded){
//                        ToastUtils.showLong("还没附上！")
//                        return@ToCAdapt
//                    }
//                    showDlg(childFragmentManager,"1222")
//                }.onConfirmCb = {
//                    endCount(data)
//                }

                getAct().showDlg("确认后不可撤销，是否确认结束盘点？") {
                    endCount(data)
                }
            }
        }
    }

    private fun endCount(data: Row) {
        vm.endCount("${data.id}").observe(this, {
            if (it?.data?.isOk() == true) {
//                ToastUtils.showLong("结束盘点成功！")
                vm.filter.postValue(vm.filter.value!!.copy())
            } else {
                ToastUtils.showLong(it.data?.msg ?: "结束盘点失败！")
            }

        })
    }

    private fun toCountAct(id: String) {
        CountAct.startAct(getAct(), id)
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

    private fun initData(bean: FilterBean) {
        vm.getCountedData(
            bean.y ?: "",
            bean.p ?: "",
            bean.n ?: "",
            bean.d ?: "",
            checkOrderStatus = "1,2"
        ).observe(this@ToCountFg, {
            binding?.srf?.isRefreshing=false
            if (it?.data?.isOk() == true) {
                adapt.updateAll(it.data?.rows ?: mutableListOf())
                vm.countNum.postValue(it.data?.rows?.size ?: 0)
            } else {
                ToastUtils.showLong(it.data?.msg ?: "获取数据失败！")
            }
        })
    }


}