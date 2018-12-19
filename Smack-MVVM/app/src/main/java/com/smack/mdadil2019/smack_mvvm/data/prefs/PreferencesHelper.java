package com.smack.mdadil2019.smack_mvvm.data.prefs;

public interface PreferencesHelper {

    String getAuthToken();

    void setAuthToken(String authToken);

    void saveUserName(String userName);

    String getUserName();

    String getAvatarName();

    void saveAvatarName(String avatarName);

    void saveAvatarColor(String avatarColor);

    String getAvatarColor();

    void saveId(String id);

    String getId();

    void setLoggedIn(boolean state);

    boolean getLoggedInStatus();

}
