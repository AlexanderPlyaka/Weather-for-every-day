package com.alexanderplyaka.weatherexchangerate.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.alexanderplyaka.weatherexchangerate.R;
import com.alexanderplyaka.weatherexchangerate.preferences.Preferences;

import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

public class MapsFragment extends Fragment {

    public View rootView;
    WebView webView;
    BottomNavigation mBottomBar;
    Preferences preferences;

    public MapsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_maps, container, false);
        webView = rootView.findViewById(R.id.webView);
        preferences = new Preferences(getContext());
        loadMap();
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void loadMap() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/map.html?lat=" + preferences.getLatitude() + "&lon=" + preferences.getLongitude() + "&k=2.0" + "&appid=" + preferences.getWeatherKey());
        webView.setInitialScale(1);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        mBottomBar = rootView.findViewById(R.id.bottomBar);
        mBottomBar.setOnMenuItemClickListener(new BottomNavigation.OnMenuItemSelectionListener() {
            @Override
            public void onMenuItemSelect(@IdRes int i, int i1, boolean b) {
                if (i == R.id.map_rain) {
                    webView.loadUrl("javascript:map.removeLayer(windLayer);map.removeLayer(tempLayer);map.addLayer(rainLayer);");
                } else if (i == R.id.map_wind) {
                    webView.loadUrl("javascript:map.removeLayer(rainLayer);map.removeLayer(tempLayer);map.addLayer(windLayer);");
                } else if (i == R.id.map_temperature) {
                    webView.loadUrl("javascript:map.removeLayer(windLayer);map.removeLayer(rainLayer);map.addLayer(tempLayer);");
                }
            }

            @Override
            public void onMenuItemReselect(@IdRes int i, int i1, boolean b) {

            }
        });
    }
}
