package com.example.ipc.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.security.Policy;

/**
 * 请求
 */
public class Request implements Parcelable {

    //获得单例对象
    public static final int GET_INSTANCE = 0;
    //执行方法
    public static final int GET_METHOD = 1;

    private int requestType;

    private String serverId;

    private String method;

    private Parameters[] mParameters;

    public Request(int requestType, String serverId, String method, Parameters[] parameters) {
        this.requestType = requestType;
        this.serverId = serverId;
        this.method = method;
        mParameters = parameters;
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Parameters[] getParameters() {
        return mParameters;
    }

    public void setParameters(Parameters[] parameters) {
        mParameters = parameters;
    }

    public static Creator<Request> getCREATOR() {
        return CREATOR;
    }

    protected Request(Parcel in) {
        requestType = in.readInt();
        serverId = in.readString();
        method = in.readString();
        mParameters = in.createTypedArray(Parameters.CREATOR);
    }

    public static final Creator<Request> CREATOR = new Creator<Request>() {
        @Override
        public Request createFromParcel(Parcel in) {
            return new Request(in);
        }

        @Override
        public Request[] newArray(int size) {
            return new Request[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(requestType);
        dest.writeString(serverId);
        dest.writeString(method);
        dest.writeTypedArray(mParameters, flags);
    }
}
