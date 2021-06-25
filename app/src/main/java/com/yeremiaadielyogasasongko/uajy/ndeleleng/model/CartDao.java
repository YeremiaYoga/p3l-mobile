package com.yeremiaadielyogasasongko.uajy.ndeleleng.model;

public class CartDao {
    private MenuDao menu;
    private int jumlah;

    public CartDao(MenuDao menu, int jumlah) {
        this.menu = menu;
        this.jumlah = jumlah;
    }

    public MenuDao getMenu() {
        return menu;
    }

    public void setMenu(MenuDao menu) {
        this.menu = menu;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
