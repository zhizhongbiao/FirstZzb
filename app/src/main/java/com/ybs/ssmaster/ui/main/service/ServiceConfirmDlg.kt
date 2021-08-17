package com.ybs.ssmaster.ui.main.service

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.ybs.reslib.databinding.DlgConfirmServiceBinding
import com.ybs.ssmaster.ui.common.AppDlgActFg
import com.ybs.ssmaster.ui.main.MainAct

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

const val KEY_TIPS = "keyTips"

class ServiceConfirmDlg : AppDlgActFg<DlgConfirmServiceBinding, MainAct>() {

    private var data: String = ""

    companion object {
        fun newInst(info: String) = ServiceConfirmDlg().apply {
            arguments = bundleOf(
                KEY_TIPS to info
            )
        }
    }

    override fun getLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = DlgConfirmServiceBinding.inflate(inflater, container, false)


    var onConfirmCb: ((data: String) -> Unit)? = null

    override fun initView(args: Bundle?) {
        binding?.tvCancel?.setOnClickListener { dismiss() }
        binding?.tvSure?.setOnClickListener {
            onConfirmCb?.invoke(data)
            dismiss()
        }


        arguments?.apply {
            getString(KEY_TIPS, "确认执行该操作？").let {
                data = it
                binding?.tvTitle?.text = data
            }
        }
    }

    override fun onDestroyView() {
        onConfirmCb = null
        super.onDestroyView()
    }
}