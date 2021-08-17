package com.ybs.reslib.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

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
class MyIv : ImageView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

//    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//        Log.e("dispatchTouchEvent", "dispatchTouchEvent: ${ev?.action}" )
//       when(ev?.action)
//       {
//           MotionEvent.ACTION_DOWN ->alpha=0.6f
//           MotionEvent.ACTION_CANCEL,
//           MotionEvent.ACTION_UP ->alpha=1f
//       }
//        return super.dispatchTouchEvent(ev)
//    }


}