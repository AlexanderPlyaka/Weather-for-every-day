package com.alexanderplyaka.weatherexchangerate.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.alexanderplyaka.weatherexchangerate.R;
import com.alexanderplyaka.weatherexchangerate.internet.CheckConnection;
import com.alexanderplyaka.weatherexchangerate.internet.Request;
import com.alexanderplyaka.weatherexchangerate.model.WeatherInfo;
import com.alexanderplyaka.weatherexchangerate.preferences.Preferences;
import com.alexanderplyaka.weatherexchangerate.preferences.SWPrefs;
import com.alexanderplyaka.weatherexchangerate.utils.Utils;

import java.io.IOException;
import java.util.Locale;

public class SmallWidgetService extends IntentService {

    private static final String TAG = "smallWidgetService";

    public SmallWidgetService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        CheckConnection cc = new CheckConnection(this);
        if (!cc.isNetworkAvailable())
            return;

        SWPrefs SWPrefs = new SWPrefs(this);
        String city = SWPrefs.getCity();
        String units = SWPrefs.getUnits();

        try {
            WeatherInfo weatherRaw = new Request(this).getItems(city, units);
            SWPrefs.saveWeather(weatherRaw);
            updateWidget(weatherRaw);
        } catch (IOException e) {
            Log.e(TAG, "Could not retrieve Weather", e);
            stopSelf();
        }
    }

    private void updateWidget(WeatherInfo weather) {
        AppWidgetManager widgetManager = AppWidgetManager.getInstance(this);
        ComponentName widgetComponent = new ComponentName(this, SmallWidgetProvider.class);

        Preferences preferences = new Preferences(this);

        int[] widgetIds = widgetManager.getAppWidgetIds(widgetComponent);
        for (int appWidgetId : widgetIds) {
            String temperatureScale = preferences.getUnits().equals("metric") ? getString(R.string.c) : getString(R.string.f);

            String temperature = String.format(Locale.getDefault(), "%.0f", weather.getMain().getTemp());
            int iconId = weather.getWeather().get(0).getId();
            String weatherIcon = Utils.getStrIcon(iconId, this);

            RemoteViews remoteViews = new RemoteViews(this.getPackageName(),
                    R.layout.widget_small);
            remoteViews.setTextViewText(R.id.widget_city, weather.getName() + ", " + weather.getSys().getCountry());
            remoteViews.setTextViewText(R.id.widget_temperature, temperature + temperatureScale);

            remoteViews.setImageViewBitmap(R.id.widget_icon,
                    Utils.createWeatherIcon(this, weatherIcon));

            widgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }
}
