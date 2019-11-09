package com.example.practica1.Sessions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.example.practica1.Clases.LoginActivity;
import com.example.practica1.Entidades.Usuario;
import com.example.practica1.Sessions.Constants;

public class UserSession {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context mContext;
    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String FIRST_TIME_LOGGED_IN = "firstTimeLoggedIn";
    public static final String KEY_ID = "id_user";
    public static final String KEY_NAME = "name";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_TYPE = "type";

    public UserSession(Context mContext) {
        this.mContext = mContext;
        sharedPreferences = mContext.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Constants.SHARED_PREFERENCES_PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(Usuario user) {

        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putLong(KEY_ID, user.getId());
        editor.putString(KEY_NAME, user.getNombre());
        editor.putString(KEY_USERNAME, user.getUsuario());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putInt(KEY_TYPE, Integer.parseInt(user.getAdmin()));

        editor.commit();
    }

    public void checkLogin() {

        if (!this.isLoggedIn()) {
            Intent intent = new Intent(mContext, LoginActivity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            mContext.startActivity(intent);
        }

    }

    public void logoutUser() {

        editor.putBoolean(IS_LOGGED_IN, false);
        editor.commit();

        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        mContext.startActivity(intent);
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

    public long getUserId() {
        return sharedPreferences.getLong(KEY_ID, -1);
    }

    public String getUserName() {
        return sharedPreferences.getString(KEY_NAME, null);
    }

    public String getUserUsername() {
        return sharedPreferences.getString(KEY_USERNAME, null);
    }

    public String getUserEmail() {
        return sharedPreferences.getString(KEY_EMAIL, null);
    }

    public int getUserType() { return sharedPreferences.getInt(KEY_TYPE, -1);}

    public Boolean getFirstTime() {
        return sharedPreferences.getBoolean(FIRST_TIME_LOGGED_IN, true);
    }

    public void setFirstTime(Boolean n) {
        editor.putBoolean(FIRST_TIME_LOGGED_IN, n);
        editor.commit();
    }
}