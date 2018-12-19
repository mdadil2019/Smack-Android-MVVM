package com.smack.mdadil2019.smack_mvvm.ui.login;

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
import com.smack.mdadil2019.smack_mvvm.data.network.model.LoginResponse;
import com.smack.mdadil2019.smack_mvvm.di.root.MyApp;

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

    private LoginViewModel getViewModel() {
        return ViewModelProviders.of(this,mViewModelFactory).get(LoginViewModel.class);
    }
}
