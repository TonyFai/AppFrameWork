package com.dxtdkwt.zzh.appframework.kotlin

class User2 {
    lateinit var name: String

    fun isNameInit(): Boolean {
        return ::name.isInitialized
    }
}

data class User3(var name: String, var sex: String, var age: Int)

class User(id: Int) {
    var id: Int
        //是一个属性
        get() = 0
        set(value) {
            id = value
        }
    var name: String = ""
        get() = ""
        set(value) {
            field = value
        }

    init {
        this.id = id
        this.name = ""
    }

    constructor(id: Int, name: String) : this(id) {//次构造函数
        this.id = id
        this.name = name
    }
}

class VarOrvalTest {
    val a: Int = 0 //不需要改变的话，尽量用哪个val
    val b: Int = 1
}

fun strDisplay(str: String?) {
//    println("str的长度：${str!!.length}")//断言运算符
    println("str的长度：${str?.length}")
}

fun getStringName(name: String): String {
    return "名字为：" + name
}

fun getUnitName(name: String): Unit {
    println("名字为：" + name)
}

fun sum(a: Int, b: Int) = a + b

fun main() {
    val a: Int = 0;
//    a = 1; //a 只读的 final
    var b: Int = 1//可读可写
    b = 12;

    val a1 = 12 //自动推断类型，静态语言 （编译期就推断类型）和 动态语言
//    a1 = 2.3f //不可以随意转化
    //？NPE

    //这两种为不同类型
    var a2: Int?
    var a3: Int

    val price = 23
    val str1 = "asdfghjkl"
    var str = "你好$price,str的长度：${str1.length}"

    val str2 = """
        asdf
        qwer
        zxcv
    """.trimIndent()

    println(str)

    var name = getStringName("渣渣辉")
    println(name)

    var sum = sum(3, 2)
    println(sum)

    var user2 = User2()
//    if (!user2.name.isEmpty()){
    if (user2.isNameInit()) {
        println(user2.name)
    }

    var user3 = User3(" 渣渣辉", "男", 18)
//    var user3name: String = ""
//    var user3sex: String = ""
//    var user3age = 0

    val (user3name, user3sex, user3age) = user3
}