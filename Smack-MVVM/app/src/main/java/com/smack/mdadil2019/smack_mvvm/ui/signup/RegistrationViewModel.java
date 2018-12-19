package com.smack.mdadil2019.smack_mvvm.ui.signup;

import android.arch.lifecycle.ViewModel;

import com.smack.mdadil2019.smack_mvvm.data.network.CreateUserService;
import com.smack.mdadil2019.smack_mvvm.data.network.LoginService;
import com.smack.mdadil2019.smack_mvvm.data.network.RegistrationService;
import com.smack.mdadil2019.smack_mvvm.data.network.model.CreateUserRequest;
import com.smack.mdadil2019.smack_mvvm.data.network.model.LoginRequest;
import com.smack.mdadil2019.smack_mvvm.data.network.model.RegistrationRequest;
import com.smack.mdadil2019.smack_mvvm.data.prefs.AppPreferencesHelper;

public class RegistrationViewModel extends ViewModel {

    RegistrationService registrationService;
    RegistrationRequest registrationRequest;
    LoginService loginService;
    LoginRequest loginRequest;
    CreateUserRequest createUserRequest;
    CreateUserService createUserService;
    AppPreferencesHelper sharedPreferences;

    String avatarName;
    String avatarColor;

    public RegistrationViewModel(RegistrationRequest regRequest, RegistrationService regService, LoginService loginSer,
                                 LoginRequest loginReq, CreateUserRequest createUserReq, CreateUserService createUserSer,
                                 AppPreferencesHelper prefs){
        registrationRequest = regRequest;
        registrationService = regService;
        loginService = loginSer;
        loginRequest = loginReq;
        createUserRequest = createUserReq;
        createUserService = createUserSer;
        sharedPreferences = prefs;
    }
}
