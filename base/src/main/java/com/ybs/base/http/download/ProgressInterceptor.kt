package com.ybs.base.http.download

import com.ybs.base.http.RetrofitManger
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

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


class ProgressInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain) = with(chain) {
        val oldRequest = chain.request()
        val url = oldRequest.url.toString()
        val oldRespo = chain.proceed(oldRequest)
        if (oldRequest.header("download")=="true") {
            RetrofitManger.logger.level=HttpLoggingInterceptor.Level.HEADERS
            oldRespo
                .newBuilder()
                .body(ProgressResponseBody(oldRespo.body!!,url))
                .build()
        } else {
            RetrofitManger.logger.level=HttpLoggingInterceptor.Level.BODY
            oldRespo
        }
    }

}