package com.ybs.reslib.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout

/**
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.base.view.widget
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/28 9:00
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/28 9:00
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class MyFl : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)



    var eventCb:((ev: MotionEvent?)->Unit)?=null


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        eventCb?.invoke(ev)
        return super.dispatchTouchEvent(ev)
    }
}