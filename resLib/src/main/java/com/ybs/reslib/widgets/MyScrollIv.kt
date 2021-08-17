package com.ybs.reslib.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ImageView
import com.ybs.reslib.R

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.reslib.widgets
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/29 16:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/29 16:18
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class MyScrollIv : ImageView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> calculatePos(ev)
            MotionEvent.ACTION_CANCEL,
            MotionEvent.ACTION_UP -> setImageResource(R.mipmap.img_glide2)
        }
        return super.dispatchTouchEvent(ev)
    }

    var goDown =true

    private fun calculatePos(ev: MotionEvent?) = ev?.apply {
        goDown=y>331
      if (goDown) setImageResource(R.mipmap.img_glide3) else setImageResource(R.mipmap.img_glide1)
    }
}