package com.alexanderplyaka.weatherexchangerate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alexanderplyaka.weatherexchangerate.activity.FirstLaunchActivity;
import com.alexanderplyaka.weatherexchangerate.activity.WeatherActivity;
import com.alexanderplyaka.weatherexchangerate.preferences.Preferences;

public class MainActivity extends AppCompatActivity {

    public static Preferences preferences;
    public static int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Loaded" , "Global");
    }

    @Override
    protected void onResume() {
        preferences = new Preferences(this);
        super.onResume();

        if (!preferences.getPrefs().getBoolean("first" , true)) {
            preferences.setLaunched();
            preferences.setCity(preferences.getCity());
        }

        super.onResume();
        Intent intent;

        if (preferences.getLaunched()) {
            intent = new Intent(MainActivity.this, WeatherActivity.class);
        }
        else {
            intent = new Intent(MainActivity.this, FirstLaunchActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
