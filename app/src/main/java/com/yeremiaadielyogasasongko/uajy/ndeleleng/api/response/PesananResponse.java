package com.yeremiaadielyogasasongko.uajy.ndeleleng.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.model.PesananDao;

public class PesananResponse {

    @SerializedName("data")
    @Expose
    private PesananDao pesananDao = null;

    @SerializedName("message")
    @Expose
    private String message;

    public PesananDao getPesananDao() {
        return pesananDao;
    }

    public String getMessage() {
        return message;
    }
}
