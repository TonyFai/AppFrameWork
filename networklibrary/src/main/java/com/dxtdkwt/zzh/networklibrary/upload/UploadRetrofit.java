package com.dxtdkwt.zzh.networklibrary.upload;


import com.dxtdkwt.zzh.networklibrary.NetworkConstant;
import com.dxtdkwt.zzh.networklibrary.http.HttpClient;
import com.dxtdkwt.zzh.networklibrary.http.RetrofitClient;
import com.dxtdkwt.zzh.networklibrary.interceptor.Transformer;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by allen on 2017/6/14.
 * <p>
 * 为上传单独建一个retrofit
 */

public class UploadRetrofit {

    private static UploadRetrofit instance;
    private Retrofit mRetrofit;


    private UploadRetrofit() {
        String baseUrl = NetworkConstant.UPLOAD_URL;
        mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl).client(HttpClient.getInstance().getOkHttpClient())
                .build();
    }

    private static UploadRetrofit getInstance() {

        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new UploadRetrofit();
                }
            }

        }
        return instance;
    }

    private Retrofit getRetrofit() {
        return mRetrofit;
    }


    public static Observable<Object> uploadImg(String filePath, String name) {
        File file = new File(filePath);

        MultipartBody.Part[] part = new MultipartBody.Part[1];

        RequestBody requestFile =
                RequestBody.create(MediaType.parse("text/plain"), file);
        part[0] = MultipartBody.Part.createFormData("file", "uploaded_file", requestFile);


        return UploadRetrofit
                .getInstance()
                .getRetrofit()
                .create(UploadFileApi.class)
                .uploadImg(part, name)
                .compose(Transformer.switchSchedulers());
    }


}
