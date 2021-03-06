package com.alexanderplyaka.weatherexchangerate.receiver;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.alexanderplyaka.weatherexchangerate.preferences.Preferences;
import com.alexanderplyaka.weatherexchangerate.service.NotificationService;
import com.alexanderplyaka.weatherexchangerate.widget.LargeWidgetProvider;
import com.alexanderplyaka.weatherexchangerate.widget.SmallWidgetProvider;

public class StartupReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent2 = new Intent(context, LargeWidgetProvider.class);
        intent2.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        int ids[] = AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context , LargeWidgetProvider.class));
        intent2.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
        context.sendBroadcast(intent2);

        intent2 = new Intent(context, LargeWidgetProvider.class);
        intent2.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        ids = AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context , SmallWidgetProvider.class));
        intent2.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
        context.sendBroadcast(intent2);

        NotificationService.setNotificationServiceAlarm(context , new Preferences(context).getNotifs());
    }
}

