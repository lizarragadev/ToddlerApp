package com.miramicodigo.toddler.model.interactor;

import com.miramicodigo.toddler.data.entity.EvaluarEntity;
import com.miramicodigo.toddler.data.entity.PreguntaEntity;
import com.miramicodigo.toddler.data.entity.request.EvaluarRequest;
import com.miramicodigo.toddler.data.entity.response.EvaluarResponse;
import com.miramicodigo.toddler.data.entity.response.PreguntasResponse;
import com.miramicodigo.toddler.data.mapper.PreguntasDataMapper;
import com.miramicodigo.toddler.data.rest.ApiClient;
import com.miramicodigo.toddler.model.entity.Preguntas;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by gusn8 on 30-01-17.
 */

public class ToddlerInteractor {
    private final PreguntasDataMapper preguntasDataMapper;
    private static final int SUCCESS = 200;

    public ToddlerInteractor(PreguntasDataMapper preguntasDataMapper) {
        this.preguntasDataMapper = preguntasDataMapper;
    }

    public void getPreguntas(final ToddlerCallback toddlerCallback) {
        ApiClient.getMyApiClient().obtienePreguntas(new Callback<List<Preguntas>>() {
            @Override
            public void success(List<Preguntas> preguntasResponses, Response response) {
                if(response.getStatus() == SUCCESS) {
                    if(preguntasResponses != null) {
                        toddlerCallback.onObtienePreguntasSuccess(preguntasResponses);
                    } else {
                        toddlerCallback.onObtienePreguntasError("Ocurrio un error al obtener los datos.");
                    }
                } else {
                    System.out.println("OCURRIO UN PROBLEMIRIJILLA");
                }
            }
            @Override
            public void failure(RetrofitError error) {
                System.out.println("KKKKKKKKKKKKKKKKKKK: "+error.getMessage());
                String msg = "";
                if(error != null) {
                    msg = error.getMessage();
                    toddlerCallback.onObtienePreguntasError(msg);
                }
            }
        });
    }

    public void getEvaluacion(String nombre, int ci, int edad, String nombreTutor, int resMG, int resMF, int resLeng, final ToddlerCallback toddlerCallback) {
        EvaluarRequest evaluarRequest = new EvaluarRequest();
        evaluarRequest.setNombre(nombre);
        evaluarRequest.setCi(ci);
        evaluarRequest.setEdad(edad);
        evaluarRequest.setNombreTutor(nombreTutor);
        evaluarRequest.setResGrueso(resMG);
        evaluarRequest.setResFino(resMF);
        evaluarRequest.setResLeng(resLeng);

        ApiClient.getMyApiClient().obtieneEvaluacion(evaluarRequest, new Callback<EvaluarEntity>() {
            @Override
            public void success(EvaluarEntity evaluarEntity, Response response) {
                System.out.println("###################################### "+evaluarEntity);
                if(response.getStatus() == SUCCESS) {
                    System.out.println("LLEGA GGGGGGGGGGGGG: "+evaluarEntity);
                    System.out.println("RESPUESTA:::: "+response);
                    /*if(evaluarEntity != null) {
                        toddlerCallback.onObtieneEvaluacionSuccess(evaluarEntity);
                    } else {
                        toddlerCallback.onObtieneEvaluacionError("Ocurrio un error al obtener los datos.");
                    }*/
                } else {
                    System.out.println("OCURRIO UN PROBLEMIRIJILLA");
                }
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("KKKKKKKKKKKKKKKKKKK: "+error.getMessage());
                String msg = "";
                if(error != null) {
                    msg = error.getMessage();
                    toddlerCallback.onObtieneEvaluacionError(msg);
                }
            }
        });
    }
}
