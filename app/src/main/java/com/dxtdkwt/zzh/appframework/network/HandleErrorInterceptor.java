package com.dxtdkwt.zzh.appframework.network;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.Response;

public class HandleErrorInterceptor extends JavaResponseBodyInterceptor {
    @Override
    public Response intercept(Response response, String body) {
        JsonObject jsonObject = null;

        jsonObject = new JsonParser().parse(body).getAsJsonObject();
        if (jsonObject != null) {
            if (jsonObject.get("code").getAsInt() != 200 && jsonObject.has("msg")) {
                throw new RuntimeException(jsonObject.get("msg").getAsString());
            }
        }
        return response;
    }
}
