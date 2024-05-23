/*package com.patob.inmobiliariaapp.ui.pago;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.patob.inmobiliariaapp.model.Pago;

import java.util.ArrayList;


public class PagoFragment extends Fragment {
    private FragmentPagoBinding binding;
    private PagoFragmentViewModel vm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPagoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm = new ViewModelProvider(this).get(PagoFragmentViewModel.class);

        RecyclerView rv = binding.listaDePagos;
        GridLayoutManager glm = new GridLayoutManager(requireContext(), 1, GridLayoutManager.VERTICAL, false);
        rv.setLayoutManager(glm);
        adapter = new PagoAdapter(new ArrayList<>(), requireContext(), getLayoutInflater());
        rv.setAdapter(adapter);

        vm.getMPago().observe(getViewLifecycleOwner(), new Observer<Pago>() {
            @Override
            public void onChanged(Pago pago) {
                binding.tvTotal.setText(String.valueOf(pago.getTotal()));
            }
        });
        if (savedInstanceState != null) {
            photoURI = Uri.parse(savedInstanceState.getString("photo_uri"));
        }
        vm.cargarPago(getArguments());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}*/
