package com.ybs.ssmaster.ui.scan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ybs.base.data.bean.scan.Data
import com.ybs.base.data.bean.scan.ScanBean
import com.ybs.reslib.databinding.FgNewScanBinding
import com.ybs.ssmaster.ui.common.AppBaseFg
import com.ybs.ssmaster.ui.count.CountAct
import com.ybs.ssmaster.ui.count.CountVm
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
class ScanFg : AppBaseFg<FgNewScanBinding, CountVm, CountAct>() {

    override fun getVmClz() = CountVm::class.java

    companion object {
        fun newInst() = ScanFg()
    }

    private val adapt by lazy {
        ScanAdapt { _, _, data, _ ->
            d("  data = $data")
            handleItemDelete(data)
        }
    }

    private fun handleItemDelete(data: Data) {
        vm.deleteRecord(data.id?:"没有返回ID").observe(this, {
            if (it?.data?.isOk() == true) {
//                adapt.remove(data)
                refreshData()
            }
        })
    }


    override fun getLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FgNewScanBinding.inflate(inflater, container, false)

    override fun initView() {
        binding!!.rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapt
        }

        vm.scanBean.observe(this, {
//            checkIfExist(it)
            postItem(it)
        })

//        vm.uploadedListList.observe(this, {
//            binding?.tvScanNum?.text = "扫描总数：${it?.size}"
//            adapt.updateAll(it)
//        })


//        vm.checkedScanBean.observe(this, {
//            postItem(it)
//        })

        refreshData()


        binding?.btnScan?.setOnClickListener {
            handleBtnClick()
        }


        vm.flag.observe(this, {
            binding?.btnScan?.text = if (it) "停止扫描" else "开始扫描"
            binding?.pb?.visibility=if (it) View.VISIBLE else View.GONE
        })


        vm.scanInfo.observe(this, {
            binding?.tvInfo?.text = it
        })


        binding?.tvScanNum?.setOnLongClickListener {
            binding?.tvInfo?.visibility = View.VISIBLE
            return@setOnLongClickListener true
        }

    }

    private fun refreshData() {
        vm.getAllRfRecords().observe(this@ScanFg, {
            if (it?.data?.isOk() == true) {

                adapt.updateAll(it?.data?.data ?: mutableListOf())
                binding?.tvScanNum?.text = "记录扫描总数：${it?.data?.data?.size?:0}"
            }
        })
    }

    private fun postItem(it: ScanBean) {
        it?.apply {
            vm.postRfRecordItem(this).observe(this@ScanFg, {
                if (it?.data?.isOk()==true) {
                    it.data?.apply {
//                        adapt.addHead()
//                        adapt.updateAll(it?.data?.data?: mutableListOf())
                        refreshData()
                    }
                }
            })
        }
    }

    private fun checkIfExist(it: ScanBean) {
        it?.apply {
            vm.getAllRfRecords().observe(this@ScanFg, {
                if (it?.data?.isOk()==true) {
                    vm.checkIfUploaded(this, it.data!!.data)
                }
            })
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