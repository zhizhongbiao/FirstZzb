package com.ybs.ssmaster.ui.main.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ybs.base.adapter.UIUtils
import com.ybs.reslib.databinding.DlgPossessBinding
import com.ybs.ssmaster.ui.common.AppDlgActFg
import com.ybs.ssmaster.ui.count.CountDetailAct
import com.ybs.ssmaster.ui.count.CountDetailVm

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
class PossessDetailFilterDlg : AppDlgActFg<DlgPossessBinding, CountDetailAct>() {

    companion object {
        fun newInst() = PossessDetailFilterDlg()
    }

    private val vm by lazy {
        retrieveActVm(CountDetailVm::class.java)
    }


    override fun getLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = DlgPossessBinding.inflate(inflater, container, false)

    override fun initView(args: Bundle?) {
        binding?.btnReset?.setOnClickListener {
            binding?.etLoc?.setText("")
            binding?.etNo?.setText("")
            binding?.etName?.setText("")
            binding?.cbNot?.isChecked = false
            binding?.cbHad?.isChecked = false
        }


        binding?.btnSure?.setOnClickListener {
            vm.filter.postValue(
                PossessFilterBean(
                    binding?.etNo?.text.toString(),
                    binding?.etName?.text.toString(),
                    binding?.etLoc?.text.toString(),
                    when {
                        binding!!.cbNot?.isChecked && binding!!.cbHad?.isChecked -> "1,2"
                        binding!!.cbNot?.isChecked -> "1"
                        binding!!.cbHad?.isChecked -> "2"
                        else -> ""

                    }
                )
            )
            dismiss()
        }
    }


    override fun getH() = UIUtils.dip2px(300)
    override fun getW() = UIUtils.dip2px(350)


}