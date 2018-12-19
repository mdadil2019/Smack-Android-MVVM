package com.smack.mdadil2019.smack_mvvm.data.network;

import android.arch.lifecycle.LiveData;

import com.smack.mdadil2019.smack_mvvm.data.network.model.LoginRequest;
import com.smack.mdadil2019.smack_mvvm.data.network.model.LoginResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginService {
    @POST(ApiEndPoint.ENDPOINT_SERVER_LOGIN)
    @Headers(ApiHeaders.HEADER)
    Single<LoginResponse> loginRequest(@Body LoginRequest loginRequest);
}
