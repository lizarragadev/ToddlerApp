package com.miramicodigo.toddler.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.miramicodigo.toddler.R;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    static final int RES_DESARROLLADO = 3;
    static final int RES_EN_PROGRESO = 2;
    static final int RES_PREOCUPANTE = 1;

    static final int MOTOR_GRUESO = 1;
    static final int MOTOR_FINO = 2;
    static final int LENGUAJE = 3;

    static final String PREOCUPANTE = "PREOCUPANTE";
    static final String EN_PROGRESO = "EN PROGRESO";
    static final String DESARROLLADO = "DESARROLLADO";

    protected void injectViews() {
        ButterKnife.bind(this);
    }

    protected void showMessage(View container, String message, int type) {
        Snackbar snackbar = Snackbar.make(container, message, 4000);
        View sbView = snackbar.getView();
        if (type == RES_PREOCUPANTE) {
            sbView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_preocupante));
        } else {
            if(type == RES_EN_PROGRESO) {
                sbView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_enprogreso));
            }else {
                sbView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_desarrollado));
            }
        }
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }
}
