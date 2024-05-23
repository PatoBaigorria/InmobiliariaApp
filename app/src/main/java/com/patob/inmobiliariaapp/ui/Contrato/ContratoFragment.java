package com.patob.inmobiliariaapp.ui.contrato;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.patob.inmobiliariaapp.databinding.FragmentContratoBinding;
import com.patob.inmobiliariaapp.model.Contrato;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ContratoFragment extends Fragment {
    private FragmentContratoBinding binding;
    private ContratoFragmentViewModel vm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentContratoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm = new ViewModelProvider(this).get(ContratoFragmentViewModel.class);
        vm.getMContrato().observe(getViewLifecycleOwner(), new Observer<Contrato>() {

            @Override
            public void onChanged(Contrato contrato) {
                // Parsear la fecha recibida a LocalDate
                LocalDate inicioFecha = LocalDate.parse(contrato.getFechaInicio().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                // Formatear la fecha a "dd 'de' MMMM 'del' yyyy"
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'del' yyyy", new Locale("es", "ES"));
                String fechaInicio = inicioFecha.format(formatter);


                // Parsear la fecha recibida a LocalDate
                LocalDate finFecha = LocalDate.parse(contrato.getFechaFin().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                // Formatear la fecha a "dd 'de' MMMM 'del' yyyy"
                String fechaFin = finFecha.format(formatter);

                binding.tvCodigoContrato.setText(String.valueOf(contrato.getId()));
                binding.tvFechaIni.setText(fechaInicio);
                binding.tvFechaFin.setText(fechaFin);
                binding.tvMonto.setText(String.valueOf(contrato.getPrecio()));
                binding.tvInquilino.setText((contrato.getInquilino().toString()));
                binding.tvInmueb.setText((contrato.getInmueble().toString()));

            }
        });
        vm.cargarContrato(getArguments());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
