package com.miramicodigo.toddler.data.mapper;

import com.miramicodigo.toddler.data.entity.EvaluarEntity;
import com.miramicodigo.toddler.data.entity.response.*;
import com.miramicodigo.toddler.model.entity.Evaluacion;

/**
 * Created by gusn8 on 31-01-17.
 */

public class EvaluarDataMapper {

    private Evaluacion transform(EvaluarEntity evaluarEntity) {
        Evaluacion evaluacion = new Evaluacion();
        if (evaluarEntity == null)
            return evaluacion;
        evaluacion.setCi(evaluarEntity.getCi());
        evaluacion.setNombre(evaluarEntity.getNombre());
        evaluacion.setEdad(evaluarEntity.getEdad());
        evaluacion.setNombreTutor(evaluarEntity.getNombreTutor());

        return evaluacion;
    }

    public Evaluacion transformResponse(EvaluarResponse evaluarResponse) {
        Evaluacion evaluacion = new Evaluacion();
        if (evaluarResponse == null)
            return evaluacion;

        evaluacion = transform(evaluarResponse.getData());
        return evaluacion;
    }
}
