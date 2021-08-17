package com.ybs.base.view.act

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.ToastUtils
import com.ybs.base.BaseApp
import com.ybs.base.data.AppDataManager
import com.ybs.base.manager.NetChangedReceiver
import com.ybs.base.util.Loger.d
import com.ybs.base.view.tips.TipsFg
import pub.devrel.easypermissions.EasyPermissions

/**
 *
 * @ProjectName: DistributeNetTool
 * @Package: com.ybs.distributenettool
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/13 17:44
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/13 17:44
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

abstract class BaseAct<B : ViewBinding> : AppCompatActivity(), EasyPermissions.PermissionCallbacks,
    NetChangedReceiver.NetStateChangeObserver {

    protected var hasToRequestPermission: Boolean = true
    protected lateinit var binding: B

    abstract fun getLayoutBinding(): B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullsceen()
        binding = getLayoutBinding()
        setContentView(binding.root)
        setupVm()
        initView()
        if (hasToRequestPermission) requestPermission()

        NetChangedReceiver.registerReceiver(this)
        NetChangedReceiver.registerObserver(this)

        if (needKeepScreenOn) {
            keepScreenOn()
        }
    }

    protected open var needKeepScreenOn = true

    private fun keepScreenOn() {
        window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    protected open fun setupVm() {

    }

    private fun setFullsceen() {
        if (isFullScreen()) {
            this.requestWindowFeature(Window.FEATURE_NO_TITLE)
            this.window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
            val decorView: View = window.decorView
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }

    protected fun requestPermission() {

        if (getPermissionArray().isEmpty()) return

        if (EasyPermissions.hasPermissions(this, *getPermissionArray())) {
            onHadPermission()
        } else {
            EasyPermissions.requestPermissions(
                this, "本应用需要所有这些权限，缺一不可！",
                getRequestPermissionCode(),
                *getPermissionArray()
            )
        }
    }

    protected val defaultCode = 999

    open fun getRequestPermissionCode() = defaultCode

    protected open fun onHadPermission() {
        d("onHadPermission ")
    }

    protected open fun getPermissionArray(): Array<String> = emptyArray()

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        d("requestCode = $requestCode")
        ToastUtils.showLong("权限已授予！")
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        d("requestCode = $requestCode")
        ToastUtils.showLong("权限已拒绝！")
    }

    abstract fun initView()

    protected open fun isFullScreen() = true


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }


    fun getActTag() = this.javaClass.simpleName + this.toString()

    override fun onBackPressed() {
        return
    }


    override fun onDestroy() {
        NetChangedReceiver.unRegisterReceiver(this)
        NetChangedReceiver.unRegisterObserver(this)
        super.onDestroy()
    }

    var showNoNetwork = false

    override fun onDisconnect() {
        if (showNoNetwork) {
            tipsAndExit("无网络连接！请联系工作人员连接网络并重启！")
        } else {
            ToastUtils.showLong("无网络连接！请联系工作人员检查连接网络!")
        }
    }

    override fun onMobileConnect() {
        tipsFg.dismiss()
    }

    override fun onWifiConnect() {
        tipsFg.dismiss()
    }

    private val tipsFg = TipsFg()

    protected open fun tipsAndExit(msg: String) {
        (tipsFg.setMsg(
            msg
                    + "\n SN:${AppDataManager.inst.token}"
                    + "\nMAC:${AppDataManager.inst.deviceMac}"
        )
            .showDlg(supportFragmentManager) as TipsFg).onSureClickCb = {
            BaseApp.exitApp()
        }
    }

    protected open fun closeTips() {
        tipsFg.dismiss()
    }
}