package com.smack.mdadil2019.smack_mvvm.di.root;

import com.smack.mdadil2019.smack_mvvm.di.LoginActivityModule;
import com.smack.mdadil2019.smack_mvvm.di.NavDrawerModule;
import com.smack.mdadil2019.smack_mvvm.di.RegistrationActivityModule;
import com.smack.mdadil2019.smack_mvvm.ui.chat.NavDrawer;
import com.smack.mdadil2019.smack_mvvm.ui.login.LoginActivity;
import com.smack.mdadil2019.smack_mvvm.ui.signup.RegistrationActivity;

import dagger.Component;

@Component(modules = {ApplicationModule.class, LoginActivityModule.class, RegistrationActivityModule.class, NavDrawerModule.class})
public interface ApplicationComponent {
    void inject(LoginActivity loginActivity);

    void inject(RegistrationActivity registrationActivity);

    void inject(NavDrawer navDrawer);
}
