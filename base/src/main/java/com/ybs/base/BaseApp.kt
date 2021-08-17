package com.ybs.base

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.*
import android.widget.Toast
import com.blankj.utilcode.util.FileIOUtils
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.ToastUtils
import com.ybs.base.manager.ActMgr
import com.ybs.base.manager.TaskManger
import com.ybs.base.util.Loger
import d
import java.io.File
import java.util.*
import kotlin.system.exitProcess


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


open class BaseApp : Application(), Thread.UncaughtExceptionHandler {

    private lateinit var logPath: String

    companion object {
        var keepAlive = false
        lateinit var app: BaseApp

        fun exitApp() {
            keepAlive = false
            ActMgr.getInst().AppExit()
        }
    }

    override fun onCreate() {
        super.onCreate()
        if (isMainProcess(this)) {
            keepProcessAlive()
            initLogPath()
            app = this
            //捕抓线程异常，能收到异常日志，不能让发生异常的线程存活
            Thread.setDefaultUncaughtExceptionHandler(this)
            registerActivityLifecycleCallbacks(ActCbImp())

//        if (BuildConfig.DEBUG) {
//            //初始化阿,必须要在ARouter.init()之前配置这两个方法才有效
//            ARouter.openLog() // 打印日志
//            ARouter.openDebug() // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
//        }
//        ARouter.init(this)
        }
        d("Application init     ARouter.init  ")
    }

    private fun initLogPath() = TaskManger.inst.execSerial {
        logPath = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)!!
            .absolutePath.toString() + File.separator + "ybsCrashedLog.txt"
        Loger.d("logPath = $logPath")
        if (FileUtils.isFileExists(logPath)) return@execSerial
        FileUtils.createOrExistsFile(logPath)
    }


    override fun uncaughtException(t: Thread, e: Throwable) {
        val msg = Date().toString() + " - - - > " + t.name + " ：：： " + e.message + "\n"
        Loger.e("expLog = $msg")
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
        TaskManger.inst.execSerial {
            FileIOUtils.writeFileFromString(logPath, msg, true)
        }
//        ToastUtils.showLong(e.message)
        e.printStackTrace()
    }

    open fun killCurrentProcess() {
        Process.killProcess(Process.myPid())
        exitProcess(10)
    }


    private fun keepProcessAlive() {
//        if (BuildConfig.DEBUG) return
//        只是捕抓主线程日志，并保持主线程存活
        Handler(Looper.getMainLooper()).post {
            while (true) {
                try {
                    Looper.loop()
                } catch (e: Throwable) {
                    val message = e.message
                    Loger.e("   keepProcessAlive  = $message")
                    uncaughtException(Thread.currentThread(), e)
                    dealWithMsg(message)
                }
            }
        }
    }

    private fun dealWithMsg(message: String?) = message?.apply {
        val currentActivity = ActMgr.getInst().currentActivity()
        if (!contains(currentActivity?.localClassName ?: "")) return@apply
        currentActivity?.finish()
        ToastUtils.showLong("出现异常，请稍后重试！ $message")
    }


    fun getTopActName() = ((getSystemService(ACTIVITY_SERVICE) as ActivityManager)
        .getRunningTasks(1)[0].topActivity)?.apply {
//        Loger.d("pkg:$packageName") //包名
//        Loger.d("cls:$className") //包名加类名
    }

    protected fun getCurrentProcessName(context: Context): String? {
        val pid = Process.myPid()
        var processName = ""
        val manager =
            context.applicationContext.getSystemService(ACTIVITY_SERVICE) as ActivityManager
        for (process in manager.runningAppProcesses) {
            if (process.pid === pid) {
                processName = process.processName
            }
        }
        return processName
    }

    protected fun isMainProcess(context: Context): Boolean {
        return context.applicationContext.packageName == getCurrentProcessName(context)
    }

}