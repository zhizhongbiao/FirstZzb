package com.ybs.base.data.bean

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.base.data.bean
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/20 21:02
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/20 21:02
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
data class GetAccessTokenResp(
    val accessToken: String? = "",
    val certificatePem: String? = "",
    val channelList: Any? = Any(),
    val componentProfileSnList: Any? = Any(),
    val deviceBaseInfo: DeviceBaseInfo? = DeviceBaseInfo(),
    val deviceNickName: Any? = Any(),
    val deviceSn: String? = "",
    val enableIot: Boolean? = false,
    val ext: Any? = Any(),
    val merchantId: String? = "",
    val onlineStatus: Int? = 0,
    val privateKey: String? = "",
    val rootCa: String? = "",
    val sslUrl: String? = "",
    val verificationCode: Any? = Any()


) {
    override fun toString(): String {
        return "GetAccessTokenResp(accessToken=$accessToken, certificatePem=$certificatePem, channelList=$channelList, componentProfileSnList=$componentProfileSnList, deviceBaseInfo=$deviceBaseInfo, deviceNickName=$deviceNickName, deviceSn=$deviceSn, enableIot=$enableIot, ext=$ext, merchantId=$merchantId, onlineStatus=$onlineStatus, privateKey=$privateKey, rootCa=$rootCa, sslUrl=$sslUrl, verificationCode=$verificationCode)"
    }
}

data class DeviceBaseInfo(
    val agvPower: Any? = Any(),
    val agvSupplier: Any? = Any(),
    val applicationCode: Any? = Any(),
    val applicationVersionCode: Any? = Any(),
    val applicationVersionName: Any? = Any(),
    val color: Any? = Any(),
    val coverOpenStatus: Any? = Any(),
    val danceStatus: Any? = Any(),
    val deviceNickName: String? = "",
    val domain: Any? = Any(),
    val emergencyStop: Any? = Any(),
    val errorStatus: Boolean? = false,
    val factorySerialNo: Any? = Any(),
    val greetStatus: Any? = Any(),
    val hardStop: Any? = Any(),
    val ipAddress: Any? = Any(),
    val isCharging: Any? = Any(),
    val leftwaterBoxStatus: Any? = Any(),
    val mapName: Any? = Any(),
    val mcuVersionCode: Any? = Any(),
    val mcuVersionName: Any? = Any(),
    val modelType: Any? = Any(),
    val onlineStatus: Int? = 0,
    val osAuth: Any? = Any(),
    val patrolType: Any? = Any(),
    val quality: Any? = Any(),
    val released: Any? = Any(),
    val rightwaterBoxStatus: Any? = Any(),
    val scMcuVersionCode: Any? = Any(),
    val scMcuVersionName: Any? = Any(),
    val shutDownTime: String? = "",
    val softStop: Any? = Any(),
    val speed: Any? = Any(),
    val statusText: String? = "",
    val voiceBoard: Any? = Any(),
    val volume: Any? = Any(),
    val wifiName: Any? = Any(),
    val ybModelType: Any? = Any(),
    val ybfaceRegister: Any? = Any(),
    val ybguide: Any? = Any(),
    val ybholdWelcome: Any? = Any(),
    val ybtakeNum: Any? = Any(),
    val ybybVersionCode: Any? = Any(),
    val ybzbVersionCode: Any? = Any()


) {
    override fun toString(): String {
        return "DeviceBaseInfo(agvPower=$agvPower, agvSupplier=$agvSupplier, applicationCode=$applicationCode, applicationVersionCode=$applicationVersionCode, applicationVersionName=$applicationVersionName, color=$color, coverOpenStatus=$coverOpenStatus, danceStatus=$danceStatus, deviceNickName=$deviceNickName, domain=$domain, emergencyStop=$emergencyStop, errorStatus=$errorStatus, factorySerialNo=$factorySerialNo, greetStatus=$greetStatus, hardStop=$hardStop, ipAddress=$ipAddress, isCharging=$isCharging, leftwaterBoxStatus=$leftwaterBoxStatus, mapName=$mapName, mcuVersionCode=$mcuVersionCode, mcuVersionName=$mcuVersionName, modelType=$modelType, onlineStatus=$onlineStatus, osAuth=$osAuth, patrolType=$patrolType, quality=$quality, released=$released, rightwaterBoxStatus=$rightwaterBoxStatus, scMcuVersionCode=$scMcuVersionCode, scMcuVersionName=$scMcuVersionName, shutDownTime=$shutDownTime, softStop=$softStop, speed=$speed, statusText=$statusText, voiceBoard=$voiceBoard, volume=$volume, wifiName=$wifiName, ybModelType=$ybModelType, ybfaceRegister=$ybfaceRegister, ybguide=$ybguide, ybholdWelcome=$ybholdWelcome, ybtakeNum=$ybtakeNum, ybybVersionCode=$ybybVersionCode, ybzbVersionCode=$ybzbVersionCode)"
    }
}