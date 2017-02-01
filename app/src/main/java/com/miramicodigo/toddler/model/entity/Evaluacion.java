package com.miramicodigo.toddler.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gusn8 on 30-01-17.
 */

public class Evaluacion implements Parcelable{

    private int id;
    private int ci;
    private String nombre;
    private String apellido;
    private int edad;
    private String nombreTutor;

    public Evaluacion() {

    }

    protected Evaluacion(Parcel in) {
        id = in.readInt();
        ci = in.readInt();
        nombre = in.readString();
        apellido = in.readString();
        edad = in.readInt();
        nombreTutor = in.readString();
    }

    public static final Creator<Evaluacion> CREATOR = new Creator<Evaluacion>() {
        @Override
        public Evaluacion createFromParcel(Parcel in) {
            return new Evaluacion(in);
        }

        @Override
        public Evaluacion[] newArray(int size) {
            return new Evaluacion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.ci);
        dest.writeString(this.nombre);
        dest.writeString(this.apellido);
        dest.writeInt(this.edad);
        dest.writeString(this.nombreTutor);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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
}
