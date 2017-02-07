package com.miramicodigo.toddler.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by gusn8 on 30-01-17.
 */

public class EvaluarEntity implements Parcelable, Serializable {
    private String nombre;
    private int ci;
    private int edad;
    private String nombreTutor;
    private String resultado;

    protected EvaluarEntity(Parcel in) {
        nombre = in.readString();
        ci = in.readInt();
        edad = in.readInt();
        nombreTutor = in.readString();
        resultado = in.readString();
    }

    public static final Creator<EvaluarEntity> CREATOR = new Creator<EvaluarEntity>() {
        @Override
        public EvaluarEntity createFromParcel(Parcel in) {
            return new EvaluarEntity(in);
        }

        @Override
        public EvaluarEntity[] newArray(int size) {
            return new EvaluarEntity[size];
        }
    };

    public EvaluarEntity(String nombre, int ci, int edad, String nombreTutor, String resultado) {
        this.nombre = nombre;
        this.ci = ci;
        this.edad = edad;
        this.nombreTutor = nombreTutor;
        this.resultado = resultado;
    }

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

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeInt(this.ci);
        dest.writeInt(this.edad);
        dest.writeString(this.nombreTutor);
        dest.writeString(this.resultado);
    }
}

