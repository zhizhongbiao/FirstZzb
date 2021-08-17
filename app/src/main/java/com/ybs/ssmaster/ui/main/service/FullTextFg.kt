package com.ybs.ssmaster.ui.main.service

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ybs.base.data.bean.QueryRestaurantCommResp
import com.ybs.base.view.fg.BaseActFg
import com.ybs.reslib.databinding.FgFullTextBinding
import com.ybs.ssmaster.ui.main.MainAct
import com.ybs.ssmaster.ui.main.recommend.KEY_URL

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


class FullTextFg : BaseActFg<FgFullTextBinding, MainAct>() {



    companion object {
        fun newInst(url: String) = FullTextFg().apply {
            arguments = Bundle().also {
                it.putString(KEY_URL, url)
            }
        }
    }

    override fun getLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FgFullTextBinding.inflate(inflater, container, false)

    override fun initView() {

        binding?.ivBack?.setOnClickListener {

        }

        arguments?.apply {
            when (getInt(KEY_FROM)) {
                FROM_SERVICE -> handleService(this)
            }
        }

    }

    private fun handleService(bundle: Bundle) =
        bundle.getParcelable<QueryRestaurantCommResp>(KEY_QRCODE_DATA)?.apply {
            binding?.tvTitle?.text = this.codeValue
            binding?.tvContent?.text = templateConfig.displayText
        }

}