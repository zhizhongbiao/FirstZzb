package com.ybs.base.http.download

import androidx.lifecycle.MutableLiveData

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.base.http.download
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/6/1 16:40
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/1 16:40
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
interface ProgressCb<T> {

    fun onStart()

    fun onProgress(progressBean: MutableLiveData<DownloadStateBean>)

    fun onProgress(total:Long,hasRead:Long)

    fun onError(e:Throwable)

    fun onSuccess(data:T)

    fun onSaveFile(data:T)


    fun onSaveFileFinished(fileDir: String, fileName: String)


}