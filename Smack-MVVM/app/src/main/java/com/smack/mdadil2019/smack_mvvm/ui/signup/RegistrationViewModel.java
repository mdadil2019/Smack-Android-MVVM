package com.smack.mdadil2019.smack_mvvm.ui.signup;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.smack.mdadil2019.smack_mvvm.data.network.CreateUserService;
import com.smack.mdadil2019.smack_mvvm.data.network.LoginService;
import com.smack.mdadil2019.smack_mvvm.data.network.RegistrationService;
import com.smack.mdadil2019.smack_mvvm.data.network.model.CreateUserRequest;
import com.smack.mdadil2019.smack_mvvm.data.network.model.CreateUserResponse;
import com.smack.mdadil2019.smack_mvvm.data.network.model.LoginRequest;
import com.smack.mdadil2019.smack_mvvm.data.network.model.LoginResponse;
import com.smack.mdadil2019.smack_mvvm.data.network.model.RegistrationRequest;
import com.smack.mdadil2019.smack_mvvm.data.prefs.AppPreferencesHelper;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

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

    MutableLiveData<CreateUserResponse> createResponseData;
    MutableLiveData<String> errorData;

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

        createResponseData= new MutableLiveData<>();
        errorData = new MutableLiveData<>();
    }

    public void register(final String userName, final String password, final String email) {
            registrationRequest.setEmail(email);
            registrationRequest.setPassword(password);
            Observable<CreateUserResponse> response = registrationService.registerRequest(registrationRequest)
                    .concatMap(new Function<ResponseBody, ObservableSource<? extends LoginResponse>>() {
                        @Override
                        public ObservableSource<? extends LoginResponse> apply(ResponseBody responseBody) throws Exception {
                            if(responseBody.string().contains("Successfully")){
                                loginRequest.setPassword(password);
                                loginRequest.setEmail(email);
                                return loginService.loginRequest(loginRequest).toObservable();
                            }
                            return null;
                        }
                    }).concatMap(new Function<LoginResponse, ObservableSource<? extends CreateUserResponse>>() {
                        @Override
                        public ObservableSource<? extends CreateUserResponse> apply(LoginResponse loginResponse) throws Exception {
                            String token = loginResponse.getToken(); //save the token in shared preferences
                            sharedPreferences.setAuthToken(token);
                            createUserRequest.setEmail(email);
                            createUserRequest.setName(userName);
                            createUserRequest.setAvatarColor(avatarColor);
                            avatarName = "dark" + (userName.length() % 27);
                            createUserRequest.setAvatarName(avatarName);
                            return createUserService.createUser(createUserRequest,"Bearer " + token,"application/json; charset=utf-8");
                        }
                    });
            response.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<CreateUserResponse>() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onNext(CreateUserResponse createUserResponse) {
                    createResponseData.setValue(createUserResponse);
                }

                @Override
                public void onError(Throwable e) {
                    errorData.setValue(e.getMessage());
                }

                @Override
                public void onComplete() {

                }
            });
    }

    public void saveDataToPrefs(CreateUserResponse createUserResponse){
        sharedPreferences.saveId(createUserResponse.getId());
        sharedPreferences.saveUserName(createUserResponse.getName());
        sharedPreferences.saveAvatarColor(createUserResponse.getAvatarColor());
        sharedPreferences.saveAvatarName(createUserResponse.getAvatarName());
    }


    public boolean isInputValid(String userName, String password, String email) {
        if(userName!=null && !userName.equals("") && password!=null && !password.equals("") && email!=null && !email.equals(""))
            return true;
        return false;
    }
}
