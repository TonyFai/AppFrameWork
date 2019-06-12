package com.dxtdkwt.zzh.networklibrary.upload;


import com.zzh.test.network.http.HttpClient;
import com.zzh.test.network.http.RetrofitClient;

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
        String baseUrl = "";
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


//    public static Observable<ImageUpLoadBean> uploadImg(String filePath,String type) {
//        File file = new File(filePath);
//
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/"+type), file);
//
//        MultipartBody.Part body =
//                MultipartBody.Part.createFormData("uploaded_file", file.getName(), requestFile);
//
//        return UploadRetrofit
//                .getInstance()
//                .getRetrofit()
//                .create(UploadFileApi.class)
//                .uploadImg(body,type)
//                .compose(Transformer.switchSchedulers());
//    }
//
//
//    public static Observable<ImageUpLoadBean> uploadImg(String filePath) {
//        File file = new File(filePath);
//
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/jpeg"), file);
//
//        MultipartBody.Part body =
//                MultipartBody.Part.createFormData("uploaded_file", file.getName(), requestFile);
//
//        return UploadRetrofit
//                .getInstance()
//                .getRetrofit()
//                .create(UploadFileApi.class)
//                .uploadImg(NetConstant.URL_UPLOAD_IMG,body)
//                .compose(Transformer.switchSchedulers());
//    }
//
//    public static Observable<ImageUpLoadBean> uploadImg(ArrayList filePaths,String type) {
//
//
//        MultipartBody.Part[] part = new MultipartBody.Part[filePaths.size()];
//        for (int i = 0; i < filePaths.size(); i++) {
//            String fileName = (String) filePaths.get(i);
//            File file = new File(fileName);
//            RequestBody requestFile =
//                    RequestBody.create(MediaType.parse("multipart/"+type), file);
//            part[i] = MultipartBody.Part.createFormData("file", fileName, requestFile);
//        }
//
//
//        return UploadRetrofit
//                .getInstance()
//                .getRetrofit()
//                .create(UploadFileApi.class)
//                .uploadImg(NetConstant.URL_UPLOAD_IMG, part, type)
//                .compose(Transformer.switchSchedulers());
//    }
//
//
//    public static Observable<ImageUpLoadBean> uploadImg(ArrayList filePaths) {
//
//
//        MultipartBody.Part[] part = new MultipartBody.Part[filePaths.size()];
//        for (int i = 0; i < filePaths.size(); i++) {
//            String fileName = (String) filePaths.get(i);
//            File file = new File(fileName);
//            RequestBody requestFile =
//                    RequestBody.create(MediaType.parse("multipart/jpeg"), file);
//            part[i] = MultipartBody.Part.createFormData("file", fileName, requestFile);
//        }
//
//
//        return UploadRetrofit
//                .getInstance()
//                .getRetrofit()
//                .create(UploadFileApi.class)
//                .uploadImg(NetConstant.URL_UPLOAD_IMG, part)
//                .compose(Transformer.switchSchedulers());
//    }
}
