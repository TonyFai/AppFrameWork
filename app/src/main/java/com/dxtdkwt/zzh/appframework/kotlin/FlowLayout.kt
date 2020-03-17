package com.dxtdkwt.zzh.appframework.kotlin

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.annotation.IntDef

class FlowLayout(context: Context) : ViewGroup(context) {

    class FlowView : ViewGroup {
        override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {

        }
    }


    //此构造函数需要掉用主构造函数
    constructor(context: Context, attributes: AttributeSet, defStyleAttr: Int, intDef: IntDef) : this(context) {

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}