package com.alexanderplyaka.weatherexchangerate.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alexanderplyaka.weatherexchangerate.model.WeatherInfo;
import com.alexanderplyaka.weatherexchangerate.utils.Constants;

public class SWPrefs {
    private static SharedPreferences prefs;
    private Context context;

    public SWPrefs(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public SharedPreferences getPrefs() {
        return prefs;
    }

    public String getCity() {
        return new Preferences(context).getCity();
    }

    public void setCity(String city) {
        new Preferences(context).setCity(city);
    }

    public String getUnits() {
        return new Preferences(context).getUnits();
    }

    public void setTemperature(double temp) {
        prefs.edit().putLong(Constants.LARGE_WIDGET_TEMPERATURE , Double.doubleToLongBits(temp)).apply();
    }

    public double getTemperature() {
        return Double.longBitsToDouble(prefs.getLong(Constants.LARGE_WIDGET_TEMPERATURE , 0));
    }

    public void setIcon(int id) {
        prefs.edit().putInt(Constants.LARGE_WIDGET_ICON , id).apply();
    }

    public int getIcon() {
        return prefs.getInt(Constants.LARGE_WIDGET_ICON , 500);
    }

    private void setCountry(String country) {
        prefs.edit().putString(Constants.LARGE_WIDGET_COUNTRY , country).apply();
    }

    public String getCountry() {
        return prefs.getString(Constants.LARGE_WIDGET_COUNTRY , "IN");
    }

    public void saveWeather(WeatherInfo weather) {
        setTemperature(weather.getMain().getTemp());
        setIcon(weather.getWeather().get(0).getId());
        setCountry(weather.getSys().getCountry());
    }
}
