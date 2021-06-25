package com.yeremiaadielyogasasongko.uajy.ndeleleng.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.model.DetailPesananDao;


import java.util.List;

public class DetailPesananResponse {
    @SerializedName("data")
    @Expose
    private List<DetailPesananDao> listDetailPesanan = null;

    @SerializedName("message")
    @Expose
    private String message;

    public List<DetailPesananDao> getListDetailPesanan() {
        return listDetailPesanan;
    }

    public void setListDetailPesanan(List<DetailPesananDao> listDetailPesanan) {
        this.listDetailPesanan = listDetailPesanan;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
