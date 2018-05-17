package me.daylight.talk.utils;

import me.daylight.talk.app.MyApplication;

public class GlobalField {
    public static String url="http://"+SharedPreferencesUtil.getString(MyApplication.getApplication().getApplicationContext(),
            GlobalField.SETTING,GlobalField.URL)+"/";
    public static final String USER="User";
    public static final String SETTING="Setting";

    public static final String ACCOUNT="Account";
    public static final String STATE="state";
    public static final String URL="url";

    public static final int MessageType_Send=1;
    public static final int MessageType_Receive=2;

    public static final int REQUEST_CODE_CHOOSE=100;
}
