package com.yeremiaadielyogasasongko.uajy.ndeleleng.api;

import com.yeremiaadielyogasasongko.uajy.ndeleleng.api.response.DetailPesananResponse;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.api.response.MenuResponse;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.api.response.PesananResponse;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.model.DetailPesananDao;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("menu/tampil")
    Call<MenuResponse> getAllMenu();

    @GET("detailpesanan/tampil/{id_pesanan}")
    Call<DetailPesananResponse> getDetailPesanan(@Path("id_pesanan") String id_pesanan);

    @POST("pesanan/tambah")
    @FormUrlEncoded
    Call<PesananResponse> createPesanan(@Field("id_reservasi") String idreservasi);

    @POST("pesanan/finish/{id}")
    Call<DetailPesananResponse> finishPesanan(@Path("id") String idPesanan);

    @POST("detailpesanan/create")
    @Multipart
    Call<DetailPesananResponse> createDetailPesanan(@Part("listMenu[]") List<DetailPesananDao> listMenu, @Part("id_pesanan") RequestBody idPesanan);
}
