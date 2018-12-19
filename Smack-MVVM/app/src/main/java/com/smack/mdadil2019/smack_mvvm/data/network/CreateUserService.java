package com.smack.mdadil2019.smack_mvvm.data.network;

import com.smack.mdadil2019.smack_mvvm.data.network.model.CreateUserRequest;
import com.smack.mdadil2019.smack_mvvm.data.network.model.CreateUserResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CreateUserService {

    @POST(ApiEndPoint.ENDPOINT_SERVER_CREATE)
    Observable<CreateUserResponse> createUser(@Body CreateUserRequest createUserRequest, @Header("Authorization") String token,
                                              @Header("Content-Type") String type);
}
