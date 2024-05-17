package com.patob.inmobiliariaapp;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.patob.inmobiliariaapp.request.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityViewModel extends AndroidViewModel {
    private SharedPreferences sharedPreferences;

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        sharedPreferences = application.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE);
    }

    public void logueo(String usuario, String clave) {
        ApiClient.MisEndPoints api = ApiClient.getEndPoints();

        Call<String> call = api.login(usuario, clave);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String token = response.body();
                    guardarToken("Bearer " + token);
                    Log.d("salida", "Inicio de sesión exitoso");
                    iniciarMenuActivity();
                } else {
                    Toast.makeText(getApplication(), "Email o contraseña incorrecta", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Toast.makeText(getApplication(), "Falla en el inicio de sesión", Toast.LENGTH_LONG).show();
                Log.d("salida", throwable.getMessage());
            }
        });
    }

    public void enviarEmail(String email){
        ApiClient.MisEndPoints api = ApiClient.getEndPoints();
        if(!email.isEmpty()){
            Call<String> call = api.olvidePassword(email);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplication(), "Email enviado a su correo para recuperar la contraseña", Toast.LENGTH_LONG).show();
                    } else {
                        Log.d("salida", response.message());
                        Toast.makeText(getApplication(), "Email incorrecto o no está registrado", Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable throwable) {
                    Toast.makeText(getApplication(), "Falla en la recuperación del email", Toast.LENGTH_LONG).show();
                    Log.d("salida", throwable.getMessage());
                }
            });
        } else {
            Toast.makeText(getApplication(), "Por favor ingrese un email", Toast.LENGTH_LONG).show();
        }
    }

    private void guardarToken(String token) {
       ApiClient.guardarToken(token, getApplication());
    }

    private void iniciarMenuActivity() {
        Intent intent = new Intent(getApplication(), MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Limpiar la pila de actividades
        getApplication().startActivity(intent);
    }
}
