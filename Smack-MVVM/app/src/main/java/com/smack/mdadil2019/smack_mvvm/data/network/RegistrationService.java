package com.smack.mdadil2019.smack_mvvm.data.network;

import com.smack.mdadil2019.smack_mvvm.data.network.model.RegistrationRequest;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RegistrationService {
    @POST(ApiEndPoint.ENDPOINT_SERVER_REGISTER)
    @Headers(ApiHeaders.HEADER)
    Observable<ResponseBody> registerRequest(@Body RegistrationRequest registrationRequest);
}
