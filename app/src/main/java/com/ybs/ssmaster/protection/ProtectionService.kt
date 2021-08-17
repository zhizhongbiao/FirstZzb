package com.ybs.ssmaster.protection

import android.content.*
import android.os.IBinder
import com.ybs.base.util.Loger
import com.ybs.ssmaster.IMyAidlInterface

class ProtectionService : BaseFgService(), ServiceConnection, IBinder.DeathRecipient {

    override fun onBind(intent: Intent) = ProtectionBinder()

    inner class ProtectionBinder : IMyAidlInterface.Stub() {
        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String?
        ) {
        }

        override fun getServiceName()=ProtectionService::class.java.simpleName
    }

    private val receiver by lazy {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Loger.e("${intent?.action}")
                intent?.action?.apply {
                    if (this.contains("FUCK")) {
//                        Process.killProcess(Process.myPid())
//                        exitProcess(0)
                    }
                }
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        registerReceiver(receiver,IntentFilter().apply {
            addAction("FUCK")
        })
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        bindService(Intent(this, MainService::class.java), this, BIND_IMPORTANT)
        Loger.d("bind MainService")
        return super.onStartCommand(intent, flags, startId)
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
        Loger.d("binderDied")
        bind?.asBinder()?.unlinkToDeath(this, 0)
        bind = null
        bootMainService()
    }

    private fun bootMainService() {
        Loger.e("bootMainService")
        startService(Intent(this, MainService::class.java))
        bindService(Intent(this, MainService::class.java), this, BIND_IMPORTANT)
    }

    override fun getId()=10
}