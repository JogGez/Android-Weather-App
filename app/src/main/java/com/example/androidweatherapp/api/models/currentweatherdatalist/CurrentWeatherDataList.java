
package com.example.androidweatherapp.api.models.currentweatherdatalist;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentWeatherDataList implements Serializable
{

    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<com.example.androidweatherapp.api.models.currentweatherdatalist.List> list = null;
    private final static long serialVersionUID = 242271711515404095L;

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public java.util.List<com.example.androidweatherapp.api.models.currentweatherdatalist.List> getList() {
        return list;
    }

    public void setList(java.util.List<com.example.androidweatherapp.api.models.currentweatherdatalist.List> list) {
        this.list = list;
    }

}
