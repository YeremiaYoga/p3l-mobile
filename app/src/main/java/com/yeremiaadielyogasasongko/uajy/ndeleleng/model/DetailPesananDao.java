package com.yeremiaadielyogasasongko.uajy.ndeleleng.model;

import com.google.gson.annotations.SerializedName;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.api.ApiClient;

public class DetailPesananDao {
    @SerializedName("id_menu")
    private int idMenu;

    @SerializedName("jumlah")
    private int jumlah;

    @SerializedName("nama_menu")
    private String namaMenu;

    @SerializedName("harga")
    private int harga;

    @SerializedName("gambar")
    private String gambar;

    @SerializedName("status")
    private int status;


    public DetailPesananDao(int idMenu, int kuantiti) {
        this.idMenu = idMenu;
        this.jumlah = kuantiti;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public int getKuantiti() {
        return jumlah;
    }

    public void setKuantiti(int kuantiti) {
        this.jumlah = kuantiti;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getGambar() {
        return ApiClient.Base_URL + "menu/gambar/" + gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
