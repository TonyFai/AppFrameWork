package com.dxtdkwt.zzh.appframework.kotlin

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.system.measureTimeMillis

class Test {
}

//fun main() {
////    println("json".equals())
//    sayHi("你好",age = 10)
//    sayHi(name = "hh",age = 10)
//
//}

fun sayHi(name:String = "jack",age:Int){

}

//协程
fun main() {
//    //在后台启动一个协程
//    GlobalScope.launch {
//        //延迟1秒(非阻塞)  挂起协程，不会阻塞线程
//        delay(1000L)
//        //延迟之后输出
//        println("World!")
//    }
//    //协程被延迟了1秒，但是主线程继续执行
//    println("Hello,")
//    //为了使JVM包活，阻塞主线程2秒钟  阻塞线程
//    Thread.sleep(2000L)

    //协程但切换可以由程序自己来控制，不需要操作系统去进行调度
    //runBlocking 为最高级但协程，也就是主协程
//    runBlocking {
//        repeat(100_000) {
//            launch {
//                println("Hello")
//            }
//        }
//
//        launch {
//            delay(1000L)
//            println("World!")
//        }
//        println("Hello,")
//        delay(2000L)
//
//        launch {
//            search()
//        }
//    }

    runBlocking {
        val job = launch {
            search()
        }
        println("Hello,")
        job.join()
    }

    runBlocking<Unit> {
        val one = searchItemlOne()
        val two = searchItemlTwo()
        println("The items is ${one} and ${two}")
    }

    runBlocking {
        //异步并行
        val one = async { searchItemlOne() }
        val two = async { searchItemlTwo() }
        println("The items is ${one.await()} and ${two.await()}")
    }

    runBlocking {
        val time = measureTimeMillis {
            val one = searchItemlOne()
            val two = searchItemlTwo()
            println("The items is ${one} and ${two}")
        }
        println("Cost time is ${time} ms")

        val time1 = measureTimeMillis {
            val one = async { searchItemlOne() }
            val two = async { searchItemlTwo() }
            println("The items is ${one.await()} and ${two.await()}")
        }
        println("Cost time is ${time1} ms")
    }

}

suspend fun search() {
    delay(1000L)
    println("查询数据...")
}

suspend fun searchItemlOne(): String {
    delay(1000L)
    return "item-one"
}

suspend fun searchItemlTwo(): String {
    delay(1000L)
    return "item-two"
}

class Shop {
    val goods = hashMapOf<Long, Int>()

    @Volatile
    private var running = false

    var lock: Lock = ReentrantLock()

    init {
        goods.put(1, 10)
        goods.put(2, 15)
    }

    @Synchronized
    fun buyGoods(id: Long) {
        val stock = goods.getValue(id)
        goods.put(id, stock - 1)
    }

    fun buyGoods2(id: Long) {
        val stock = goods.getValue(id)
        goods.put(id, stock - 1)
    }

    fun buyGoods3(id: Long) {
        lock.lock()
        try {
            val stock = goods.getValue(id)
            goods.put(id, stock - 1)
        } catch (ex: Exception) {
            println("[Exception] is ${ex}")
        } finally {
            lock.unlock()
        }
    }

    fun buyGoods4(id:Long){
        val stock = goods.getValue(id)
        goods.put(id,stock -1)
    }

    init {
//        withLock(lock) {buyGoods4(1)}
        lock.withLock { buyGoods4(1) }
    }
}

//fun <T> withLock(lock: Lock, action: () -> T) {
//    lock.lock()
//    try {
//        action()
//    } catch (ex: Exception) {
//        println("[Exception] is ${ex}")
//    } finally {
//        lock.unlock()
//    }
//}

fun <T> Lock.withLock(action:()->T){
    this.lock()
    try {
        action()
    }catch (ex:Exception){
        println("[Exception] is ${ex}")
    }finally {
        this.unlock()
    }
}