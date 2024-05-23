package com.patob.inmobiliariaapp.ui.inmueble;

import android.app.Application;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.patob.inmobiliariaapp.model.Inmueble;
import com.patob.inmobiliariaapp.model.Tipo;
import com.patob.inmobiliariaapp.model.Uso;
import com.patob.inmobiliariaapp.request.ApiClient;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesFragmentViewModel extends AndroidViewModel {
    private MutableLiveData<List<Inmueble>> mInmuebles;
    public InmueblesFragmentViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<List<Inmueble>> getMInmuebles(){
        if(mInmuebles == null){
            mInmuebles = new MutableLiveData<>();
        }
        return mInmuebles;
    }

    public void cargarInmuebles(){
        ApiClient.MisEndPoints mep = ApiClient.getEndPoints();
        String token = ApiClient.leerToken(getApplication().getApplicationContext());
        Call<List<Inmueble>> call = mep.obtenerInmuebles(token);
        call.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful()){
                    mInmuebles.postValue(response.body());
                } else {
                    Log.d("salida", response.message());
                }
            }
            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable throwable) {
                Log.d("salida", "Falla: " + throwable.getMessage());
            }
        });
    }
}
