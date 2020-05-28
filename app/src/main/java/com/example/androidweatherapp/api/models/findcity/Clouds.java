
package com.example.androidweatherapp.api.models.findcity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Clouds implements Serializable
{

    @SerializedName("all")
    @Expose
    private Integer all;
    private final static long serialVersionUID = -8549229831218769178L;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

}
