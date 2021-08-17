package com.ybs.ssmaster.protection

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.IBinder
import android.widget.Toast
import com.ybs.base.BaseApp
import com.ybs.base.util.Loger
import com.ybs.ssmaster.IMyAidlInterface
import kotlinx.coroutines.*

class MainService : BaseFgService(), ServiceConnection, IBinder.DeathRecipient {

    override fun onBind(intent: Intent) = MainBinder()

    private val scope by lazy {
        CoroutineScope(Dispatchers.Default)
    }

    override fun onCreate() {
        super.onCreate()
        setupCounting()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Loger.d("onStartCommand")
        bootProtectionService()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun bootProtectionService() {
        Loger.d("bootProtectionService")
        startService(Intent(this, ProtectionService::class.java))
        bindService(Intent(this, ProtectionService::class.java), this, BIND_IMPORTANT)
    }

    private fun setupCounting() {
//        val currentActivity = ActMgr.getInst().currentActivity()
        val topActName = BaseApp.app.getTopActName()?.toString()?:""
//        Loger.d("topActName = $topActName")
//        Loger.d("currentActivity = ${currentActivity?.localClassName}")
        val temp = !topActName.contains("MainAct")
                && !topActName.contains("SplashAct")
                && !topActName.contains("com.qq.e.ads")
        if (temp&&BaseApp.keepAlive) {
            val packageManager = packageManager
            if (checkPackInfo(packageName)) {
                val intent = packageManager.getLaunchIntentForPackage(packageName)
                startActivity(intent)
            } else {
                Toast.makeText(this, "没有安装$packageName", Toast.LENGTH_LONG).show()
            }
        }
        scope.launch {
            delay( 1)
            withContext(Dispatchers.Main) {
                setupCounting()
            }
        }
    }


    override fun onDestroy() {
        scope.cancel("onDestroy")
        super.onDestroy()
    }


    /**
     * 检查包是否存在
     *
     * @param packname
     * @return
     */
    private fun checkPackInfo(packname: String): Boolean {
        var packageInfo: PackageInfo? = null
        try {
            packageInfo = packageManager.getPackageInfo(packname, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return packageInfo != null
    }

    inner class MainBinder : IMyAidlInterface.Stub() {

        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String?
        ) {

        }

        override fun getServiceName() = MainService::class.java.simpleName
    }

    private var bind: IMyAidlInterface? = null

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        Loger.e("onServiceConnected")
        try {
            bind = IMyAidlInterface.Stub.asInterface(service)
            Loger.d("onServiceConnected  name = ${bind?.serviceName}")
            bind?.asBinder()?.linkToDeath(this, 0)
        } catch (e: Throwable) {
            Loger.d("e = " + e.message)
        }
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        Loger.d("onServiceDisconnected  ComponentName = ${name?.className}")
    }

    override fun binderDied() {
        Loger.e("binderDied")
        bind?.asBinder()?.unlinkToDeath(this, 0)
        bind = null
        bootProtectionService()
    }


}