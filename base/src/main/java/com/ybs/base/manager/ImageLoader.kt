package com.ybs.base.manager

import android.app.Activity
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.base.manager
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/26 20:45
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/26 20:45
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */


fun loadImg(url: String, iv: ImageView, host: Fragment, holder: Int) {

    val options: RequestOptions = RequestOptions()
        .placeholder(holder) //图片加载出来前，显示的图片
        .fallback(holder) //url为空的时候,显示的图片
        .error(holder) //图片加载失败后，显示的图片

    Glide.with(host).load(url).apply(options).into(iv)
}

fun loadImg(url: String, iv: ImageView, host: Activity, holder: Int) {

    val options: RequestOptions = RequestOptions()
        .placeholder(holder) //图片加载出来前，显示的图片
        .fallback(holder) //url为空的时候,显示的图片
        .error(holder) //图片加载失败后，显示的图片

    Glide.with(host).load(url).apply(options).into(iv)
}

fun loadImg(url: String, iv: ImageView, host: Activity, options: RequestOptions) {

    Glide.with(host).load(url).apply(options).into(iv)
}

fun loadImg(url: String, iv: ImageView, host: Fragment, options: RequestOptions) {

    Glide.with(host).load(url).apply(options).into(iv)
}

