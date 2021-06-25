package com.yeremiaadielyogasasongko.uajy.ndeleleng.model;

import com.google.gson.annotations.SerializedName;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.api.ApiClient;

public class MenuDao {
    @SerializedName("id")
    private String id;

    @SerializedName("nama_menu")
    private String nama_menu;

    @SerializedName("deskripsi_menu")
    private String deskripsi_menu;

    @SerializedName("kategori")
    private String kategori;

    @SerializedName("harga")
    private String harga;

    @SerializedName("hapus")
    private String hapus;

    @SerializedName("gambar")
    private String gambar;

    public String getId() {
        return id;
    }

    public String getNama_menu() {
        return nama_menu;
    }

    public String getDeskripsi_menu() {
        return deskripsi_menu;
    }

    public String getKategori() {
        return kategori;
    }

    public String getHarga() {
        return harga;
    }

    public String getHapus() {
        return hapus;
    }

    public String getGambar() {
        return ApiClient.Base_URL + "menu/gambar/" + gambar;
    }
}
