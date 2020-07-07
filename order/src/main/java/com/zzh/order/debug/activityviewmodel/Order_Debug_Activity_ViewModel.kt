package com.zzh.order.debug.activityviewmodel

import android.widget.Toast
import com.dxtdkwt.zzh.baselibrary.model.BaseModel
import com.dxtdkwt.zzh.baselibrary.utils.ToastUtils
import com.dxtdkwt.zzh.baselibrary.view.IBaseView
import com.dxtdkwt.zzh.baselibrary.viewmodel.BaseViewModel

class Order_Debug_Activity_ViewModel : BaseViewModel<Order_Debug_Activity_ViewModel.Companion.IActivityView, Order_Debug_Model>(), BaseModel.IModelListener<String> {

    var str: String = "你好"

    init {
        model = Order_Debug_Model()
        model.register(this)
    }

    public fun refresh() {
        model.getCachedDataAndLoad()
    }

    override fun onLoadFinish(model: BaseModel<*>?, data: String?) {
        if (model is Order_Debug_Model) {
            if (getPageView() != null && data is String) {
                getPageView().onChannelsLoaded(data)
            }
        }
    }

    override fun onLoadFail(model: BaseModel<*>?, prompt: String?) {
        ToastUtils.showShort(str);
    }

    companion object {
        interface IActivityView : IBaseView {
            fun onChannelsLoaded(channels: String)
        }
    }
}
