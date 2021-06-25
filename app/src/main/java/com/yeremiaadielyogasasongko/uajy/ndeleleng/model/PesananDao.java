package com.yeremiaadielyogasasongko.uajy.ndeleleng.model;

import com.google.gson.annotations.SerializedName;

public class PesananDao {
    @SerializedName("id")
    private String id;

    @SerializedName("id_reservasi")
    private String id_reservasi;

    public String getId() {
        return id;
    }

    public String getId_reservasi() {
        return id_reservasi;
    }
}
