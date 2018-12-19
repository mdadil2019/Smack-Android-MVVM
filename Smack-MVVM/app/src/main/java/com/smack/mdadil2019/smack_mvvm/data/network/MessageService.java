package com.smack.mdadil2019.smack_mvvm.data.network;

import com.smack.mdadil2019.smack_mvvm.data.network.model.MessageResponse;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface MessageService {

    @GET(ApiEndPoint.ENDPOINT_GET_ALL_MESSAGES_OF_CHANNEL+"{channelId}")
    Observable<ArrayList<MessageResponse>> getAllMessages(@Path(value = "channelId") String channelId, @Header("Authorization") String token,
                                                          @Header("Content-Type") String type);
}
