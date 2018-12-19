package com.smack.mdadil2019.smack_mvvm.ui.signup;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anonymanager.mdadil2019.smack_mvvm.R;
import com.smack.mdadil2019.smack_mvvm.di.root.MyApp;

import javax.inject.Inject;
import javax.inject.Named;

public class RegistrationActivity extends AppCompatActivity {

    @Inject
    @Named("RegistrationFactory")

    ViewModelProvider.Factory mFactory;

    RegistrationViewModel registrationViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ((MyApp)getApplication()).getApplicationComponent().inject(this);
    }


}
