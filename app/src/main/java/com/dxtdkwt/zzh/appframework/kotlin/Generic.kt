package com.dxtdkwt.zzh.appframework.kotlin

class Generic {
}

interface Source<out T> {
    fun getT(): T
}

fun test(src :Source<String>){
    val dest : Source<Any?> =  src;
}

//逆变super
fun <T> copyIn(dest: Array<in T>, src: Array<T>) {
    //PESC
    src.forEachIndexed { index, value -> dest[index] = src[index] }
}

//协变extends
fun <T> copyOut(dest: Array<T>, src: Array<out T>) {

}

fun main() {
    var list = listOf<Int>(1, 2, 3)
    var array = arrayOf(1, 2, 3)

    var destDouble = arrayOf<Double>()
    var srcDouble = arrayOf<Double>(1.0, 2.0, 3.0, 4.0)

    copyIn(destDouble, srcDouble)

    var destInt = arrayOf<Int>()
    var src = arrayOf(1, 2, 5)

    var dest = arrayOf<Number>(3)
    copyIn(dest, src)
    copyOut(dest, src)

}