package com.patob.inmobiliariaapp.ui.pago;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.patob.inmobiliariaapp.model.Pago;

public class PagoFragmentViewModel extends AndroidViewModel {
    private MutableLiveData<Pago> mPago;
    public PagoFragmentViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<Pago> getMPago() {
        if(mPago == null){
            mPago = new MutableLiveData<>();
        }
        return mPago;
    }
    public void cargarPago(Bundle arguments) {
        if (arguments != null) {
            Pago pago = (Pago) arguments.getSerializable("pago");
            if (pago != null) {
                mPago.setValue(pago);
            }
        }
    }
}
