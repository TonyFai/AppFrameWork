package com.zzh.order.debug

import android.content.Intent
import android.view.View
import com.dxtdkwt.zzh.baselibrary.activity.BaseActivity
import com.zzh.order.Order_MainActivity
import com.zzh.order.R

class Order_DebugActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.order_debug_main_activity

    override fun initData() {
    }

    override fun initView() {
    }

    fun onClick(view: View) {
        startActivity(Intent(this, Order_MainActivity::class.java))
    }

}