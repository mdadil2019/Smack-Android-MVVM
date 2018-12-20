package com.smack.mdadil2019.smack_mvvm.di.root;

import android.app.Application;

import com.smack.mdadil2019.smack_mvvm.di.LoginActivityModule;
import com.smack.mdadil2019.smack_mvvm.di.NavDrawerModule;
import com.smack.mdadil2019.smack_mvvm.di.RegistrationActivityModule;

public class MyApp extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .loginActivityModule(new LoginActivityModule())
                .registrationActivityModule(new RegistrationActivityModule())
                .navDrawerModule(new NavDrawerModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }
}
