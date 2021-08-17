package com.ybs.ssmaster.protection

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.os.Build
import com.ybs.base.util.Loger
import com.ybs.ssmaster.R
import java.util.concurrent.atomic.AtomicInteger

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.ssmaster.protection
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/6/7 17:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/7 17:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
abstract class BaseFgService :Service() {


    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }


     open fun createNotificationChannel() {
        val mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        // 通知渠道的id
        val id = "my_channel_"+this.javaClass.canonicalName
        // 用户可以看到的通知渠道的名字.
        val name: CharSequence = getString(R.string.app_name)
        //         用户可以看到的通知渠道的描述
        val description = "BaseFgService"+this.javaClass.canonicalName
        val importance = NotificationManager.IMPORTANCE_HIGH
        var mChannel: NotificationChannel? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = NotificationChannel(id, name, importance)
            //         配置通知渠道的属性
            mChannel.description = description
            //         设置通知出现时的闪灯（如果 android 设备支持的话）
            mChannel.enableLights(true)
            mChannel.lightColor = Color.RED
            //         设置通知出现时的震动（如果 android 设备支持的话）
            mChannel.enableVibration(true)
            mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            //         最后在notificationmanager中创建该通知渠道 //
            mNotificationManager.createNotificationChannel(mChannel)
        }


        // 为该通知设置一个id
        val notifyID = getId()
        // 通知渠道的id
        val CHANNEL_ID =id
        // Create a notification and set the notification channel.
        val b = Notification.Builder(this)
            .setContentTitle(description).setContentText(description)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            b.setChannelId(CHANNEL_ID)
        }
        val notification = b.build()
        startForeground(notifyID, notification)
    }


    protected open fun getId()= (idCounter.incrementAndGet()).apply {
        Loger.d("name = ${this@BaseFgService.javaClass.canonicalName}  serviceCounter =$this")

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int) = START_STICKY

}

private  var idCounter:AtomicInteger= AtomicInteger(0)