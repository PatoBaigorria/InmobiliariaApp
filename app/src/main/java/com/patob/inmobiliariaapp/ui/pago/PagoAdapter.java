package com.patob.inmobiliariaapp.ui.pago;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.patob.inmobiliariaapp.R;
import com.patob.inmobiliariaapp.model.Pago;


import java.util.List;

public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.ViewHolderPepe> {
    private List<Pago> listaDePagos;
    private LayoutInflater li;
    public PagoAdapter(List<Pago> listaDePagos, LayoutInflater li) {
        this.listaDePagos = listaDePagos;
        this.li = li;
    }
    @NonNull
    @Override
    public ViewHolderPepe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.item_pago, parent, false);
        return new ViewHolderPepe(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPepe holder, int position) {
        Pago pago = listaDePagos.get(position);
        holder.tvCodigoPagoValue.setText(pago.getId());
        holder.tvNumeroPagoValue.setText(pago.getNumeroDePago());
        holder.tvCodigoContValue.setText(pago.getContratoId());
        holder.tvImporteValue.setText(String.valueOf(pago.getMonto()));
        holder.tvFechaPagoValue.setText(String.valueOf(pago.getFecha()));
    }

    @Override
    public int getItemCount() {
        return listaDePagos.size();
    }

    public class ViewHolderPepe extends RecyclerView.ViewHolder {
        TextView tvCodigoPagoValue, tvNumeroPagoValue, tvCodigoContValue, tvImporteValue, tvFechaPagoValue;

        public ViewHolderPepe(@NonNull View itemView) {
            super(itemView);
            tvCodigoPagoValue = itemView.findViewById(R.id.tvCodigoPagoValue);
            tvNumeroPagoValue = itemView.findViewById(R.id.tvNumeroPagoValue);
            tvCodigoContValue = itemView.findViewById(R.id.tvCodigoContValue);
            tvImporteValue = itemView.findViewById(R.id.tvImporteValue);
            tvFechaPagoValue = itemView.findViewById(R.id.tvFechaPagoValue);
        }
    }
}
