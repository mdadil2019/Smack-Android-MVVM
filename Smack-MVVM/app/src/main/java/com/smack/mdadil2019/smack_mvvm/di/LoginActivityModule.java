package com.smack.mdadil2019.smack_mvvm.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smack.mdadil2019.smack_mvvm.ViewModelProviderFactory;
import com.smack.mdadil2019.smack_mvvm.data.network.ApiEndPoint;
import com.smack.mdadil2019.smack_mvvm.data.network.LoginService;
import com.smack.mdadil2019.smack_mvvm.data.network.model.LoginRequest;
import com.smack.mdadil2019.smack_mvvm.data.prefs.AppPreferencesHelper;
import com.smack.mdadil2019.smack_mvvm.ui.login.LoginViewModel;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class LoginActivityModule {

    @Provides
    public ViewModelProvider.Factory provideLoginActivityViewModel(LoginViewModel loginViewModel){
        return new ViewModelProviderFactory<>(loginViewModel);
    }

    @Provides
    public LoginViewModel provideLoginViewModel(LoginService loginService, LoginRequest loginRequest,
                                                AppPreferencesHelper preferencesHelper){
        return new LoginViewModel(loginService,loginRequest,preferencesHelper);
    }




    @Provides
    public LoginRequest provideLoginRequest(){
       return new LoginRequest();
    }

    @Provides
    public LoginService provideLoginService(){
        return provideRetrofit(provideOkHttpClient(),provideGson()).create(LoginService.class);
    }


    @Provides
    public Retrofit provideRetrofit( OkHttpClient okHttpClient, Gson gson){
        return new Retrofit.Builder()
                .baseUrl(ApiEndPoint.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    public Gson provideGson(){
        return new GsonBuilder().setLenient().create();
    }

    @Provides
    public OkHttpClient provideOkHttpClient(){
        return new OkHttpClient.Builder().
                readTimeout(20,TimeUnit.SECONDS)
                .connectTimeout(20,TimeUnit.SECONDS)
                .build();
    }

}
