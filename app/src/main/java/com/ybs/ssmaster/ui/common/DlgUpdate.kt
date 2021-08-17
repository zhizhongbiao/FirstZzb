package com.ybs.ssmaster.ui.common

import android.os.Bundle
import android.os.Environment
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ToastUtils
import com.ybs.base.BaseApp
import com.ybs.base.data.bean.UpgradeResp
import com.ybs.base.http.download.DefaultCb
import com.ybs.base.http.download.DownLoadMgr
import com.ybs.base.http.download.DownloadStateBean
import com.ybs.base.util.Loger
import com.ybs.base.view.fg.BaseDialogFg
import com.ybs.reslib.databinding.DlgUpdateBinding
import com.ybs.ssmaster.ui.splash.upgrade.CheckVersionManager
import okhttp3.ResponseBody

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.ssmaster.ui.common
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/6/4 17:37
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/4 17:37
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

const val KEY_UPDATE = "keyUpdate"

class DlgUpdate : BaseDialogFg<DlgUpdateBinding>() {

    companion object {
        fun newInst(data: Parcelable) = DlgUpdate().apply {
            arguments = bundleOf(
                KEY_UPDATE to data
            )
        }
    }

    override fun getLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = DlgUpdateBinding.inflate(inflater, container, false)

    private lateinit var d: UpgradeResp

    override fun initView(args: Bundle?) {
        args?.apply {
            getParcelable<UpgradeResp>(KEY_UPDATE)?.let {
                binding?.tvVer?.text = it.versionName
                binding?.tvContent?.text = it.versionDescription
//                binding?.btnCancel?.isEnabled = it?.isEnforce == 0
                d = it
            }
        }

        binding?.btnCancel?.setOnClickListener {
            dismiss()
        }

        binding?.tvFindVer?.setOnLongClickListener {
            dismiss()
            return@setOnLongClickListener true
        }

        binding?.btnSure?.setOnClickListener {
            onSureClick()
        }
    }

    private fun onSureClick() {
        binding?.btnSure?.visibility = View.GONE
        binding?.btnCancel?.visibility = View.GONE

        binding?.rpb?.visibility = View.VISIBLE
        binding?.tvLoadingTitle?.visibility = View.VISIBLE
        binding?.tvLoadingProgress?.visibility = View.VISIBLE

        val destFileDir: String =
            BaseApp.app.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "/"
        val destFileName: String = d.versionName //文件路径

        DownLoadMgr.inst.loadFile(
            d.updatePackageUrl, object : DefaultCb(destFileDir, destFileName) {

                override fun onSaveFileFinished(fileDir: String, fileName: String) {
                    Loger.e("onSaveFileFinished fileDir =$fileDir    fileName = $fileName")
                    //                        dismiss()
                    CheckVersionManager.installAPK(BaseApp.app, destFileDir + destFileName)
                }

                override fun onProgress(total: Long, hasRead: Long) {

                }

                override fun onStart() {
                    Loger.e("onStart")
                }

                override fun onProgress(progressBean: MutableLiveData<DownloadStateBean>) {
                    progressBean.observe(activity!!, { downloadStateBean: DownloadStateBean? ->
                        if (downloadStateBean == null) {
                            Loger.e("downloadStateBean == null")
                            return@observe
                        }
                        val byteLoaded = downloadStateBean.byteLoaded.toFloat()
                        val totalLength = downloadStateBean.totalLength.toFloat()

                        if (byteLoaded.toInt() % 444 == 0) {
                            Loger.e(downloadStateBean.toString())
                        }
                        val p = (byteLoaded / totalLength * 100).toInt()
                        binding?.tvLoadingProgress?.text = "$p%"
                        binding?.rpb?.setProgress(p)
                    })
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    Loger.e("Throwable  = " + e.message)
                    ToastUtils.showShort("文件下载失败！exp = ${e.message}")
                    binding?.rpb?.visibility = View.GONE
                    binding?.tvLoadingTitle?.visibility = View.GONE
                    binding?.tvLoadingProgress?.visibility = View.GONE
                    dismiss()
                }

                override fun onSuccess(data: ResponseBody) {
                    Loger.e("onSuccess  ")
                }
            })
    }
}