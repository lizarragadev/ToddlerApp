package com.miramicodigo.toddler.model.interactor;

import com.miramicodigo.toddler.data.entity.response.PreguntasResponse;
import com.miramicodigo.toddler.data.mapper.PreguntasDataMapper;
import com.miramicodigo.toddler.data.rest.ApiClient;
import com.miramicodigo.toddler.model.entity.Preguntas;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by gusn8 on 30-01-17.
 */

public class ToddlerInteractor {
    private final PreguntasDataMapper preguntasDataMapper;

    public ToddlerInteractor(PreguntasDataMapper preguntasDataMapper) {
        this.preguntasDataMapper = preguntasDataMapper;
    }

    public void obtienePreguntas(final ToddlerCallback toddlerCallback) {
        ApiClient.getMyApiClient().obtienePreguntas(new Callback<PreguntasResponse>() {
            @Override
            public void success(PreguntasResponse preguntasResponse, Response response) {
                if(preguntasResponse != null) {
                    Preguntas preguntas = preguntasDataMapper.transformResponse(preguntasResponse);
                    toddlerCallback.onObtienePreguntasSuccess(preguntas);
                } else {
                    toddlerCallback.onObtienePreguntasError("Ocurrio un error al obtener los datos.");
                }
            }

            @Override
            public void failure(RetrofitError error) {
                String msg = "";
                if(error != null) {
                    msg = error.getMessage();
                    toddlerCallback.onObtienePreguntasError(msg);
                }
            }
        });
    }
}
