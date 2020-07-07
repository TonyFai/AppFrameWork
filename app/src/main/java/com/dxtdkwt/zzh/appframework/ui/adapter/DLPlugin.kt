package com.dxtdkwt.zzh.appframework.ui.adapter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.WindowManager

interface DLPlugin {
    fun onStart()
    fun onRestart()
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent)
    fun onResume()
    fun onPause()
    fun onStop()
    fun onDestroy()
    fun onCreate(saveInstanceState: Bundle)
    fun setProxy(proxyActivity: Activity, dexPath:String)
    fun onSaveInstanceState(outState: Bundle)
    fun onNewIntent(intent: Intent)
    fun onRestoreInstanceState(savedInstanceState: Bundle)
    fun onTouchEvent(event: MotionEvent)
    fun onKeyUp(keyCode:Int,event: MotionEvent)
    fun onWindowAttributesChanged(params: WindowManager.LayoutParams)
    fun onWindowFocusChanged(hasFocus:Boolean)
    fun onBackPressed()
}