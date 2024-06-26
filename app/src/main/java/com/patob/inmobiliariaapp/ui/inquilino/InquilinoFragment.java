package com.patob.inmobiliariaapp.ui.inquilino;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.patob.inmobiliariaapp.databinding.FragmentContratoBinding;
import com.patob.inmobiliariaapp.databinding.FragmentInquilinoBinding;
import com.patob.inmobiliariaapp.model.Contrato;
import com.patob.inmobiliariaapp.model.Inquilino;

public class InquilinoFragment extends Fragment {
    private FragmentInquilinoBinding binding;
    private InquilinoFragmentViewModel vm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm = new ViewModelProvider(this).get(InquilinoFragmentViewModel.class);
        vm.getMInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {

            @Override
            public void onChanged(Inquilino inquilino) {
                binding.etCodigo.setText(String.valueOf(inquilino.getId()));
                binding.etCodigo.setFocusable(false);
                binding.etNombre.setText(inquilino.getNombre());
                binding.etNombre.setFocusable(false);
                binding.etApellido.setText(inquilino.getApellido());
                binding.etApellido.setFocusable(false);
                binding.etDNI.setText(String.valueOf(inquilino.getDni()));
                binding.etDNI.setFocusable(false);
                binding.etEmailInq.setText((inquilino.getEmail()));
                binding.etEmailInq.setFocusable(false);
                binding.etTelefono.setText((inquilino.getTelefono()));
                binding.etTelefono.setFocusable(false);

            }
        });
        vm.cargarInquilino(getArguments());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
