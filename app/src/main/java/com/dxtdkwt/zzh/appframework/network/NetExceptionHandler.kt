package com.dxtdkwt.zzh.appframework.network

import android.content.Context
import android.net.ConnectivityManager
import android.system.ErrnoException
import com.dxtdkwt.zzh.appframework.R
import com.google.gson.JsonParseException
import io.reactivex.exceptions.OnErrorNotImplementedException
import kotlinx.android.synthetic.main.activity_shortcut.view.*
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLHandshakeException

class NetExceptionHandler {
    fun dealException(context: Context, t: Throwable) {
        if (!context.isAvailable()) {
            onException(HttpConfig.CONNECT_ERROR, context)
            return
        }
        when (t) {
            is ConnectException -> {
                onException(HttpConfig.CONNECT_ERROR, context)
            }
            is UnknownHostException -> {
                onException(HttpConfig.UNKNOWN_HOST, context)
            }
            is InterruptedException -> {
                onException(HttpConfig.CONNECT_TIMEOUT, context)
            }
            is ParseException,
            is JSONException -> {
                onException(HttpConfig.PARSE_ERROR, context)
            }
            is SocketTimeoutException -> {
                onException(HttpConfig.REQUEST_TIMEOUT, context)
            }
            is UnknownError -> {
                onException(HttpConfig.UNKNOWN_ERROR, context)
            }
            is IllegalArgumentException -> {
                onException(HttpConfig.ILLEGAL_PARAMS, context)
            }
            else -> onException(HttpConfig.OTHER_ERROR, context)
        }
    }

    private fun onException(connectError: Int, context: Context) {

    }

    private fun Context.isAvailable(): Boolean {
        val connectivityManager: ConnectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo ?: return false
        return activeNetwork.isAvailable
    }

}

val Throwable.showMsg: String
    get() {
        return when (this) {
            is HttpException -> "${
            when (code()) {
                401 -> "文件未授权或证书错误"
                403 -> "服务器拒绝请求"
                404 -> "服务器找不到请求到文件"
                408 -> "请求超时，服务器未响应"
                500 -> "服务器内部错误）服务器遇到错误，无法完成请求"
                502 -> "服务器从上游服务器收到无效响应"
                503 -> "服务器目前无法使用"
                504 -> "服务器上游服务器获取数据超时"
                else -> "服务器错误"
            }
            },错误码:${code()}"
            is ParseException -> "数据格式错误，请稍后重试"
            is JSONException -> "发送的数据不正确，请稍后重试"
            is JsonParseException -> "json参数异常导致解析失败"
            is SSLHandshakeException -> "证书验证失败"
            is SocketTimeoutException -> "连接超时，请稍后重试"
            is TimeoutException -> "连接超时，服务器未在设定时间内回应，请稍后重试"
            is UnknownHostException -> "网络链接失败"
            is ErrnoException -> "网络不可访问"
            is ConnectException -> "远程服务器未响应"
            is OnErrorNotImplementedException -> cause?.showMsg ?: message ?: "未知错误"
            else -> message ?: "未知错误"
        }
    }