package com.smack.mdadil2019.smack_mvvm.data.network;

public final class ApiEndPoint {
    public static final String BASE_URL = "https://adilchat.herokuapp.com/v1/";

    public static final String ENDPOINT_SERVER_LOGIN = BASE_URL + "account/login/";

    public static final String ENDPOINT_SERVER_REGISTER = BASE_URL + "account/register";

    public static final String ENDPOINT_SERVER_CREATE = BASE_URL + "user/add";


    public static final String ENDPOINT_GET_ALL_CHANNELS = BASE_URL + "channel/";

    public static final String ENDPOINT_GET_ALL_MESSAGES_OF_CHANNEL = BASE_URL + "message/byChannel/";

}
