package com.dxtdkwt.zzh.appframework.data_collection

sealed class StatReportStrategy (open val state: StatConfig) {


//    //实时发送，App每产生一条消息都会发送到服务器
//    INSTANT,
//    //只在Wifi状态喜爱发送，而在非Wifi状态下缓存到本地
//    ONLY_WIFI,
//    //批量发送，默认当消息数量达到30条时发送一次
//    BATCH,
//    //只在启动时发送，本次产生的所有数据在下次启动时发送
//    APP_LAUNCH,
//    //开发者模式，只在App调用void commitEvents(Context)时发送，否则缓存消息到本地
//    DEVELOPER,
//    //间隔一段时间发送，每隔一段时间一次发送到服务器
//    PERIOD
}

class AppLaunch(override val state: StatConfig) : StatReportStrategy(state)
