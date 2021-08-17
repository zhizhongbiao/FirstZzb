package com.ybs.base.ble

import android.bluetooth.BluetoothDevice

/**
 *
 * @ProjectName:    AylsonHome
 * @Package:        com.aylson.app.ui5.treasure.base
 * @ClassName:      ResultBean
 * @Description:
 * @Author:         TaxiDriverSantos
 * @CreateDate:     2020/7/30   10:39
 * @UpdateUser:     TaxiDriverSantos
 * @UpdateDate:     2020/7/30   10:39
 * @UpdateRemark:
 * @Version:        1.0
 */
data class ResultBean(
    val state: Int,
    val btList: MutableList<BluetoothDevice>,
    val type: Int = -999
) {
    override fun toString(): String {
        return "ResultBean(code=$state, type=$type, btList=$btList)"
    }
}