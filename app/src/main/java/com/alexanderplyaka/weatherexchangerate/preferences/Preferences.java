package com.alexanderplyaka.weatherexchangerate.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alexanderplyaka.weatherexchangerate.utils.Constants;

public class Preferences {
    private static SharedPreferences prefs;

    public Preferences(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public SharedPreferences getPrefs() {
        return prefs;
    }

    public String getCity() {
        return prefs.getString(Constants.CITY , null);
    }

    public void setCity(String city) {
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(Constants.CITY , city);
        prefsEditor.apply();
    }

    public void setLaunched() {
        prefs.edit().putBoolean(Constants.FIRST , true).apply();
    }

    public boolean getLaunched() {
        return prefs.getBoolean(Constants.FIRST , false);
    }

    public void setLastCity(String city) {
        prefs.edit().putString(Constants.LASTCITY , city).apply();
    }

    public String getLastCity() {
        return prefs.getString(Constants.LASTCITY , null);
    }

    public void setLatitude(float lat) {
        prefs.edit().putFloat(Constants.LATITUDE , lat).apply();
    }

    public float getLatitude() {
        return prefs.getFloat(Constants.LATITUDE , 0);
    }

    public void setLongitude(float lon) {
        prefs.edit().putFloat(Constants.LONGITUDE , lon).apply();
    }

    public float getLongitude() {
        return prefs.getFloat(Constants.LONGITUDE , 0);
    }

    public void setUnits(String string) {
        prefs.edit().putString(Constants.UNITS , string).apply();
    }

    public String getUnits() {
        return prefs.getString(Constants.UNITS , "metric");
    }

    public void setNotifs(Boolean bool) {
        prefs.edit().putBoolean(Constants.NOTIFICATIONS , bool).apply();
    }

    public Boolean getNotifs() {
        return prefs.getBoolean(Constants.NOTIFICATIONS , false);
    }

    public void setv3TargetShown(boolean bool) {
        prefs.edit().putBoolean(Constants.V3TUTORIAL , bool).apply();
    }

    public boolean getv3TargetShown() {
        return prefs.getBoolean(Constants.V3TUTORIAL , false);
    }

    public String getWeatherKey() {
        return prefs.getString(Constants.OWM_KEY , Constants.OWM_APP_ID);
    }
}
