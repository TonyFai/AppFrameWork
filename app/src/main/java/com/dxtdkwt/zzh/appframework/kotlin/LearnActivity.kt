package com.dxtdkwt.zzh.appframework.kotlin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dxtdkwt.zzh.appframework.R
import kotlinx.android.synthetic.main.activity_learn.*

//exends == ：  implements  == ,
class LearnActivity : BaseActivity(), Runnable {
    override fun getLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return R.layout.activity_learn
    }

    override fun initView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun run() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //变量声明
//    var | val 变量名[:类型][= 初始值]
//    var 代表是一个变量 可以随意修改  val 代表一个常量

    //延时加载
    lateinit var mTextView3 : TextView;
    var mTextView2 : TextView? = null;
    val mTextView: TextView = findViewById(R.id.tv_text);

    //@overrride  == override 关键字
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn)

        //判空处理
        mTextView2?.text = ""
        //确定该值不会为空
        mTextView2!!.text = ""

        tv_text.text = ""
    }
}
