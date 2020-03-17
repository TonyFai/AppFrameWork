package com.zzh.order.debug

import com.dxtdkwt.zzh.baselibrary.BaseApplication
import com.dxtdkwt.zzh.baselibrary.utils.LogUtils
import com.zzh.order.config.Order_Config

class Order_Debug_Application : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        LogUtils.i(Order_Config.Tag, "order/debug/Order_Debug_Application  模式开启运行   Application 正在加载中。。。")
    }

}