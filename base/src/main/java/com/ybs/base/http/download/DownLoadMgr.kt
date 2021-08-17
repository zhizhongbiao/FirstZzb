package com.ybs.base.http.download

import androidx.annotation.UiThread
import androidx.lifecycle.MutableLiveData
import com.ybs.base.http.RetrofitManger
import com.ybs.base.util.Loger
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Streaming
import retrofit2.http.Url

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.base.http.download
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/6/1 16:30
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/1 16:30
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */


class DownLoadMgr private constructor() : Callback<ResponseBody> {

    companion object {
        val inst = DownLoadMgr()
        val progressState = MutableLiveData<DownloadStateBean>()
    }

    private var progressCb: ProgressCb<ResponseBody>? = null

    @UiThread
    fun loadFile(url: String, cb: ProgressCb<ResponseBody>) {
        progressCb = cb
        progressCb?.onStart()
        progressCb?.onProgress(progressState)
        RetrofitManger
            .inst
            .createService(DownLoadApi::class.java)
            .download(url).enqueue(this)
    }



    interface DownLoadApi {
        @Streaming
        @Headers("download:true")
        @GET
        fun download(@Url url: String): Call<ResponseBody>
    }

    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
        if (response.isSuccessful) {
            val body = response.body()
            progressCb?.onSuccess(body!!)
            progressCb?.onSaveFile(body!!)
        } else {
            progressCb?.onError(Throwable("code = ${response.code()}   message = ${response.message()}"))
        }
    }

    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        progressCb?.onError(t)
        Loger.e(t.message)
    }
}