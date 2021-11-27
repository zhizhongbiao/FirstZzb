package com.ybs.base.http.request

import com.blankj.utilcode.util.ToastUtils
import com.ybs.base.data.AppDataManager
import com.ybs.base.util.Loger
import d
import e
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.nio.charset.Charset

/**
 *
 * @ProjectName: RetrieveIdTool
 * @Package: com.ybs.base.base.http
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/20 17:53
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/20 17:53
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */


const val TAG_GO_TO_LOGIN="tagToLogin"


class AppInterceptor : Interceptor {

    companion object{
        var onTokenExpire:(()->Unit)?=null
    }

    var accessToken: String? = ""
    override fun intercept(chain: Interceptor.Chain) = with(chain) {

//        if (TextUtils.isEmpty(accessToken)) {
        accessToken = AppDataManager.inst.token
//        }

        Loger.e("OkHttpClient  accessToken = $accessToken")

        val oldRequest = chain.request()

        val response = when {
            oldRequest.header("download") == "true" -> {
                chain.proceed(oldRequest)
            }
            oldRequest.header("download") == "false" -> {
                chain.proceed(oldRequest)
            }
            else -> {
                val newRequest = oldRequest
                    .newBuilder()
                    .header(
                        "Content-Type", "application/json"
                    )
                    .addHeader("charset", "UTF-8")
//                    .header(
//                        "Authorization",
//                        accessToken ?: "token is null"
//                    )
                    .build()

                chain.proceed(newRequest)
            }
        }




        resolveResponse(response)


    }

    private fun addQueryParam(oldRequest: Request): Request {
        val stringBuilder = StringBuilder(oldRequest.url.toString())
        //拼接公共请求参数
        for ((key, value) in getParams()) {
            stringBuilder.append("&$key=$value")
        }
        var url = stringBuilder.toString()
        //如果之前的url没有？号，我们需要手动给他添加一个？号
        if (!url.contains("?")) {
            url = url.replaceFirst("&", "?")
        }
        return oldRequest.newBuilder()
            .header("Content-Type", "application/json")
            .url(url)
            .build()
    }

    private fun getParams() = params

    private val params = mutableMapOf<String, String>()

    private fun resolveResponse(response: Response) = with(response) {
        e("response.code = " + response.code)
        try {
            val responseBody = response.body
            val source = responseBody!!.source()
            source.request(java.lang.Long.MAX_VALUE)
            val buffer = source.buffer()
            var charset: Charset? = Charsets.UTF_8
            val contentType = responseBody.contentType()
            contentType?.let {
                charset = contentType.charset(charset)
            }
            val rBody = buffer.clone().readString(charset!!).trim()
//            rBody.takeIf { it.contains("401") ||it.contains("认证失败，无法访问系统资源")}?.apply {
            rBody.takeIf { it.contains("\"code\":401")}?.apply {
                e(" BusUtils.post(TAG_GO_TO_LOGIN) ")
              onTokenExpire?.invoke()
            }
            d(" response.url =" + response.request.url)
            d(" response.data before - - - - -  $rBody")

        } catch (ex: Exception) {
            e("resolveResponse: ex = " + ex.message)
            ToastUtils.showLong(ex.message)
        }
        response
    }

}