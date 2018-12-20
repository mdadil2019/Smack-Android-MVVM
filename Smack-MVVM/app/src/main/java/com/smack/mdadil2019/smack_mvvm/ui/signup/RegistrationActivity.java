package com.smack.mdadil2019.smack_mvvm.ui.signup;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.anonymanager.mdadil2019.smack_mvvm.R;
import com.smack.mdadil2019.smack_mvvm.data.network.model.CreateUserResponse;
import com.smack.mdadil2019.smack_mvvm.di.root.MyApp;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class RegistrationActivity extends AppCompatActivity {

    @Inject
    @Named("RegistrationFactory")

    ViewModelProvider.Factory mFactory;

    RegistrationViewModel registrationViewModel;


    @BindView(R.id.textInputUserNameReg)
    TextInputEditText userNameEt;

    @BindView(R.id.textInputEmailReg)
    TextInputEditText emailEt;

    @BindView(R.id.textInputPasswordReg)
    TextInputEditText passwordEt;

    @BindView(R.id.progressBarEmail)
    ProgressBar pgBarRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        ((MyApp)getApplication()).getApplicationComponent().inject(this);
        getViewModel();
        setObservers();
    }

    private void setObservers() {
        registrationViewModel.createResponseData.observe(this, new Observer<CreateUserResponse>() {
            @Override
            public void onChanged(@Nullable CreateUserResponse createUserResponse) {
                pgBarRegistration.setVisibility(INVISIBLE);
                //save data to preferences
                registrationViewModel.saveDataToPrefs(createUserResponse);

                //start another activity
                Toast.makeText(RegistrationActivity.this, createUserResponse.getEmail(), Toast.LENGTH_SHORT).show();
            }
        });

        registrationViewModel.errorData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                pgBarRegistration.setVisibility(INVISIBLE);
                Toast.makeText(RegistrationActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.createAccountBtn)void createAccount(){
        String userName = userNameEt.getText().toString();
        String password = passwordEt.getText().toString();
        String email = emailEt.getText().toString();
        if(registrationViewModel.isInputValid(userName,password,email)){
            pgBarRegistration.setVisibility(VISIBLE);
            registrationViewModel.register(userName,password,email);

        }else{
            Toast.makeText(this, "Input is not valid", Toast.LENGTH_SHORT).show();
        }
    }

    private void getViewModel() {
        registrationViewModel = ViewModelProviders.of(this,mFactory).get(RegistrationViewModel.class);
    }


}
