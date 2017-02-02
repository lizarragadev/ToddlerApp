package com.miramicodigo.toddler.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    private static final int SUCCESS = 3;
    private static final int WARNING = 2;
    private static final int ERROR = 1;

    protected void injectViews() {
        ButterKnife.bind(this);
    }

    protected void showMessage(View container, String message, int type) {
        Snackbar snackbar = Snackbar.make(container,message, Snackbar.LENGTH_LONG);
        if (type == ERROR) {
            snackbar.setActionTextColor(Color.RED);
        } else {
            if(type == 2) {
                snackbar.setActionTextColor(Color.YELLOW);
            }else {
                snackbar.setActionTextColor(Color.GREEN);
            }
        }
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

    protected void next(Bundle bundle, Class<?> activity, boolean destroy) {
        Intent intent= new Intent(this,activity);
        if(bundle!=null){intent.putExtras(bundle);}
        startActivity(intent);
        if(destroy)finish();
    }
}
