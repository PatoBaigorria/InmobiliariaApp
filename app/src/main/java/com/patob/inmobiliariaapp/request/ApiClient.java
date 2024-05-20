package com.patob.inmobiliariaapp.request;
import android.content.Context;
import android.content.SharedPreferences;

import com.patob.inmobiliariaapp.model.Contrato;
import com.patob.inmobiliariaapp.model.Inmueble;
import com.patob.inmobiliariaapp.model.Propietario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.patob.inmobiliariaapp.model.Tipo;
import com.patob.inmobiliariaapp.model.Uso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class ApiClient {
    private static final String URL = "http://192.168.0.26:5000/";
    private static MisEndPoints mep;

    public static MisEndPoints getEndPoints(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mep = retrofit.create(MisEndPoints.class);
        return mep;
    }

    public interface MisEndPoints {
        @FormUrlEncoded
        @POST("Propietarios/login")
        Call<String> login(@Field("Usuario") String u, @Field("Clave") String c);

        @GET("Propietarios")
        Call<Propietario> miPerfil(@Header("Authorization") String token);

        @PUT("Propietarios")
        Call<Propietario> modificarUsuario(@Header("Authorization") String token, @Body Propietario propietario);

        @POST("Propietarios/olvidecontraseña/{email}")
        Call<String> enviarEmail(@Path("email") String email);

        @GET("Inmuebles")
        Call<List<Inmueble>> obtenerInmuebles(@Header("Authorization") String token);

        @GET("Inmuebles/{id}")
        Call<Inmueble> obtenerInmueble(@Header("Authorization") String token, @Path("id") int id);

        @POST("Inmuebles")
        Call<Inmueble> agregarInmueble(@Header("Authorization") String token,  @Body Inmueble inmueble);

        @PUT("Inmuebles/cambiologico/{id}")
        Call<Void> inmuebleDisponible(@Header("Authorization") String token, @Path("id") int id);

        @GET("Contratos/{id}")
        Call<Contrato> obtenerContrato(@Header("Authoization") String token, @Path("id") int id);

        @GET("Tipos")
        Call<List<Tipo>> obtenerTipos(@Header("Authorization") String token);

        @GET("Usos")
        Call<List<Uso>> obtenerUsos(@Header("Authorization") String token);

        @GET("Tipos/{id}")
        Call<Tipo> obtenerTipo(@Header("Authorization") String token, @Path("id") int id);

        @GET("Usos/{id}")
        Call<Uso> obtenerUso(@Header("Authorization") String token, @Path("id") int id);

    }

    public static void guardarToken(String token, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public static String leerToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE);
        return sharedPreferences.getString("token",null);
    }

    public static void eliminarToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", "");
        editor.apply();
    }
}
