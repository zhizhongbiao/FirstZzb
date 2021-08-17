package com.ybs.ssmaster.ui.main.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ybs.base.view.fg.BaseActFg
import com.ybs.reslib.R
import com.ybs.reslib.databinding.FgAdBinding
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

const val KEY_URL = "keyUrl"

class AdFg : BaseActFg<FgAdBinding, MainAct>() {


    companion object {
        fun newInst(url: String) = AdFg().apply {
            arguments = Bundle().also {
                it.putString(KEY_URL, url)
            }
        }
    }

    override fun getLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FgAdBinding.inflate(inflater, container, false)

    override fun initView() {

        arguments?.getString(KEY_URL)?.apply {

            val options: RequestOptions = RequestOptions()
                .placeholder(R.mipmap.img_placeholder) //图片加载出来前，显示的图片
                .fallback(R.mipmap.img_placeholder) //url为空的时候,显示的图片
                .error(R.mipmap.img_placeholder) //图片加载失败后，显示的图片

            Glide.with(this@AdFg).load(this).apply(options).into(binding!!.iv)
        }

    }


}