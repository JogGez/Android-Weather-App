
package com.example.androidweatherapp.api.models.forecastweatherdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sys implements Serializable
{

    @SerializedName("pod")
    @Expose
    private String pod;
    private final static long serialVersionUID = 3350760092301047754L;

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

}
