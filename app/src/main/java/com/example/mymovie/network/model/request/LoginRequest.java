package com.example.mymovie.network.model.request;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest implements Parcelable {
    @SerializedName("mem_no")
    @Expose
    private String mem_no;

    @SerializedName("mem_id")
    @Expose
    private String imem_id;

    @SerializedName("mem_pw")
    @Expose
    private String mem_pw;

    @SerializedName("mem_name")
    @Expose
    private String mem_name;

    public String getMem_no() {
        return mem_no;
    }

    public void setMem_no(String mem_no) {
        this.mem_no = mem_no;
    }

    public String getImem_id() {
        return imem_id;
    }

    public void setImem_id(String imem_id) {
        this.imem_id = imem_id;
    }

    public String getMem_pw() {
        return mem_pw;
    }

    public void setMem_pw(String mem_pw) {
        this.mem_pw = mem_pw;
    }

    public String getMem_name() {
        return mem_name;
    }

    public void setMem_name(String mem_name) {
        this.mem_name = mem_name;
    }

    protected LoginRequest(Parcel in) {
        mem_no = in.readString();
        imem_id = in.readString();
        mem_pw = in.readString();
        mem_name = in.readString();
    }

    public static final Creator<LoginRequest> CREATOR = new Creator<LoginRequest>() {
        @Override
        public LoginRequest createFromParcel(Parcel in) {
            return new LoginRequest(in);
        }

        @Override
        public LoginRequest[] newArray(int size) {
            return new LoginRequest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(mem_no);
        dest.writeString(imem_id);
        dest.writeString(mem_pw);
        dest.writeString(mem_name);
    }
}
