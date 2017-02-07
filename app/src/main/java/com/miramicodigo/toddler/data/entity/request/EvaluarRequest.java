package com.miramicodigo.toddler.data.entity.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by gusn8 on 30-01-17.
 */

public class EvaluarRequest {

    private String nombre;
    private int ci;
    private int edad;
    private String nombreTutor;
    private int resGrueso;
    private int resFino;
    private int resLeng;


    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }

    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }

    public int getResGrueso() {
        return resGrueso;
    }

    public void setResGrueso(int resGrueso) {
        this.resGrueso = resGrueso;
    }

    public int getResFino() {
        return resFino;
    }

    public void setResFino(int resFino) {
        this.resFino = resFino;
    }

    public int getResLeng() {
        return resLeng;
    }

    public void setResLeng(int resLeng) {
        this.resLeng = resLeng;
    }
}
