package com.dxtdkwt.zzh.networklibrary.upload;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by allen on 2017/6/15.
 * <p>
 * 文件上传
 */

interface UploadFileApi {



    @Multipart
    @POST("svc/upload/setFileUpload/")
    Observable<Object> uploadImg(@Part MultipartBody.Part[] file, @Query("type") String type);

}
