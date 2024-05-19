/*package com.patob.inmobiliariaapp.ui.inquilino;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.patob.inmobiliariaapp.R;
import com.patob.inmobiliariaapp.model.Inquilino;
import com.patob.inmobiliariaapp.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoAdapter extends RecyclerView.Adapter<InquilinoAdapter.ViewHolderPepe> {
    private List<Inquilino> listaDeInquilinos;
    private LayoutInflater li;
    public InquilinoAdapter(List<Inquilino> listaDeInquilinos, LayoutInflater li) {
        this.listaDeInquilinos = listaDeInquilinos;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolderPepe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.item_inquilino, parent, false);
        return new ViewHolderPepe(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPepe holder, int position) {
        Inquilino inquilino = listaDeInquilinos.get(position);
        holder.direccion.setText(contrato.inquilino.inmueble.getDireccion());
        //holder.foto.setImageResource(inmueble.getImagenInmueble());
        holder.btnVerMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                String token = ApiClient.leerToken(li.getContext());
                ApiClient.MisEndPoints api = ApiClient.getEndPoints();
                Call<Inquilino> call = api.obtenerInquilino(token, inquilino.getId());
                call.enqueue(new Callback<Inquilino>() {
                    @Override
                    public void onResponse(Call<Inquilino> call, Response<Inquilino> response) {
                        if(response.isSuccessful()){
                            bundle.putSerializable("inquilino", response.body());
                            Navigation.findNavController(v).navigate(R.id.nav_inquilino, bundle);
                        } else {
                            Log.d("salida", response.message());
                        }
                    }
                    @Override
                    public void onFailure(Call<Inquilino> call, Throwable throwable) {
                        Log.d("salida", "Falla: " + throwable.getMessage());
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaDeInquilinos.size();
    }

    public class ViewHolderPepe extends RecyclerView.ViewHolder {
        TextView direccion;
        //ImageView foto;
        Button btnVerMas;

        public ViewHolderPepe(@NonNull View itemView) {
            super(itemView);
            //foto = itemView.findViewById(R.id.ivImagenInm);
            direccion = itemView.findViewById(R.id.tvDireccionCont);
            btnVerMas = itemView.findViewById(R.id.btnVerMas);
        }
    }
}*/
