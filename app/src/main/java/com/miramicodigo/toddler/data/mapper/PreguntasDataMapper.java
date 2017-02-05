package com.miramicodigo.toddler.data.mapper;

import com.miramicodigo.toddler.data.entity.EvaluarEntity;
import com.miramicodigo.toddler.data.entity.PreguntaEntity;
import com.miramicodigo.toddler.data.entity.response.EvaluarResponse;
import com.miramicodigo.toddler.data.entity.response.PreguntasResponse;
import com.miramicodigo.toddler.model.entity.Evaluacion;
import com.miramicodigo.toddler.model.entity.Preguntas;

import java.util.List;

/**
 * Created by gusn8 on 31-01-17.
 */

public class PreguntasDataMapper {

    private Preguntas transform(PreguntaEntity preguntaEntity) {
        Preguntas preguntas = new Preguntas();
        if (preguntaEntity == null)
            return preguntas;
        preguntas.setId(preguntaEntity.getId());
        preguntas.setPregunta(preguntaEntity.getPregunta());
        preguntas.setTipo(preguntaEntity.getTipo());

        return preguntas;
    }

    public Preguntas transformResponse(List<Preguntas> preguntasResponse) {
        Preguntas preguntas = new Preguntas();
        for (Preguntas pregunta: preguntasResponse) {
        //    Preguntas preguntas = new Preguntas();
        }

        if (preguntasResponse == null) {}


        //preguntas = transform(preguntasResponse.getData());
        return preguntas;
    }
}
