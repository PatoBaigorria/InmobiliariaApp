package com.patob.inmobiliariaapp.ui.contrato;

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
        Button btnVerMas;

        public ViewHolderPepe(@NonNull View itemView) {
            super(itemView);
            direccion = itemView.findViewById(R.id.tvDireccionCont);
            btnVerMas = itemView.findViewById(R.id.btnVerMas);
        }
    }
}