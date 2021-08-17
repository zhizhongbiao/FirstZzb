package com.ybs.ssmaster.ui.count

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ybs.reslib.databinding.FgScanBinding
import com.ybs.ssmaster.ui.common.AppBaseFg
import com.ybs.ssmaster.ui.main.counter.CountDetailAdapt
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
class ScanFg : AppBaseFg<FgScanBinding, CountVm, CountAct>() {

    override fun getVmClz() = CountVm::class.java

    companion object {
        fun newInst() = ScanFg()
    }

    private val adapt by lazy {
        CountDetailAdapt { _, _, data, _ ->
            d("  data = $data")
        }
    }


    override fun getLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FgScanBinding.inflate(inflater, container, false)

    override fun initView() {
        binding!!.rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapt
        }

        vm.scanList.observe(this, {
            binding?.tvScanNum?.text = "扫描总数：${it?.size}"
            adapt.updateAll(it)
        })


        binding?.btnScan?.setOnClickListener {
            handleBtnClick()
        }


        vm.flag.observe(this, {
            binding?.btnScan?.text = if (it) "停止扫描" else "开始扫描"
        })


        vm.scanInfo.observe(this, {
            binding?.tvInfo?.text = it
        })


        binding?.tvScanNum?.setOnLongClickListener {
            binding?.tvInfo?.visibility = View.VISIBLE
            return@setOnLongClickListener true
        }

    }

     fun handleBtnClick() {
        if (vm.flag.value == true) {
            vm.stopScan()
        } else {
            vm.scan()
        }
    }


    override fun onStop() {
        super.onStop()
        vm.stopScan()
    }


}