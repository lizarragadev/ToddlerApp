package com.miramicodigo.toddler.presenter;

import com.miramicodigo.toddler.data.mapper.EvaluarDataMapper;
import com.miramicodigo.toddler.data.mapper.PreguntasDataMapper;
import com.miramicodigo.toddler.model.entity.Preguntas;
import com.miramicodigo.toddler.model.interactor.ToddlerCallback;
import com.miramicodigo.toddler.model.interactor.ToddlerInteractor;
import com.miramicodigo.toddler.view.ToddlerView;

/**
 * Created by gusn8 on 30-01-17.
 */

public class ToddlerPresenter implements Presenter<ToddlerView>, ToddlerCallback {

    private static final String TAG ="ToddlerPresenter" ;
    private ToddlerView toddlerView;
    private ToddlerInteractor toddlerInteractor;

    public ToddlerPresenter() {

    }

    public void obtienePreguntas() {
        toddlerView.showLoading();
        toddlerInteractor.obtienePreguntas(this);
    }

    @Override
    public void onEvaluarSuccess(Object object) {

    }

    @Override
    public void onEvaluarError(Object object) {

    }

    @Override
    public void onObtienePreguntasSuccess(Object object) {
        toddlerView.hideLoading();
        Preguntas preguntas= (Preguntas) (object);
        toddlerView.gotoMain(preguntas);
    }

    @Override
    public void onObtienePreguntasError(Object object) {
        String message= object.toString();
        toddlerView.hideLoading();
        toddlerView.showMessageError(message);
    }

    @Override
    public void addView(ToddlerView view) {
        this.toddlerView = view;
        toddlerInteractor = new ToddlerInteractor(new PreguntasDataMapper());
    }

    @Override
    public void removeView() {
        this.toddlerView = null;
    }
}
