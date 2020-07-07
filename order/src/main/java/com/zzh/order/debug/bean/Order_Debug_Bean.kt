package com.zzh.order.debug.bean

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.zzh.order.BR

class Order_Debug_Bean : BaseObservable() {

    @get:Bindable
    var data: String = "你好世界"
        set(value) {
            field = value;
            notifyPropertyChanged(BR.data)
        }

    @get:Bindable
    var str: String = "啦啦啦"
        set(value) {
            field = value
            notifyPropertyChanged(BR.data)
        }
}