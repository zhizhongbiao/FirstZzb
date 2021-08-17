package com.ybs.base.ble

import android.app.Service
import android.bluetooth.*
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Binder
import android.text.TextUtils
import com.ybs.base.BaseApp

import d
import e
import kotlinx.coroutines.*
import v
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import kotlin.collections.HashMap

/**
 *
 * @ProjectName:    AylsonHome
 * @Package:        com.aylson.app.ui5.treasure.base
 * @ClassName:      BtService
 * @Description:
 * @Author:         TaxiDriverSantos
 * @CreateDate:     2020/7/25   15:39
 * @UpdateUser:     TaxiDriverSantos
 * @UpdateDate:     2020/7/25   15:39
 * @UpdateRemark:
 * @Version:        1.0
 */


const val STATE_SCAN_START = 999
const val STATE_SCAN_SUCCEEDED = 888
const val STATE_SCAN_FAILED = 777
const val STATE_SCAN_FINISHED = 666
const val STATE_SCAN_TIMEOUT = 555


const val STATE_CONN_START = 456654
const val STATE_CONN_FAILED = 789987


val UUID_SERVICE: UUID = UUID.fromString("0000180A-0000-1000-8000-00805F9B34FB")
val UUID_CHAR: UUID = UUID.fromString("00009999-0000-1000-8000-00805F9B34FB")
val UUID_DESC: UUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb")

open class BtService : Service(), BtFun {

    protected val mRequestQueue: GattUtils.RequestQueue = GattUtils.createRequestQueue()

    var isBtScanning = false

    val connectedList = HashMap<String, BluetoothGatt>()

    companion object {
        fun getIntent(ctx: Context) = Intent(ctx, BtService::class.java)
    }

    val coroutineScope by lazy {
        CoroutineScope(Dispatchers.Main)
    }

    protected val wt: ExecutorService by lazy {
        Executors.newSingleThreadExecutor().apply { d("newSingleThreadContext = $this") }
    }


//    private val btMgr by lazy {
//        getSystemService<BluetoothManager>(BluetoothManager::class.java)
//    }

    private val gattCallback by lazy {
        object : BluetoothGattCallback() {
            override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
                onBtConnStateChanged(status, newState, gatt)
            }

            override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
                onBtServiceDiscovered(status, gatt)
            }

            override fun onCharacteristicRead(
                gatt: BluetoothGatt?,
                characteristic: BluetoothGattCharacteristic?,
                status: Int
            ) {
                onCharRead(characteristic, status, gatt)
            }

            override fun onCharacteristicWrite(
                gatt: BluetoothGatt?,
                characteristic: BluetoothGattCharacteristic?,
                status: Int
            ) {
                onCharWrite(characteristic, status, gatt)
            }

            override fun onCharacteristicChanged(
                gatt: BluetoothGatt?,
                characteristic: BluetoothGattCharacteristic?
            ) {

                val uuid = characteristic?.uuid
                val originalStr = characteristic?.getStringValue(0)
                e(
                    "onCharacteristicChanged:  Thread = ${Thread.currentThread().name} "
                            + "\n  originalStr = " + originalStr
                            + "\n  name =" + gatt?.device?.name + ",   addr = " + gatt?.device?.address
                            + "\n  uuid = " + uuid
                )

                originalStr?.apply {
                    val originalStrArray = toByteArray(Charsets.UTF_8)
                    onCharChanged(originalStr, originalStrArray, characteristic, gatt)
                }


            }

            override fun onDescriptorRead(
                gatt: BluetoothGatt?,
                descriptor: BluetoothGattDescriptor?,
                status: Int
            ) {
                onDescRead(descriptor, status, gatt)
            }

            override fun onDescriptorWrite(
                gatt: BluetoothGatt?,
                descriptor: BluetoothGattDescriptor?,
                status: Int
            ) {
                onDescWrite(descriptor, status, gatt)
            }


            override fun onMtuChanged(gatt: BluetoothGatt?, mtu: Int, status: Int) {
                onMtuSizeChanged(gatt, mtu, status)
            }

        }
    }

    protected open fun onCharChanged(
        originalStr: String,
        originalStrArray: ByteArray,
        characteristic: BluetoothGattCharacteristic,
        gatt: BluetoothGatt?
    ) {

    }

    open fun onMtuSizeChanged(gatt: BluetoothGatt?, mtu: Int, status: Int) {
        d("onMtuChanged MtuSize = $mtu; status = $status")
    }

    open fun onDescWrite(
        descriptor: BluetoothGattDescriptor?,
        status: Int,
        gatt: BluetoothGatt?
    ) {
        val uuid = descriptor?.uuid
        d(
            "onDescriptorWrite   status = $status  " +
                    " uuid = $uuid   " +
                    "name = ${gatt?.device?.name}  " +
                    "addr = ${gatt?.device?.address}   " +
                    "valueStr = ${String(descriptor?.value ?: byteArrayOf())}  "
        )
        nextTask()
    }

    open fun onDescRead(
        descriptor: BluetoothGattDescriptor?,
        status: Int,
        gatt: BluetoothGatt?
    ) {
        val uuid = descriptor?.uuid
        d(
            "onDescriptorRead   status = $status  " +
                    " uuid = $uuid   " +
                    "name = ${gatt?.device?.name}  " +
                    "addr = ${gatt?.device?.address}   " +
                    "valueStr = ${String(descriptor?.value ?: byteArrayOf())}  "
        )
        nextTask()
    }


    open fun onCharWrite(
        characteristic: BluetoothGattCharacteristic?,
        status: Int,
        gatt: BluetoothGatt?
    ) {
        val uuid = characteristic?.uuid
        d(
            "Write   status = $status  " +
                    " uuid = $uuid   " +
                    "name = ${gatt!!.device.name}  " +
                    "addr = ${gatt.device.address}   " +
                    "valueStr = ${String(characteristic?.value ?: byteArrayOf())}  "
        )
        nextTask()
    }

    open fun onCharRead(
        characteristic: BluetoothGattCharacteristic?,
        status: Int,
        gatt: BluetoothGatt?
    ) {
        val uuid = characteristic?.uuid
        d(
            "Read   status = $status  " +
                    " uuid = $uuid   " +
                    "name = ${gatt!!.device.name}  " +
                    "addr = ${gatt.device.address}   " +
                    "valueStr = ${String(characteristic?.value ?: byteArrayOf())}  "
        )
        nextTask()
    }

    open fun onBtServiceDiscovered(status: Int, gatt: BluetoothGatt?) {
        d("status = $status     gatt = $gatt")
        when (status) {
            BluetoothGatt.GATT_SUCCESS -> {
//                showUUID(gatt)
                onSetUpConnection(gatt)
//                getReadyToCommunicate(gatt)
                connTimeoutTask?.cancel("STATE_CONNECTED")
                connectCb?.invoke(BluetoothGatt.STATE_CONNECTED, gatt)
                updateBtList(gatt, true)
                e("connect successful  start setUpConnection ")
            }
            else -> searchService(gatt, true)
        }
    }

    open fun onBtConnStateChanged(
        status: Int,
        newState: Int,
        gatt: BluetoothGatt?
    ) {
        d("status = $status  newState = $newState   gatt = $gatt")
        when (status) {
            BluetoothGatt.GATT_SUCCESS -> {
                if (newState == BluetoothGatt.STATE_CONNECTED) {
                    searchService(gatt, false)
                } else if (newState == BluetoothGatt.STATE_DISCONNECTED) {
                    connTimeoutTask?.cancel("STATE_DISCONNECTED")
                    updateBtList(gatt, false)
                    connectCb?.invoke(BluetoothGatt.STATE_DISCONNECTED, gatt)
                    gatt?.close()
                    refreshBtCache(gatt).apply { d("refreshBtCache = $this") }
                }
            }
            else -> {
                connTimeoutTask?.cancel("STATE_CONN_FAILED")
                updateBtList(gatt, false)
                gatt?.apply {
                    disconnect()
                    close()
                }
                refreshBtCache(gatt).apply { d("refreshBtCache = $this") }
                connectCb?.apply {
                    invoke(STATE_CONN_FAILED, gatt)
                    e("蓝牙连接失败！")
                }
            }
        }
    }


    /**
     * call this method to get ready before starting a communication
     */
    protected open fun getReadyToCommunicate(gatt: BluetoothGatt?) {
        val characteristic = GattUtils.getCharacteristic(gatt, UUID_SERVICE, UUID_CHAR)

        val descriptor = GattUtils.getDescriptor(
            gatt,
            UUID_CHAR,
            UUID_CHAR,
            UUID_DESC
        )

        characteristic?.also {

            gatt!!.setCharacteristicNotification(characteristic, true)
            descriptor?.value = BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE
            mRequestQueue.addWriteDescriptor(
                gatt,
                descriptor,
                BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE
            )


            descriptor?.value = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
            mRequestQueue.addWriteDescriptor(
                gatt,
                descriptor,
                BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
            )
            mRequestQueue.execute()

        }
    }

    protected open fun onSetUpConnection(gatt: BluetoothGatt?) {}

    private fun updateBtList(gatt: BluetoothGatt?, add: Boolean) =
        coroutineScope.launch(Dispatchers.Main) {
            gatt?.apply {
                connectedList.apply {
                    if (add) put(device.address, gatt) else remove(device.address)
                }
                d("connectedList.size = ${connectedList.size}")
            }
        }


    open fun writeData(addr: String, sendByte: ByteArray) = wt.submit {
        val bluetoothGatt = connectedList[addr]
        bluetoothGatt?.getService(UUID_SERVICE)?.getCharacteristic(UUID_CHAR)?.apply {
//            val data = BlueToothUtil.splitPacketFor20Byte(sendByte)
//            for (i in data.indices) {
//                value = data[i]
//                writeType = BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE
//               v("writeCharacteristic - - - $i")
//                bluetoothGatt.writeCharacteristic(this)
//                Thread.sleep(30)
//            }
        }
    }

    private fun showUUID(gatt: BluetoothGatt?) = coroutineScope.launch(Dispatchers.Default) {
        for (service in gatt!!.services) {
            val allUUIDs = StringBuilder(" UUIDs = {\n S = ${service.uuid}")
            for (characteristic in service.characteristics) {
                allUUIDs.append(",\n C = ").append(characteristic.uuid)
                for (descriptor in characteristic.descriptors) allUUIDs.append(",\n Descriptor = ")
                    .append(
                        descriptor.uuid
                    )
            }
            allUUIDs.append("}")
            e("onServicesDiscovered : $allUUIDs")
        }
    }

    private fun searchService(gatt: BluetoothGatt?, isRetry: Boolean) = coroutineScope.launch(
        Dispatchers.IO
    ) {
        v("before  gatt = $gatt  isRetry = $isRetry  ")
        isRetry.takeIf { isRetry }?.also {
            delay(1500)
        }
        gatt?.discoverServices()
        v("after   gatt = $gatt  isRetry = $isRetry  ")
    }

    private val btAdapter by lazy {
        BluetoothAdapter.getDefaultAdapter()
    }

    private val btScanner by lazy {
        btAdapter.bluetoothLeScanner
    }

    override fun onBind(intent: Intent?) = BtBinder().apply {
        registerReceiver(br, makeFilter())
        d("Service onBind")
    }

    override fun onCreate() {
        super.onCreate()
        d("BtService init")
    }

    open inner class BtBinder() : Binder() {
        open fun getService() = getRealService()
    }

    open fun getRealService() = this@BtService


    override fun hasBtConnected() = hasBtEnable() && connectedList.isNotEmpty().apply {
        d("connectedList.isNotEmpty() = $this")
    }

    override fun hasBtEnable() = btAdapter.isEnabled.apply {
        d("hasBtEnable = $this")
    }

    private var connTimeoutTask: Job? = null
    override fun connectBt(dev: BluetoothDevice, cb: (status: Int, gatt: BluetoothGatt?) -> Unit) {
        disconnectBt(dev.address)
        connectCb = cb
        connectCb?.invoke(STATE_CONN_START, null)
        connTimeoutTask?.cancel("new one created")
        connTimeoutTask = coroutineScope.launch(Dispatchers.Main) {
            delayTimeInSecond(20 * 1000)
            connectCb?.invoke(STATE_CONN_FAILED, null)
        }
        dev.connectGatt(BaseApp.app, false, gattCallback)
    }

    suspend fun delayTimeInSecond(s: Long) = withContext(Dispatchers.IO) {
        delay(s)
    }


    override fun enableBt() = btAdapter.enable().apply { d("enable = $this") }

    override fun disableBt() = btAdapter.disable().apply { d("disable = $this") }


    override fun disconnectBt(addr: String) {
        connectedList[addr]?.apply {
            disconnect()
        }
    }

    private var myScanCb: ((bean: ResultBean) -> Unit)? = null

    var connectCb: ((status: Int, gatt: BluetoothGatt?) -> Unit)? = null

    private var scanTask: Job? = null

    override fun startScanBt(cb: (bean: ResultBean) -> Unit) {
        myScanCb = cb
        if (isBtScanning) return
        btList.clear()
        isBtScanning = true
        d("startScanBt")
        myScanCb?.invoke(ResultBean(STATE_SCAN_START, mutableListOf(), -1))
        scanTask = coroutineScope.launch(Dispatchers.Default) {
            btScanner.startScan(scanCb)
            delay(8 * 1000)
            withContext(Dispatchers.Main) {
                myScanCb?.invoke(ResultBean(STATE_SCAN_FINISHED, btList, -1))
                stopScanBt()
            }
        }
    }

    override fun stopScanBt() {
        myScanCb = null
        if (isBtScanning) {
            isBtScanning = false
            btScanner.stopScan(scanCb)
        }
        scanTask?.apply {
            cancel("stopScan")
        }
        e("stopScanBt")
    }


    private val br by lazy {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                when (intent.action) {
                    BluetoothAdapter.ACTION_STATE_CHANGED -> {
                        val btState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0)
                        when (btState) {
                            BluetoothAdapter.STATE_TURNING_ON -> v("onReceive---------蓝牙正在打开中")
                            BluetoothAdapter.STATE_ON -> {
                                v("onReceive---------蓝牙已经打开")
                            }
                            BluetoothAdapter.STATE_TURNING_OFF -> v("onReceive---------蓝牙正在关闭中")
                            BluetoothAdapter.STATE_OFF -> v("onReceive---------蓝牙已经关闭")
                        }
                    }
                }
            }
        }
    }


    private val scanCb by lazy {
        object : ScanCallback() {
            override fun onScanResult(callbackType: Int, result: ScanResult?) {
                dealWithResult(callbackType, result)
            }

            override fun onScanFailed(errorCode: Int) {
                e("errorCode = $errorCode")
                myScanCb?.invoke(ResultBean(STATE_SCAN_FAILED, mutableListOf(), errorCode))
            }
        }
    }

    private fun dealWithResult(callbackType: Int, result: ScanResult?) = wt.submit {

        val serviceUuids = result?.scanRecord?.serviceUuids
//        v("result = $result    \n serviceUuids = $serviceUuids   \n  scanRecord = ${result?.scanRecord}    ")

        val device = result?.device

        if (device == null) return@submit

        if (TextUtils.isEmpty(device.name)) return@submit

        if (device.address.equals("00:00:00:00:00:00", ignoreCase = true)) return@submit

        d("device = $device     device.name = ${device.name}  ")

        if (btList.contains(device)) return@submit

        if (!makeScanBtFilter(device)) return@submit

        btList.add(device)

        coroutineScope.launch(Dispatchers.Main) {
            myScanCb?.invoke(ResultBean(STATE_SCAN_SUCCEEDED, btList, callbackType))
            d("Thread = ${Thread.currentThread().name} btList.size=${btList.size}  $btList ")
        }

    }

    protected open fun makeScanBtFilter(it: BluetoothDevice) = true

    val btList: MutableList<BluetoothDevice> = mutableListOf()

    private fun makeFilter(): IntentFilter {
        val filter = IntentFilter()
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
        return filter
    }

    override fun onUnbind(intent: Intent?) = super.onUnbind(intent).apply {
        e("Service unbind()")
        unregisterReceiver(br)
        scanTask?.cancel("onServiceUnbind")
        connTimeoutTask?.cancel("onServiceUnbind")
        coroutineScope.cancel("Service onDestroy")
        scanTask = null
        connTimeoutTask = null
        myScanCb = null
        connectCb = null
        wt.shutdownNow()
        connectedList.clear()
        btList.clear()
    }


    fun refreshBtCache(gatt: BluetoothGatt?): Boolean {
        try {
            val localMethod = gatt?.javaClass?.getMethod("refresh", *arrayOfNulls(0))
            return (localMethod?.invoke(gatt, *arrayOfNulls(0))) as Boolean
        } catch (e: Exception) {
            e.printStackTrace()
            e("e = ${e.message}")
        }
        return false
    }

//    private val lock = Any()  // for lock
//    protected fun nextTask() {
//        synchronized(lock) { mRequestQueue.next() }
//    }

    protected fun nextTask(): Future<*> = wt.submit {
        mRequestQueue.next()
    }


}