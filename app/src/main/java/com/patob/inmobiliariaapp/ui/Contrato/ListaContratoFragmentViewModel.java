package com.patob.inmobiliariaapp.ui.contrato;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.patob.inmobiliariaapp.model.Contrato;
import com.patob.inmobiliariaapp.model.Inmueble;
import com.patob.inmobiliariaapp.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListaContratoFragmentViewModel extends AndroidViewModel {
    private MutableLiveData<List<Inmueble>> mInmuebles;
    public ListaContratoFragmentViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<List<Inmueble>> getMInmuebles(){
        if(mInmuebles == null){
            mInmuebles = new MutableLiveData<>();
        }
        return mInmuebles;
    }

    public void cargarContratos(){
        ApiClient.MisEndPoints mep = ApiClient.getEndPoints();
        String token = ApiClient.leerToken(getApplication().getApplicationContext());
        Call<List<Contrato>> call = mep.obtenerContratos(token);
        List<Inmueble> inmuebles = new ArrayList<>();
        call.enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                if(response.isSuccessful()){
                    for(Contrato c : response.body()){
                        inmuebles.add(c.getInmueble());
                    }
                    mInmuebles.postValue(inmuebles);
                } else {
                    Log.d("salida", response.message());
                }
            }
            @Override
            public void onFailure(Call<List<Contrato>> call, Throwable throwable) {
                Log.d("salida", "Falla: " + throwable.getMessage());
            }
        });
    }
}
