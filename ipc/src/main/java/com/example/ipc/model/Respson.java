package com.example.ipc.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Respson  implements Parcelable {
    private String source;
    private boolean isSuccess;

    public Respson(String source, boolean isSuccess) {
        this.source = source;
        this.isSuccess = isSuccess;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public static Creator<Respson> getCREATOR() {
        return CREATOR;
    }

    protected Respson(Parcel in) {
        source = in.readString();
        isSuccess = in.readByte() != 0;
    }

    public static final Creator<Respson> CREATOR = new Creator<Respson>() {
        @Override
        public Respson createFromParcel(Parcel in) {
            return new Respson(in);
        }

        @Override
        public Respson[] newArray(int size) {
            return new Respson[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(source);
        dest.writeByte((byte) (isSuccess ? 1 : 0));
    }
}
