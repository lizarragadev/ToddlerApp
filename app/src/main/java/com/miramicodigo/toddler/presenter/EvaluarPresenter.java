package com.miramicodigo.toddler.presenter;

import com.miramicodigo.toddler.data.mapper.EvaluarDataMapper;
import com.miramicodigo.toddler.model.interactor.EvaluarCallback;
import com.miramicodigo.toddler.model.interactor.EvaluarInteractor;
import com.miramicodigo.toddler.view.EvaluarView;

/**
 * Created by gusn8 on 30-01-17.
 */

public class EvaluarPresenter implements Presenter<EvaluarView>, EvaluarCallback {

    private static final String TAG ="EvaluarPresenter" ;
    private EvaluarView evaluarView;
    private EvaluarInteractor evaluarInteractor;

    public EvaluarPresenter() {

    }

    @Override
    public void onEvaluarSuccess(Object object) {

    }

    @Override
    public void onEvaluarError(Object object) {

    }

    @Override
    public void addView(EvaluarView view) {
        this.evaluarView = view;
        evaluarInteractor = new EvaluarInteractor(new EvaluarDataMapper());
    }

    @Override
    public void removeView() {

    }
}
