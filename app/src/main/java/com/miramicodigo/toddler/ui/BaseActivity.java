package com.miramicodigo.toddler.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
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

    protected MainActivity mainActivity;

    protected void injectViews() {
        ButterKnife.bind(this);
    }

    protected void showMessage(View container, String message, int type, int time) {
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

    protected Boolean estaConectado(CoordinatorLayout coordinatorLayout){
        if(!conectadoWifi()){
            if(!conectadoRedMovil()) {
                showMessage(coordinatorLayout, "Tu Dispositivo no tiene Conexion a Internet, por favor conectate y vuelve a intentarlo", 1, 3000);
                return false;
            }
        }
        return true;
    }

    protected Boolean conectadoWifi(){
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    protected Boolean conectadoRedMovil(){
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setIcon((status) ? R.drawable.ic_clear_all_white_24dp : R.drawable.ic_keyboard_arrow_right_black_24dp);

        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.hide();
            }
        });
        alertDialog.show();
    }
}
