package com.wprotheus.pdm2a02atv01.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {

    private final static long serialVersionUID = 6341275047972193034L;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User { ");
        sb.append("userId: ").append(userId).append('\n')
                .append("id: ").append(id).append('\n')
                .append("title: ").append(title).append('\n')
                .append("body: ").append(body).append(" }\n");
        return sb.toString();
    }
}
