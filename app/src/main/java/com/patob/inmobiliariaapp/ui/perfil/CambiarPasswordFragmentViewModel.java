package com.patob.inmobiliariaapp.ui.perfil;

import android.app.Application;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.navigation.Navigation;

import com.patob.inmobiliariaapp.R;
import com.patob.inmobiliariaapp.request.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.io.IOException;

public class CambiarPasswordFragmentViewModel extends AndroidViewModel {
    public CambiarPasswordFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public void cambiarPassword(String cVieja, String cNueva, String cRepetir, View view) {
        if (cVieja.isEmpty() || cNueva.isEmpty() || cRepetir.isEmpty()) {
            Toast.makeText(getApplication(), "Debe completar todos los campos antes de cambiar la contraseña", Toast.LENGTH_LONG).show();
        } else {
            String token = ApiClient.leerToken(getApplication());
            ApiClient.MisEndPoints api = ApiClient.getEndPoints();
            Call<Void> call = api.cambiarPassword(token, cVieja, cNueva, cRepetir);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplication(), "Contraseña cambiada con exito", Toast.LENGTH_LONG).show();
                        Navigation.findNavController(view).navigate(R.id.nav_perfil);
                    } else {
                        try {
                            Toast.makeText(getApplication(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable throwable) {
                    Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}