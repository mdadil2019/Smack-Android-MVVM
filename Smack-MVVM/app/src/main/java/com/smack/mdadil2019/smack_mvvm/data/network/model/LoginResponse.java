package com.smack.mdadil2019.smack_mvvm.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Expose
    @SerializedName("user")
    private String user;

    @Expose
    @SerializedName("token")
    private String token;
}
