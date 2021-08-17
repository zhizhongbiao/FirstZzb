package com.ybs.ssmaster.ui.main.recommend

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_VERTICAL
import com.ybs.base.base.http.response.Status
import com.ybs.base.data.bean.QueryCarouselResp
import com.ybs.reslib.databinding.FgVpBinding
import com.ybs.ssmaster.ui.common.AppBaseFg
import com.ybs.ssmaster.ui.common.TaskHelper
import com.ybs.ssmaster.ui.main.MainAct
import com.ybs.ssmaster.ui.main.MainVm
import e

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
class VpFg : AppBaseFg<FgVpBinding, MainVm, MainAct>() {

    override fun getVmClz() = MainVm::class.java

    companion object {
        fun newInst() = VpFg()
    }

    private val fgs = mutableListOf<Fragment>()


    private val th by lazy {
        TaskHelper()
    }

    private val adapt by lazy {
        MyAdapter(getAct(), fgs)
    }

    private var counter = 0

    override fun getLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FgVpBinding.inflate(inflater, container, false)

    override fun initView() {

        fgs.add(RecommendFg.newInst())

        binding!!.vp.apply {
            orientation = ORIENTATION_VERTICAL
            adapter = adapt
        }

        vm.queryAdvertisement().observe(this, {
            when (it.status) {
                Status.SUCCESS -> onAds(it.data)
            }
        })


        binding?.fl?.eventCb = {
//            d("event?.action = ${it?.action}")
            when (it?.action) {
                MotionEvent.ACTION_DOWN -> cancelCircle()
                MotionEvent.ACTION_UP -> doCircle()
            }
        }


        binding?.vp?.registerOnPageChangeCallback(vpCb)

    }

    private val vpCb by lazy {
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
//                    0 -> vm.startTts(TtsUtil.getTts("请用微信扫码下单吧 | $SCAN_TTS"))
//                    fgs.size - 1 -> vm.startTts(TtsUtil.getGuideLineTts(FeatureRequestConstants.SPECIAL_DISH))
                }
            }
        }
    }

    override fun onDestroyView() {
        binding?.vp?.unregisterOnPageChangeCallback(vpCb)
        super.onDestroyView()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) cancelCircle() else doCircle()
    }

    fun cancelCircle() {
        th.cancelJob()
    }

    fun doCircle() {
        if (isHidden) {
            cancelCircle()
            return
        }
        th.runCircle(scope = lifecycleScope) {
            binding?.vp?.apply {
                ++counter
                setCurrentItem(counter % fgs.size, true)
            }
        }
    }

    private fun onAds(data: MutableList<QueryCarouselResp>?) {
        e("size ${data?.size}")
        data?.forEach {

            if (it?.isActive!=true) return@forEach

            val adFg = AdFg.newInst(it.carouselImage)
            if (fgs.size > 1)
                fgs.add(1, adFg)
            else
                fgs.add(adFg)
        }
        adapt.notifyDataSetChanged()
    }

    fun toRecommendFg() {
        doCircle()
        binding?.vp?.setCurrentItem(fgs.size - 1, true)
    }

    fun toEntryQRCOdeFg(tts: String) {
        doCircle()
        binding?.vp?.setCurrentItem(0, true)
//        vm.startTts(tts)
    }
}

const val SCAN_TTS = "还在等什么，亲！赶紧扫码下单吧"