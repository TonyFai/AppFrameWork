package com.zzh.order.debug

import android.content.Intent
import android.view.View
import com.dxtdkwt.zzh.baselibrary.activity.BaseActivity
import com.dxtdkwt.zzh.baselibrary.utils.ToastUtils
import com.zzh.order.BR
import com.zzh.order.Order_MainActivity
import com.zzh.order.R
import com.zzh.order.databinding.OrderDebugMainActivityBinding
import com.zzh.order.debug.activityviewmodel.Order_Debug_Activity_ViewModel
import com.zzh.order.debug.bean.Order_Debug_Bean

class Order_DebugActivity : BaseActivity<OrderDebugMainActivityBinding, Order_Debug_Activity_ViewModel>(), Order_Debug_Activity_ViewModel.Companion.IActivityView {
    override fun onRetryBtnClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getViewModel(): Order_Debug_Activity_ViewModel = Order_Debug_Activity_ViewModel()
    override fun getBindingVariable(): Int {
        return 0
    }

    override fun onRefreshEmpty() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutId(): Int = R.layout.order_debug_main_activity

    override fun initData() {
    }

    var orderDebugBean = Order_Debug_Bean()

    override fun initView() {
        viewDataBinding.bean = Order_Debug_Bean()
        viewDataBinding.presenter = this
//        orderDebugBean.data
        ToastUtils.showShort("数据为："+orderDebugBean.data)

    }

    fun onClick(view: View) {
        startActivity(Intent(this, Order_MainActivity::class.java))
    }

    override fun onChannelsLoaded(channels: String) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        viewDataBinding.tvText.setText(channels)
//        orderDebugBean.data = "嘿嘿嘿"
        viewDataBinding.setVariable(BR.data, orderDebugBean.str)
    }

    fun onTextClick(view: View) {
        ToastUtils.showShort("点击了这个地方，执行了refresh方法")
        viewModel.refresh()
    }


}