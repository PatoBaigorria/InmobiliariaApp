package com.patob.inmobiliariaapp.ui.inquilino;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.patob.inmobiliariaapp.model.Contrato;
import com.patob.inmobiliariaapp.model.Inquilino;

public class InquilinoFragmentViewModel extends AndroidViewModel {
    private MutableLiveData<Inquilino> mInquilino;
    public InquilinoFragmentViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<Inquilino> getMInquilino() {
        if(mInquilino == null){
            mInquilino = new MutableLiveData<>();
        }
        return mInquilino;
    }
    public void cargarInquilino(Bundle arguments) {
        if (arguments != null) {
            Inquilino inquilino = (Inquilino) arguments.getSerializable("inquilino");
            if (inquilino != null) {
                mInquilino.setValue(inquilino);
            }
        }
    }
}
