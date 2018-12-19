package com.smack.mdadil2019.smack_mvvm.di;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import com.smack.mdadil2019.smack_mvvm.ViewModelProviderFactory;
import com.smack.mdadil2019.smack_mvvm.data.network.CreateUserService;
import com.smack.mdadil2019.smack_mvvm.data.network.LoginService;
import com.smack.mdadil2019.smack_mvvm.data.network.RegistrationService;
import com.smack.mdadil2019.smack_mvvm.data.network.model.CreateUserRequest;
import com.smack.mdadil2019.smack_mvvm.data.network.model.LoginRequest;
import com.smack.mdadil2019.smack_mvvm.data.network.model.RegistrationRequest;
import com.smack.mdadil2019.smack_mvvm.data.prefs.AppPreferencesHelper;
import com.smack.mdadil2019.smack_mvvm.di.LoginActivityModule;
import com.smack.mdadil2019.smack_mvvm.ui.signup.RegistrationViewModel;

import javax.inject.Named;
import javax.inject.Qualifier;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {LoginActivityModule.class})
public class RegistrationActivityModule {


    @Provides
    @Named("RegistrationFactory")
    ViewModelProvider.Factory provideRegistrationViewModelFactory(RegistrationViewModel registrationViewModel){
        return new ViewModelProviderFactory<>(registrationViewModel);
    }

    @Provides
    RegistrationViewModel provideRegistrationViewModel(RegistrationRequest registrationRequest,
                                                       RegistrationService registrationService, LoginService loginService,
                                                       LoginRequest loginRequest, CreateUserService createUserService,
                                                       CreateUserRequest createUserRequest, AppPreferencesHelper prefs){
        return new RegistrationViewModel(registrationRequest,registrationService, loginService, loginRequest,createUserRequest,
                createUserService,prefs);
    }

    @Provides
    AppPreferencesHelper provideSharedPrefs(Context context){
        return new AppPreferencesHelper(context);
    }

    @Provides
    CreateUserService provideCreateUserService(Retrofit retrofit){
        return retrofit.create(CreateUserService.class);
    }

    @Provides
    CreateUserRequest provideCreateUserRequest(){
        return new CreateUserRequest();
    }

    @Provides
    RegistrationRequest provideRegistrationRequest(){
        return new RegistrationRequest();
    }

    @Provides
    RegistrationService provideRegistrationService(Retrofit retrofit){
        return retrofit.create(RegistrationService.class);
    }
}
