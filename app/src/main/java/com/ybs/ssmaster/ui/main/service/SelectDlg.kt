package com.ybs.ssmaster.ui.main.service

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import com.ybs.base.data.bean.QueryRestaurantCommResp
import com.ybs.reslib.databinding.DlgConfirmCountBinding
import com.ybs.ssmaster.ui.common.AppDlgActFg
import com.ybs.ssmaster.ui.main.MainAct
import com.ybs.ssmaster.ui.main.MainVm

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



class SelectDlg : AppDlgActFg<DlgConfirmCountBinding, MainAct>() {

    private var data: QueryRestaurantCommResp? = null
    private val count: MutableLiveData<Int> = MutableLiveData(1)

    private val vm by lazy {
        retrieveActVm(MainVm::class.java)
    }

    companion object {
        fun newInst(d: QueryRestaurantCommResp) = SelectDlg().apply {
            arguments = bundleOf(
                KEY_TIPS to d
            )
        }
    }

    override fun getLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = DlgConfirmCountBinding.inflate(inflater, container, false)


    var onConfirmCb: ((count: Int) -> Unit)? = null

    override fun initView(args: Bundle?) {
        binding?.tvCancel?.setOnClickListener { dismiss() }
        binding?.tvSure?.setOnClickListener { onConfirmCb?.invoke(count.value!!) }

        binding?.ivAdd?.setOnClickListener {
            count.value = count.value?.inc()
        }
        binding?.ivCut?.setOnClickListener {
            val value = count.value
            if (value == 1) return@setOnClickListener
            count.value = value?.dec()
        }

        count.observe(this, {
            binding?.tvCount?.text = it.toString()
        })


        arguments?.apply {
            getParcelable<QueryRestaurantCommResp>(KEY_TIPS)?.let {
                data = it
            }
        }
    }

    override fun onDestroyView() {
        onConfirmCb = null
        super.onDestroyView()
    }
}