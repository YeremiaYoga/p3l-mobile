package com.yeremiaadielyogasasongko.uajy.ndeleleng.view;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yeremiaadielyogasasongko.uajy.ndeleleng.api.ApiClient;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.api.ApiInterface;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.api.response.DetailPesananResponse;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.api.response.MenuResponse;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.api.response.PesananResponse;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.model.CartDao;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.model.DetailPesananDao;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.model.MenuDao;
import com.yeremiaadielyogasasongko.uajy.ndeleleng.model.PesananDao;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityViewModel extends ViewModel {
    private MutableLiveData<List<MenuDao>> listMenuMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<CartDao>> listCartMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<DetailPesananDao>> listDetailPesananMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<PesananDao> pesananDaoMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> messageMutableLiveData = new MutableLiveData<>();


    {
        listMenuMutableLiveData.postValue(new ArrayList<>());
        listCartMutableLiveData.postValue(new ArrayList<>());
        listDetailPesananMutableLiveData.postValue(new ArrayList<>());
        pesananDaoMutableLiveData.postValue(null);
        messageMutableLiveData.postValue(null);
    }

    public void loadMenu() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MenuResponse> call = apiService.getAllMenu();

        call.enqueue(new Callback<MenuResponse>() {
            @Override
            public void onResponse(Call<MenuResponse> call, Response<MenuResponse> response) {
                if (response.body() != null) {
                    listMenuMutableLiveData.setValue(response.body().getMenuDaoList());
                    messageMutableLiveData.setValue(response.body().getMessage());
                } else {
                    messageMutableLiveData.setValue("Ada Yang Salah");
                }
            }

            @Override
            public void onFailure(Call<MenuResponse> call, Throwable t) {
                Log.d("string", "onResponse: " + t.getMessage());
                messageMutableLiveData.setValue(t.getMessage());
            }
        });
    }

    public void loadDetailPesanan() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<DetailPesananResponse> call = apiService.getDetailPesanan(pesananDaoMutableLiveData.getValue().getId());

        call.enqueue(new Callback<DetailPesananResponse>() {
            @Override
            public void onResponse(Call<DetailPesananResponse> call, Response<DetailPesananResponse> response) {
                if (response.body() != null) {
                    listDetailPesananMutableLiveData.setValue(response.body().getListDetailPesanan());
                    messageMutableLiveData.setValue(response.body().getMessage());
                } else {
                    messageMutableLiveData.setValue("Ada Yang Salah");
                }
            }

            @Override
            public void onFailure(Call<DetailPesananResponse> call, Throwable t) {
                Log.d("string", "onResponse: " + t.getMessage());
                messageMutableLiveData.setValue(t.getMessage());
            }
        });
    }

    public void finish() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<DetailPesananResponse> call = apiService.finishPesanan(pesananDaoMutableLiveData.getValue().getId());
        call.enqueue(new Callback<DetailPesananResponse>() {
            @Override
            public void onResponse(Call<DetailPesananResponse> call, Response<DetailPesananResponse> response) {
                if (response.body() != null) {
                    listCartMutableLiveData.postValue(new ArrayList<>());
                    listDetailPesananMutableLiveData.postValue(new ArrayList<>());
                    pesananDaoMutableLiveData.postValue(null);
                    messageMutableLiveData.postValue(null);

                    messageMutableLiveData.setValue(response.body().getMessage());
                } else {
                    messageMutableLiveData.setValue("Ada Yang Salah");
                }
            }

            @Override
            public void onFailure(Call<DetailPesananResponse> call, Throwable t) {
                Log.d("string", "onResponse: " + t.getMessage());
                messageMutableLiveData.setValue(t.getMessage());
            }
        });
    }

    public void createPesanan(String idReservasi) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<PesananResponse> add = apiService.createPesanan(idReservasi);

        add.enqueue(new Callback<PesananResponse>() {
            @Override
            public void onResponse(Call<PesananResponse> call, Response<PesananResponse> response) {
                if (response.body() != null) {
                    pesananDaoMutableLiveData.setValue(response.body().getPesananDao());
                }
                messageMutableLiveData.setValue(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<PesananResponse> call, Throwable t) {
                Log.d("string", "onResponse: " + t.getMessage());
                messageMutableLiveData.setValue(t.getMessage());
            }
        });
    }

    public void createDetailPesanan() {

        List<DetailPesananDao> listDetailPesananDao = new ArrayList<>();
        List<CartDao> temp = listCartMutableLiveData.getValue();
        if (temp != null) {
            for (CartDao cart : temp) {
                listDetailPesananDao.add(new DetailPesananDao(Integer.parseInt(cart.getMenu().getId()), cart.getJumlah()));
            }
        }

        RequestBody id_pesanan = RequestBody.create(MediaType.parse("text/plain"), pesananDaoMutableLiveData.getValue().getId());
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<DetailPesananResponse> add = apiService.createDetailPesanan(listDetailPesananDao, id_pesanan);

        add.enqueue(new Callback<DetailPesananResponse>() {
            @Override
            public void onResponse(Call<DetailPesananResponse> call, Response<DetailPesananResponse> response) {
                if (response.body() != null) {
                    messageMutableLiveData.setValue(response.body().getMessage());
                    listCartMutableLiveData.postValue(new ArrayList<>());
                } else {
                    Log.d("mendoan", response.message());
                    messageMutableLiveData.setValue("Ada Yang Salah");
                }
            }

            @Override
            public void onFailure(Call<DetailPesananResponse> call, Throwable t) {
                Log.d("string", "onResponse: " + t.getMessage());
                messageMutableLiveData.setValue(t.getMessage());
            }
        });

    }

    public void tambahCart(MenuDao menu) {
        CartDao cartDao = cariItem(menu.getId());

        if (cartDao != null) {
            cartDao.setJumlah(cartDao.getJumlah() + 1);
        } else {
            listCartMutableLiveData.getValue().add(new CartDao(menu, 1));
        }

        listCartMutableLiveData.setValue(listCartMutableLiveData.getValue());
    }

    public void incrementCart(String id) {
        CartDao cartDao = cariItem(id);

        if (cartDao != null) {
            cartDao.setJumlah(cartDao.getJumlah() + 1);
        }
    }

    public void decrementCart(String id) {
        CartDao cartDao = cariItem(id);

        if (cartDao != null) {
            if (cartDao.getJumlah() < 1) {
                List<CartDao> temp = listCartMutableLiveData.getValue();

                if (temp != null) {
                    temp.removeIf(data -> data.getMenu().getId().equals(id));
                }

                listCartMutableLiveData.setValue(temp);
            } else {
                cartDao.setJumlah(cartDao.getJumlah() - 1);
            }
        }
    }

    private CartDao cariItem(String id) {
        List<CartDao> keranjang = listCartMutableLiveData.getValue();

        if (keranjang != null) {
            for (CartDao cart : keranjang) {
                if (cart.getMenu().getId().equals(id)) {
                    return cart;
                }
            }
        }
        return null;
    }

    public MutableLiveData<List<MenuDao>> getListMenuMutableLiveData() {
        return listMenuMutableLiveData;
    }

    public void setListMenuMutableLiveData(MutableLiveData<List<MenuDao>> listMenuMutableLiveData) {
        this.listMenuMutableLiveData = listMenuMutableLiveData;
    }

    public MutableLiveData<PesananDao> getPesananDaoMutableLiveData() {
        return pesananDaoMutableLiveData;
    }

    public void setPesananDaoMutableLiveData(MutableLiveData<PesananDao> pesananDaoMutableLiveData) {
        this.pesananDaoMutableLiveData = pesananDaoMutableLiveData;
    }

    public MutableLiveData<String> getMessageMutableLiveData() {
        return messageMutableLiveData;
    }

    public void setMessageMutableLiveData(MutableLiveData<String> messageMutableLiveData) {
        this.messageMutableLiveData = messageMutableLiveData;
    }

    public MutableLiveData<List<CartDao>> getListCartMutableLiveData() {
        return listCartMutableLiveData;
    }

    public void setListCartMutableLiveData(MutableLiveData<List<CartDao>> listCartMutableLiveData) {
        this.listCartMutableLiveData = listCartMutableLiveData;
    }

    public MutableLiveData<List<DetailPesananDao>> getListDetailPesananMutableLiveData() {
        return listDetailPesananMutableLiveData;
    }

    public void setListDetailPesananMutableLiveData(MutableLiveData<List<DetailPesananDao>> listDetailPesananMutableLiveData) {
        this.listDetailPesananMutableLiveData = listDetailPesananMutableLiveData;
    }
}
