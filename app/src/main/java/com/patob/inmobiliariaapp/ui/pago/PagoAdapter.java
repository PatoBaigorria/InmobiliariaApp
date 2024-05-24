package com.patob.inmobiliariaapp.ui.pago;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.patob.inmobiliariaapp.R;
import com.patob.inmobiliariaapp.model.Pago;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        holder.tvCodigoPagoValue.setText(String.valueOf(pago.getId()));
        holder.tvNumeroPagoValue.setText(String.valueOf(pago.getNumeroDePago()));
        holder.tvCodigoContValue.setText(String.valueOf(pago.getContratoId()));
        holder.tvImporteValue.setText(Double.toString(pago.getMonto()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'del' yyyy");
        LocalDate fechaLD = LocalDate.parse(pago.getFecha(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String fechaS = fechaLD.format(formatter);
        holder.tvFechaPagoValue.setText(fechaS);
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
