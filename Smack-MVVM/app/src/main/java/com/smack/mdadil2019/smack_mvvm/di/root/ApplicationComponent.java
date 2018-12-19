package com.smack.mdadil2019.smack_mvvm.di.root;

import com.smack.mdadil2019.smack_mvvm.di.LoginActivityModule;
import com.smack.mdadil2019.smack_mvvm.di.RegistrationActivityModule;
import com.smack.mdadil2019.smack_mvvm.ui.login.LoginActivity;
import com.smack.mdadil2019.smack_mvvm.ui.signup.RegistrationActivity;

import dagger.Component;

@Component(modules = {ApplicationModule.class, LoginActivityModule.class, RegistrationActivityModule.class})
public interface ApplicationComponent {
    void inject(LoginActivity loginActivity);

    void inject(RegistrationActivity registrationActivity);
}
