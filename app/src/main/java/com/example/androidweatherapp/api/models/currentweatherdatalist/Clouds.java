
package com.example.androidweatherapp.api.models.currentweatherdatalist;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds implements Serializable
{

    @SerializedName("all")
    @Expose
    private Integer all;
    private final static long serialVersionUID = 5934193612479686090L;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

}
