package com.patob.inmobiliariaapp.ui.contrato;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.patob.inmobiliariaapp.model.Contrato;

public class ContratoFragmentViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato> mContrato;
    public ContratoFragmentViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<Contrato> getMContrato() {
        if(mContrato == null){
            mContrato = new MutableLiveData<>();
        }
        return mContrato;
    }
    public void cargarContrato(Bundle arguments) {
        if (arguments != null) {
            Contrato contrato = (Contrato) arguments.getSerializable("contrato");
            if (contrato != null) {
                mContrato.setValue(contrato);
            }
        }
    }
}
