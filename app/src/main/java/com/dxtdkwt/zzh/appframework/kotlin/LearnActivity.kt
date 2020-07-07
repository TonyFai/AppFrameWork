package com.dxtdkwt.zzh.appframework.kotlin

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.dxtdkwt.zzh.appframework.R
import kotlinx.android.synthetic.main.activity_learn.*

//exends == ：  implements  == ,
class LearnActivity : BaseActivity(), Runnable {
    override fun getLayoutId(): Int = R.layout.activity_learn


    override fun initView() {
    }

    override fun initData() {
    }

    override fun run() {
    }

    private fun parseUi() {
        mapOf(Pair(1,2))
        val hintMap = hashMapOf(tv_text to "错误", tv_text2 to "错误2", et_text to "错误")
        fun parseView(vararg id: TextView) {
            id.forEach {
                if (it.text.toString().isEmpty()) {
                    Toast.makeText(this, hintMap[it], Toast.LENGTH_SHORT).show()
                }
            }
        }
        parseView(tv_text, tv_text2, et_text)
    }

    //变量声明
//    var | val 变量名[:类型][= 初始值]
//    var 代表是一个变量 可以随意修改  val 代表一个常量

    //延时加载
    lateinit var mTextView3: TextView;
    var mTextView2: TextView? = null;
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
