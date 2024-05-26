package com.patob.inmobiliariaapp.ui.contrato;

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
import com.patob.inmobiliariaapp.model.Inmueble;
import com.patob.inmobiliariaapp.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoAdapter extends RecyclerView.Adapter<ContratoAdapter.ViewHolderPepe> {
    private List<Inmueble> listaDeContratos;
    private LayoutInflater li;
    public ContratoAdapter(List<Inmueble> listaDeContratos, LayoutInflater li) {
        this.listaDeContratos = listaDeContratos;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolderPepe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.item_contrato, parent, false);
        return new ViewHolderPepe(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPepe holder, int position) {
        Inmueble inmueble = listaDeContratos.get(position);
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
                Call<Contrato> call = api.obtenerContrato(token, inmueble.getId());
                call.enqueue(new Callback<Contrato>() {
                    @Override
                    public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                        if(response.isSuccessful()){
                            bundle.putSerializable("contrato", response.body());
                            Navigation.findNavController(v).navigate(R.id.nav_contrato, bundle);
                        }
                    }
                    @Override
                    public void onFailure(Call<Contrato> call, Throwable throwable) {
                        Log.d("salida", "Falla: " + throwable.getMessage());
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaDeContratos.size();
    }

    public class ViewHolderPepe extends RecyclerView.ViewHolder {
        TextView direccion;

        ImageView foto;
        Button btnVerMas;

        public ViewHolderPepe(@NonNull View itemView) {
            super(itemView);
            direccion = itemView.findViewById(R.id.tvDireccionInm);
            foto = itemView.findViewById(R.id.ivImagenInm);
            btnVerMas = itemView.findViewById(R.id.btnVerMas);
        }
    }
}