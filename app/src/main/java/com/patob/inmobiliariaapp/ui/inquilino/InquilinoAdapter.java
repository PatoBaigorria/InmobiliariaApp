package com.patob.inmobiliariaapp.ui.inquilino;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.patob.inmobiliariaapp.R;
import com.patob.inmobiliariaapp.model.Contrato;
import com.patob.inmobiliariaapp.model.Inquilino;
import com.patob.inmobiliariaapp.model.Inmueble;
import com.patob.inmobiliariaapp.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoAdapter extends RecyclerView.Adapter<InquilinoAdapter.ViewHolderPepe> {
    private List<Inmueble> listaDeInquilinos;
    private LayoutInflater li;
    public InquilinoAdapter(List<Inmueble> listaDeInquilinos, LayoutInflater li) {
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
        Inmueble inmueble = listaDeInquilinos.get(position);
        holder.direccion.setText(inmueble.getDireccion());
        String imageUrl = ApiClient.URL + inmueble.getImagenUrl();
        RequestOptions options = new RequestOptions().placeholder(R.drawable.icon_inmuebles).error(R.drawable.icon_logout);
        Glide.with(li.getContext()).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).apply(options).into(holder.foto);
        holder.btnVerMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                String token = ApiClient.leerToken(li.getContext());
                ApiClient.MisEndPoints api = ApiClient.getEndPoints();
                Call<Contrato> contratoCall = api.obtenerContrato(token, inmueble.getId());
                contratoCall.enqueue(new Callback<Contrato>() {
                    @Override
                    public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                        if (response.isSuccessful()) {
                            bundle.putSerializable("inquilino", response.body().getInquilino());
                            Navigation.findNavController(v).navigate(R.id.nav_inquilino, bundle);
                        }
                    }

                    @Override
                    public void onFailure(Call<Contrato> call, Throwable throwable) {

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

        ImageView foto;
        Button btnVerMas;

        public ViewHolderPepe(@NonNull View itemView) {
            super(itemView);
            direccion = itemView.findViewById(R.id.tvDireccionInq);
            foto = itemView.findViewById(R.id.ivImagenInmInq);
            btnVerMas = itemView.findViewById(R.id.btnVerMas);
        }
    }
}