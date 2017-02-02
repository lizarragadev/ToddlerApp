package com.miramicodigo.toddler.view;

import com.miramicodigo.toddler.model.entity.Preguntas;

/**
 * Created by gusn8 on 30-01-17.
 */

public interface ToddlerView extends BaseView {
    void showLoading();
    void hideLoading();
    boolean validate();
    void gotoMain(Preguntas preguntas);
    void showMessageError(String message);

}