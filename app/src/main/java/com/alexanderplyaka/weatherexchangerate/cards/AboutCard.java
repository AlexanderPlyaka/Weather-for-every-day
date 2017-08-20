package com.alexanderplyaka.weatherexchangerate.cards;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;

import com.alexanderplyaka.weatherexchangerate.R;

import it.gmariotti.cardslib.library.internal.Card;

public class AboutCard extends Card {
    private int type;
    private Context context;

    public AboutCard(Context context, int innerLayout, int type) {
        super(context, innerLayout);
        this.type = type;
        this.context = context;
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);
        switch (type) {
            case 1:
                (parent.findViewById(R.id.name)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(context.getString(R.string.github_link)));
                        context.startActivity(intent);
                    }
                });
                break;
            case 2:
                (parent.findViewById(R.id.credits)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(context.getString(R.string.dev_2_link)));
                        context.startActivity(intent);
                    }
                });
                break;
            case 3:
                parent.findViewById(R.id.license_layout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(context.getString(R.string.license_link)));
                        context.startActivity(intent);
                    }
                });
                break;
        }
    }
}
