package com.ybs.ssmaster.ui.count

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.ybs.reslib.databinding.FgPlanBinding
import com.ybs.ssmaster.ui.common.AppBaseFg
import com.ybs.ssmaster.ui.main.counter.CountDetailAdapt
import com.ybs.ssmaster.ui.main.recommend.PossessPlanFilterDlg
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
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
class PlanFg : AppBaseFg<FgPlanBinding, CountVm, CountAct>() {

    override fun getVmClz() = CountVm::class.java

    companion object {
        fun newInst(id: String) = PlanFg().apply {
            arguments = bundleOf(KEY_ID to id)
        }
    }

    private val adapt by lazy {
        CountDetailAdapt() { _, _, data, _ ->
            d("  data = $data")
        }
    }


    override fun getLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FgPlanBinding.inflate(inflater, container, false)

    override fun initView() {
        binding!!.rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapt
        }

        arguments?.apply {
            val id = getString(KEY_ID, "id is null")
            vm.filter.observe(this@PlanFg, {
                vm.getPossessList(
                    id,
                    it.no,
                    it.loc,
                    it.state,
                    it.name
                ).observe(this@PlanFg, {
                    if (it?.data?.isOk() == true) {
                        adapt.updateAll(it.data?.data ?: mutableListOf())
//                        ToastUtils.showLong(it.data?.msg ?: "获取数据数据成功！")
                    } else {
                        ToastUtils.showLong(it.data?.msg ?: "获取数据失败！")
                    }
                })




                if (it == null || it.isEmpty()) {
                    binding?.fl?.visibility = View.GONE
                    return@observe
                }

                binding?.fl?.visibility = View.VISIBLE
                tags.apply {
                    clear()
                    add(it.no)
                    add(it.name)
                    add(it.loc)
                    add(when(it.state){
                        "1" ->"未盘"
                        "2" ->"已盘"
                        else ->""
                    })
                }
                a.notifyDataChanged()

            })
        }

        binding?.btnFilter?.setOnClickListener {
            PossessPlanFilterDlg.newInst().showDlg(childFragmentManager)
        }
    }


    private val tags: ArrayList<String> = arrayListOf()

    private val a by lazy {
        object : TagAdapter<String>(tags) {
            override fun getView(parent: FlowLayout?, position: Int, s: String?) :View{

                val tv = layoutInflater.inflate(
                    com.ybs.ssmaster.R.layout.layout_tv,
                    binding?.fl, false
                ) as TextView
                tv.text = s
                return tv
            }

        }
    }



}