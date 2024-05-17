package com.patob.inmobiliariaapp.ui.Contrato;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.patob.inmobiliariaapp.model.Inmueble;

public class ContratoFragmentViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> mInmueble;
    public ContratoFragmentViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<Inmueble> getMFarmacia() {
        if(mInmueble == null){
            mInmueble = new MutableLiveData<>();
        }
        return mInmueble;
    }
    public void cargarInmueble(Bundle arguments) {
        if (arguments != null) {
            Inmueble inmueble = (Inmueble) arguments.getSerializable("inmueble");
            if (inmueble != null) {
                mInmueble.setValue(inmueble);
            }
        }
    }
}
