package com.yeremiaadielyogasasongko.uajy.ndeleleng.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.model.MenuDao;

import java.util.List;

public class MenuResponse {
    @SerializedName("data")
    @Expose
    private List<MenuDao> menuDaoList =null;

    @SerializedName("message")
    @Expose
    private String message;

    public List<MenuDao> getMenuDaoList() {
        return menuDaoList;
    }

    public String getMessage() {
        return message;
    }
}
