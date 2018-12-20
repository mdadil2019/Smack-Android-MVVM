package com.smack.mdadil2019.smack_mvvm.ui.login;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.anonymanager.mdadil2019.smack_mvvm.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.smack.mdadil2019.smack_mvvm.data.network.model.LoginResponse;
import com.smack.mdadil2019.smack_mvvm.di.root.MyApp;
import com.smack.mdadil2019.smack_mvvm.ui.signup.RegistrationActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.view.View.VISIBLE;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.inputTextUserName)
    TextInputEditText userNameEt;

    @BindView(R.id.passwordInputTxt)
    TextInputEditText passwordEt;


    @BindView(R.id.pgBarLogin)
    ProgressBar loginPgBar;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        ((MyApp)getApplication()).getApplicationComponent().inject(this);
        loginViewModel = getViewModel();
        setObservers();
    }

    private void setObservers() {
        loginViewModel.loginResponseLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
                loginPgBar.setVisibility(View.INVISIBLE);
                loginViewModel.setLoggedIn();
                //start new activity
            }
        });

        loginViewModel.errorResponse.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.loginBtn)void loginClicked(){
        String userName = userNameEt.getText().toString();
        String password = passwordEt.getText().toString();
        if(loginViewModel.inputsAreValid(userName,password)){
            loginPgBar.setVisibility(VISIBLE);
            loginViewModel.login(userName,password);
        }
    }

    @OnClick(R.id.textViewSignup)void openSignUpActivity(){
        startActivity(new Intent(this,RegistrationActivity.class));
        finish();
    }


    private LoginViewModel getViewModel() {
        return ViewModelProviders.of(this,mViewModelFactory).get(LoginViewModel.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.INTERNET,Manifest.permission.ACCESS_WIFI_STATE,Manifest.permission.ACCESS_NETWORK_STATE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if(!report.areAllPermissionsGranted()){
                            Toast.makeText(LoginActivity.this, "You can't access the features of the app without permissions", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                }).check();
    }
}
