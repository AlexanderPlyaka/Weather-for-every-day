package com.alexanderplyaka.weatherexchangerate.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.alexanderplyaka.weatherexchangerate.R;
import com.alexanderplyaka.weatherexchangerate.fragments.FirstLaunchFragment;

public class FirstLaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_launch);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Динамическое добавление фрагмента в activity
        // Метод getSupportFragmentManager() возвращает объект FragmentManager
        // Первым аргументом передается ресурс разметки, в который надо добавить фрагмент
        getSupportFragmentManager().beginTransaction().add(R.id.fragment, new FirstLaunchFragment()).commit();
        setSupportActionBar(toolbar);
    }
}
