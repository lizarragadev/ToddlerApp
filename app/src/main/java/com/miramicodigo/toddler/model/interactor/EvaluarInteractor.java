package com.miramicodigo.toddler.model.interactor;

import com.miramicodigo.toddler.data.entity.request.EvaluarRequest;
import com.miramicodigo.toddler.data.mapper.EvaluarDataMapper;

/**
 * Created by gusn8 on 30-01-17.
 */

public class EvaluarInteractor {
    private final EvaluarDataMapper evaluarDataMapper;

    public EvaluarInteractor(EvaluarDataMapper evaluarDataMapper) {
        this.evaluarDataMapper = evaluarDataMapper;
    }

    public void evaluar(int ci, String nombre, String apellido, int edad, String nombreTutor, final EvaluarCallback evaluarCallback) {
        EvaluarRequest evaluarRequest = new EvaluarRequest();
        evaluarRequest.setCi(ci);
    }
}
