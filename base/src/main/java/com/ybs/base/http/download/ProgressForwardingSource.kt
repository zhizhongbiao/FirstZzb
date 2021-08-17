package com.ybs.base.http.download

import okio.Buffer
import okio.ForwardingSource
import okio.Source
import java.io.IOException

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.base.http.download
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/6/1 16:00
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/1 16:00
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class ProgressForwardingSource(private val origin: ProgressResponseBody, delegate: Source, val tag: String) :
    ForwardingSource(delegate) {

    private var bytesRead: Long = 0

    @Throws(IOException::class)
    override fun read(sink: Buffer, byteCount: Long) = super.read(sink, byteCount).apply {
        bytesRead += if (this == -1L) 0 else this
        DownLoadMgr.progressState.postValue(
            DownloadStateBean(
                origin.contentLength(),
                bytesRead,
                tag
            )
        )
    }
}