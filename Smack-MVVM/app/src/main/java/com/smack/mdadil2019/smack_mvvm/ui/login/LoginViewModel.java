package com.smack.mdadil2019.smack_mvvm.ui.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.smack.mdadil2019.smack_mvvm.data.network.LoginService;
import com.smack.mdadil2019.smack_mvvm.data.network.model.LoginRequest;
import com.smack.mdadil2019.smack_mvvm.data.network.model.LoginResponse;
import com.smack.mdadil2019.smack_mvvm.data.prefs.AppPreferencesHelper;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {
    private LoginService loginService;
    private LoginRequest loginRequest;
    private AppPreferencesHelper mPrefs;
    private Disposable disposable;

    //Observable
    MutableLiveData<String> loginResponseLiveData;
    MutableLiveData<String> errorResponse;

    public LoginViewModel(LoginService loginApiService, LoginRequest loginReq, AppPreferencesHelper preferencesHelper){
        loginService = loginApiService;
        loginRequest = loginReq;
        mPrefs = preferencesHelper;
        loginResponseLiveData = new MutableLiveData<>();
        errorResponse = new MutableLiveData<>();
    }


    public void login(String userName, String password) {
            loginRequest.setEmail(userName);
            loginRequest.setPassword(password);
            Single<LoginResponse> loginResponse = loginService.loginRequest(loginRequest);
            loginResponse.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<LoginResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(LoginResponse loginResponse) {
                            loginResponseLiveData.setValue(loginResponse.getUser());
                        }

                        @Override
                        public void onError(Throwable e) {
                            loginResponseLiveData.setValue(e.getMessage());
                        }
                    });
//                    view.openNavigationDrawer();
    }

    public void setLoggedIn(){
        mPrefs.setLoggedIn(true);
    }

    public boolean isLoggedIn(){
        return mPrefs.getLoggedInStatus();
    }

    public boolean inputsAreValid(String uName, String password){
        if(uName!=null && !uName.equals("") && password!=null && !password.equals(""))
            return true;
        return false;
    }


}
