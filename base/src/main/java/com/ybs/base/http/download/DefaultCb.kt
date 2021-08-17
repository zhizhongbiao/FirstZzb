package com.ybs.base.http.download

import android.os.Handler
import android.os.Looper
import com.blankj.utilcode.util.FileUtils
import com.ybs.base.manager.TaskManger
import com.ybs.base.util.Loger
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.base.http.download
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/6/1 16:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/1 16:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
abstract class DefaultCb(private val fileDir: String, private val fileName: String) :
    ProgressCb<ResponseBody> {

    private val handler = Handler(Looper.getMainLooper())

    override fun onSaveFile(data: ResponseBody) = TaskManger.inst.execInBg {
        saveFile(data)
    }

    protected open fun saveFile(body: ResponseBody) {

        FileUtils.deleteAllInDir(fileDir)

        var ins: InputStream? = null
        val buf = ByteArray(2048)
        var len: Int
        var fos: FileOutputStream? = null

        var hasWritingFinished = false

        try {
            val contentLength = body.contentLength()
            var hasRead = 0L
            ins = body.byteStream()
            val dir: File = File(fileDir)
            if (!dir.exists()) {
                dir.mkdirs()
            }
            val file = File(dir, fileName)
            fos = FileOutputStream(file)
            while (ins.read(buf).also { len = it } != -1) {
                fos.write(buf, 0, len)
                hasRead += len
//                handler.post {
//                    onProgress(contentLength,hasRead)
//                }
            }
            fos.flush()
            hasWritingFinished = true
        } catch (e: Exception) {
            handler.post {
                onError(e)
                Loger.e("saveFile", e.message)
                e.printStackTrace()
            }
        } finally {
            try {
                ins?.close()
                fos?.close()
                handler.post {
                    onSaveFileFinished(fileDir, fileName)
                }
            } catch (e: IOException) {
                handler.post {
                    onError(e)
                    Loger.e("saveFile", e.message)
                    e.printStackTrace()
                }
            }
        }
    }

}