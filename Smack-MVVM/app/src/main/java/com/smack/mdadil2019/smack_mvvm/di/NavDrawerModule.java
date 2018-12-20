package com.smack.mdadil2019.smack_mvvm.di;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import com.smack.mdadil2019.smack_mvvm.ViewModelProviderFactory;
import com.smack.mdadil2019.smack_mvvm.data.network.ChannelService;
import com.smack.mdadil2019.smack_mvvm.data.network.MessageService;
import com.smack.mdadil2019.smack_mvvm.data.network.model.ChannelResponse;
import com.smack.mdadil2019.smack_mvvm.data.prefs.AppPreferencesHelper;
import com.smack.mdadil2019.smack_mvvm.ui.chat.NavDrawerViewModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class NavDrawerModule {


    @Provides
    @Named("NavDrawerViewModelFactory")
    ViewModelProvider.Factory provideNavDrawerViewModelFactory(NavDrawerViewModel viewModel){
        return new ViewModelProviderFactory<>(viewModel);
    }


    @Provides
    NavDrawerViewModel provideNavDrawerViewModel(Context context, AppPreferencesHelper appPreferencesHelper, ChannelService channelService,
                                                     ChannelResponse channelResponse, MessageService messageService){
        return new NavDrawerViewModel(context, appPreferencesHelper, channelService,channelResponse,messageService);
    }

    @Provides
    ChannelService provideChannelService(Retrofit retrofit){
        return retrofit.create(ChannelService.class);
    }

    @Provides
    MessageService provideMessageService(Retrofit retrofit){
        return retrofit.create(MessageService.class);
    }

    @Provides
    ChannelResponse provideChannelResponse(){
        return new ChannelResponse();
    }


}
