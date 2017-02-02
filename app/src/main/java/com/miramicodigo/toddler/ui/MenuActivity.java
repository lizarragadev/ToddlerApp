package com.miramicodigo.toddler.ui;

import android.content.Context;
import android.os.Bundle;

import com.miramicodigo.toddler.R;
import com.miramicodigo.toddler.model.entity.Preguntas;
import com.miramicodigo.toddler.presenter.ToddlerPresenter;
import com.miramicodigo.toddler.view.ToddlerView;

public class MenuActivity extends BaseActivity implements ToddlerView {

    private static final String TAG = "MenuActivity";
    private ToddlerPresenter toddlerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        toddlerPresenter = new ToddlerPresenter();
        toddlerPresenter.addView(this);

        toddlerPresenter.obtienePreguntas();
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public void gotoMain(Preguntas preguntas) {
        System.out.println("MENSAJE: "+preguntas.getPregunta());
    }

    @Override
    public void showMessageError(String message) {

    }
}
