package com.ybs.base.ble

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt


/**
 *
 * @ProjectName:    AylsonHome
 * @Package:        com.aylson.app.ui5.treasure.base
 * @ClassName:      BtFun
 * @Description:
 * @Author:         TaxiDriverSantos
 * @CreateDate:     2020/7/25   15:09
 * @UpdateUser:     TaxiDriverSantos
 * @UpdateDate:     2020/7/25   15:09
 * @UpdateRemark:
 * @Version:        1.0
 */
interface BtFun {


    fun enableBt(): Boolean
    fun disableBt(): Boolean

    fun hasBtEnable(): Boolean
    fun hasBtConnected(): Boolean

    fun connectBt(dev: BluetoothDevice, cb: (status: Int, gatt: BluetoothGatt?) -> Unit)
    fun disconnectBt(addr: String)
    fun startScanBt(cb: (bean: ResultBean) -> Unit)
    fun stopScanBt()

}