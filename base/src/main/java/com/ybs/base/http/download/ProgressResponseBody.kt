package com.ybs.base.http.download

import okhttp3.ResponseBody
import okio.BufferedSource
import okio.Source
import okio.buffer

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.base.http.download
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/6/1 15:43
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/1 15:43
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class ProgressResponseBody(
    private val origin: ResponseBody,
    val tag: String) : ResponseBody() {

    var bufSource: BufferedSource? = null

    override fun contentLength() = origin.contentLength()

    override fun contentType() = origin.contentType()

    override fun source()=bufSource?:source(origin.source()).buffer().apply {
        bufSource=this
    }

    private fun source(source: Source) = ProgressForwardingSource(this,source,tag)
}