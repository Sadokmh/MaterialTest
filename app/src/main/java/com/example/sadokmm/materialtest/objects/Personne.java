package com.example.sadokmm.materialtest.objects;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

public class Personne implements Parcelable{

    private String nom;
    private String des;
    private Bitmap av;
    private float rating;


    public Personne(String nom, String des, Bitmap av,float rating) {
        this.nom = nom;
        this.des = des;
        this.av = av;
        this.rating=rating;
    }


    protected Personne(Parcel in) {
        nom = in.readString();
        des = in.readString();
        av = in.readParcelable(Bitmap.class.getClassLoader());
        rating = in.readFloat();
    }

    public static final Creator<Personne> CREATOR = new Creator<Personne>() {
        @Override
        public Personne createFromParcel(Parcel in) {
            return new Personne(in);
        }

        @Override
        public Personne[] newArray(int size) {
            return new Personne[size];
        }
    };

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Bitmap getAv() {
        return av;
    }

    public void setAv(Bitmap av) {
        this.av = av;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }




    //Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(nom);
        parcel.writeString(des);
        parcel.writeString(encodeTobase64(av));
        parcel.writeFloat(rating);


    }


    public static String encodeTobase64(Bitmap image) {
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.PNG, 90, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }
}
