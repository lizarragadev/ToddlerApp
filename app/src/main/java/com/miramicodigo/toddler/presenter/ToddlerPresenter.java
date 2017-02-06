package com.miramicodigo.toddler.presenter;

import com.miramicodigo.toddler.data.entity.EvaluarEntity;
import com.miramicodigo.toddler.data.mapper.EvaluarDataMapper;
import com.miramicodigo.toddler.data.mapper.PreguntasDataMapper;
import com.miramicodigo.toddler.model.entity.Preguntas;
import com.miramicodigo.toddler.model.interactor.ToddlerCallback;
import com.miramicodigo.toddler.model.interactor.ToddlerInteractor;
import com.miramicodigo.toddler.view.ToddlerView;

import java.util.List;

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
        toddlerInteractor.getPreguntas(this);
    }

    public void getEvaluacion(String nombre, int ci, int edad, String tutor, int resGrueso, int resFino, int resLeng) {
        toddlerView.showLoading();
        toddlerInteractor.getEvaluacion(nombre, ci, edad, tutor, resGrueso, resFino, resLeng, this);
    }

    @Override
    public void onObtieneEvaluacionSuccess(Object object) {
        toddlerView.hideLoading();
        EvaluarEntity evaluarEntity = (EvaluarEntity) (object);
        toddlerView.gotoMainEvaluacion(evaluarEntity);
    }

    @Override
    public void onObtieneEvaluacionError(Object object) {
        String message= object.toString();
        toddlerView.hideLoading();
        toddlerView.showMessageError(message);
    }

    @Override
    public void onObtienePreguntasSuccess(Object object) {
        toddlerView.hideLoading();
        List<Preguntas> preguntas= (List<Preguntas>) (object);
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
