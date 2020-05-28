
package com.example.androidweatherapp.api.models.findcity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FindCity implements Serializable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("list")
    @Expose
    private java.util.List<com.example.androidweatherapp.api.models.findcity.List> list = null;
    private final static long serialVersionUID = 7382576609791884618L;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public java.util.List<com.example.androidweatherapp.api.models.findcity.List> getList() {
        return list;
    }

    public void setList(java.util.List<com.example.androidweatherapp.api.models.findcity.List> list) {
        this.list = list;
    }

}
