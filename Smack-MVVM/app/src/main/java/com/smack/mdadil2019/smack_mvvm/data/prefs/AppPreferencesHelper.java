package com.smack.mdadil2019.smack_mvvm.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferencesHelper implements PreferencesHelper{

    private static final String PREF_KEY_AUTH_TOKEN = "PREF_KEY_AUTH_TOKEN";
    private static final String PREF_USERNAME_KEY = "PREF_USERNAME_KEY";
    private static final String PREF_AVATAR_NAME_KEY = "PREF_AVATAR_NAME_KEY";
    private static final String PREF_AVATAR_COLOR_KEY = "PREF_AVATAR_COLOR_KEY";
    private static final String PREF_USER_ID_KEY = "PREF_USER_ID_KEY";
    private static final String PREF_USER_LOGIN_KEY = "PREF_USER_LOGIN_KEY";

    private static final String LOCAL_PREF_KEY = "LOCAL_PREF_KEY";
    private final SharedPreferences mPrefs;

    public AppPreferencesHelper(Context context){
        mPrefs = context.getSharedPreferences(LOCAL_PREF_KEY,Context.MODE_PRIVATE);
    }

    @Override
    public String getAuthToken() {
        return mPrefs.getString(PREF_KEY_AUTH_TOKEN,null);
    }

    @Override
    public void setAuthToken(String authToken) {
        mPrefs.edit().putString(PREF_KEY_AUTH_TOKEN,authToken).apply();
    }

    @Override
    public void saveUserName(String userName) {
        mPrefs.edit().putString(PREF_USERNAME_KEY,userName).apply();
    }

    @Override
    public String getUserName() {
        return mPrefs.getString(PREF_USERNAME_KEY,null);
    }

    @Override
    public String getAvatarName() {
        return mPrefs.getString(PREF_AVATAR_NAME_KEY,null);
    }

    @Override
    public void saveAvatarName(String avatarName) {
        mPrefs.edit().putString(PREF_AVATAR_NAME_KEY,avatarName).apply();
    }

    @Override
    public void saveAvatarColor(String avatarColor) {
        mPrefs.edit().putString(PREF_AVATAR_COLOR_KEY,avatarColor).apply();
    }

    @Override
    public void saveId(String id) {
        mPrefs.edit().putString(PREF_USER_ID_KEY,id).apply();
    }

    @Override
    public String getId() {
        return mPrefs.getString(PREF_USER_ID_KEY,null);
    }

    @Override
    public String getAvatarColor() {
        return mPrefs.getString(PREF_AVATAR_COLOR_KEY,null);
    }

    @Override
    public void setLoggedIn(boolean state) {
        mPrefs.edit().putBoolean(PREF_USER_LOGIN_KEY,state).apply();
    }

    @Override
    public boolean getLoggedInStatus() {
        return mPrefs.getBoolean(PREF_USER_LOGIN_KEY,false);
    }
}
