package com.smack.mdadil2019.smack_mvvm.di.root;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Application application;
    public ApplicationModule(Application application){
        this.application = application;
    }

    @Provides
    public Context provideContext(){
        return  application;
    }
}
