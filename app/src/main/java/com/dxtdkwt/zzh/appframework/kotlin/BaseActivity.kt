package com.dxtdkwt.zzh.appframework.kotlin

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(getLayoutId())

        initView()
        initData()
    }

    abstract fun getLayoutId():Int

    abstract fun initView()

    abstract fun initData()
}