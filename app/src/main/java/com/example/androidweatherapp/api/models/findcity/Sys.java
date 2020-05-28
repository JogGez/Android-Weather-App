
package com.example.androidweatherapp.api.models.findcity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sys implements Serializable
{

    @SerializedName("country")
    @Expose
    private String country;
    private final static long serialVersionUID = -5655847908373921742L;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
