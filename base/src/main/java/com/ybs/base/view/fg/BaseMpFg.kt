package com.ybs.base.view.fg

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding

/**
 *
 * @ProjectName: DistributeNetTool
 * @Package: com.ybs.distributenettool.box
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/14 10:03
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/14 10:03
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
abstract class BaseMpFg<B : ViewBinding, A : FragmentActivity> : BaseDialogActFg<B,A>(), MediaPlayer.OnPreparedListener,
    MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {

    abstract fun getVv(): VideoView

    abstract fun getMediaResId(): Int

    override fun initView(args: Bundle?) {
        getVv().apply {
            setOnPreparedListener(this@BaseMpFg)
            setOnCompletionListener(this@BaseMpFg)
            setOnErrorListener(this@BaseMpFg)
        }.setOnClickListener {
            dismiss()
        }
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp?.setOnInfoListener { mp, what, extra ->
            if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
//                getVv().setBackgroundColor(Color.TRANSPARENT) // 解决短暂黑屏问题
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    getVv().foreground = ColorDrawable(Color.TRANSPARENT)
                }
            }
            true
        }
    }

    override fun onCompletion(mp: MediaPlayer?) {
        mp!!.start()
        mp.isLooping = true
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int) = true.apply {
        getVv().stopPlayback()
    }

    override fun onResume() {
        super.onResume()
        getVv().apply {
            if (!isPlaying) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    getVv().foreground = ColorDrawable(Color.WHITE)
                }
//                setBackgroundColor(Color.WHITE)
                setVideoURI(Uri.parse("android.resource://" + requireActivity().packageName.toString() + "/" + getMediaResId()))
                start()
            }
        }
    }

    override fun onPause() {
        getVv().apply {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                getVv().foreground = ColorDrawable(Color.WHITE)
            }
//            setBackgroundColor(Color.WHITE)
            if (isPlaying) {
                pause()
            }
        }
        super.onPause()
    }

    override fun onDestroyView() {
        getVv().stopPlayback()
        super.onDestroyView()
    }

}