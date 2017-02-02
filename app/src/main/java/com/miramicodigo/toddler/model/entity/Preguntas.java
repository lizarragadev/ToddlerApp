package com.miramicodigo.toddler.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gusn8 on 02-02-17.
 */

public class Preguntas implements Parcelable {
    private int id;
    private String pregunta;
    private String descripcion;
    private int tipo;

    public Preguntas() {

    }

    protected Preguntas(Parcel in) {
        this.id = in.readInt();
        this.pregunta = in.readString();
        this.descripcion = in.readString();
        this.tipo = in.readInt();
    }

    public static final Creator<Preguntas> CREATOR = new Creator<Preguntas>() {
        @Override
        public Preguntas createFromParcel(Parcel in) {
            return new Preguntas(in);
        }

        @Override
        public Preguntas[] newArray(int size) {
            return new Preguntas[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.pregunta);
        dest.writeString(this.descripcion);
        dest.writeInt(this.tipo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
