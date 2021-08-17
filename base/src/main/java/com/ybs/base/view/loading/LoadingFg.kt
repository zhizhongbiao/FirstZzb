package com.ybs.base.view.loading

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.lifecycle.lifecycleScope
import com.ybs.base.R
import com.ybs.base.databinding.DialogLoadingBinding
import com.ybs.base.view.fg.BaseDialogFg
import e
import kotlinx.coroutines.*

/**
 *
 * @ProjectName: DistributeNetTool
 * @Package: com.ybs.distributenettool.box
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/14 19:03
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/14 19:03
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */


const val DLG_LOAD_LOADING = 555
const val DLG_LOAD_SUCCEEDED = 666
const val DLG_LOAD_FAILED = 444

class LoadingFg : BaseDialogFg<DialogLoadingBinding>() {


    lateinit var rotationAnim: ObjectAnimator
    private var job: Job? = null

    override fun getLayoutBinding(inflater: LayoutInflater, container: ViewGroup?) =
        DialogLoadingBinding.inflate(inflater, container, false)

    override fun initView(args: Bundle?) {

    }

    override fun onResume() {
        super.onResume()
        rotationAnim = ObjectAnimator.ofFloat(binding!!.ivState, "Rotation", 0f, 360f * 50).apply {
            setAutoCancel(false)
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
            duration = 1000L * 40
        }
        updateUIState()
    }

    private var mState = DlgStateBean()


    fun setUIState(state: DlgStateBean) = this.apply {
        mState = state
        onDismissedCb = state.onDismissedCb
        if (isResumed) {
            updateUIState()
        }
    }

    private fun updateUIState() = this.apply {
        e("mState = $mState")

        if (mState.state != DLG_LOAD_LOADING) {

            if (rotationAnim.isRunning) {
                rotationAnim.cancel()
            }

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                rotationAnim.setCurrentFraction(0F)
            }

            job = lifecycleScope.launch(Dispatchers.Main) {
                delayTimeInSecond(1500)
                dismiss()
            }
        }

        when (mState.state) {
            DLG_LOAD_LOADING -> {
                job?.cancel("new state here")
                binding!!.tvState.text = mState.msg ?: "正在配置网络..请稍后"
                binding!!.ivState.setImageResource(R.mipmap.icon_loading)
                if (!rotationAnim.isRunning) rotationAnim.start()
            }
            DLG_LOAD_SUCCEEDED -> {
                binding!!.tvState.text = mState.msg ?: "配置网络成功！"
                binding!!.ivState.setImageResource(R.mipmap.icon_successbig)
            }
            else -> {
                binding!!.tvState.text = mState.msg ?: "配置网络失败！"
                binding!!.ivState.setImageResource(R.mipmap.icon_failbig)
            }
        }
    }


    private suspend fun delayTimeInSecond(s: Long) = withContext(Dispatchers.IO) {
        delay(s)
    }


    override fun onDismiss(dialog: DialogInterface) {
        rotationAnim.cancel()
        job?.cancel("onDismiss")
        job = null
        super.onDismiss(dialog)
    }


}