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
                binding.tvCodigoContrato.setText(String.valueOf(contrato.getId()));
                binding.tvFechaIni.setText(contrato.getFechaInicio().toString());
                binding.tvFechaFin.setText(contrato.getFechaFin().toString());
                binding.tvMonto.setText(String.valueOf(contrato.getPrecio()));
                binding.tvInmueb.setText(String.valueOf(contrato.getInmuebleId()));
                binding.tvInquilino.setText(String.valueOf(contrato.getInquilinoId()));
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
