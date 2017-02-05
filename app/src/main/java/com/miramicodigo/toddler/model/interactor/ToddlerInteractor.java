package com.miramicodigo.toddler.model.interactor;

import com.miramicodigo.toddler.data.entity.PreguntaEntity;
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

    public void obtienePreguntas(final ToddlerCallback toddlerCallback) {
        ApiClient.getMyApiClient().obtienePreguntas(new Callback<List<Preguntas>>() {
            @Override
            public void success(List<Preguntas> preguntasResponses, Response response) {
                if(response.getStatus() == SUCCESS) {
                    if(preguntasResponses != null) {
                        //List<Preguntas> preguntas = preguntasDataMapper.transformResponse(preguntasResponses);
                        toddlerCallback.onObtienePreguntasSuccess(preguntasResponses);

                        /*for(Preguntas pre : preguntasResponses) {
                            System.out.println(">>>>>>>>>>> ID: "+pre.getId());
                            System.out.println(">>>>> PREGUNTA: "+pre.getPregunta());
                            System.out.println(">> DESCRIPCION: "+pre.getDescripcion());
                            System.out.println(">>>>>>>>> TIPO: "+pre.getTipo());
                            System.out.println("<><><><><><><><><><><><><><>><>><><><><><><><><><><><>");
                        }*/

                        /*PreguntaEntity pe = new PreguntaEntity();
                        pe.setId(preguntasResponses.get(0).getId());
                        pe.setPregunta(preguntasResponses.get(0).getPregunta());
                        pe.setTipo(preguntasResponses.get(0).getTipo());
                        System.out.println("GGGGGGGGG PreguntasResponse: "+preguntasResponses);
                        System.out.println("HHHHHHHHH Response: "+response.getReason());
                        System.out.println("HHHHHHHHH Response: "+response.getUrl());
                        System.out.println("HHHHHHHHH Response: "+response.getBody());
                        System.out.println("HHHHHHHHH Response: "+response.getHeaders());
                        System.out.println("HHHHHHHHH Response: "+response.getStatus());
                        System.out.println(">>>>>>>>>>>>>>>>> "+pe.getId());
                        System.out.println(">>>>>>>>>>>>>>>>> "+pe.getPregunta());
                        System.out.println(">>>>>>>>>>>>>>>>> "+pe.getTipo());*/
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
}
