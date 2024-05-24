package com.patob.inmobiliariaapp.ui.pago;

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

import com.patob.inmobiliariaapp.databinding.FragmentListaPagosBinding;
import com.patob.inmobiliariaapp.model.Pago;
import java.util.List;


public class PagoFragment extends Fragment {
    private FragmentListaPagosBinding binding;
    private PagoFragmentViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListaPagosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PagoFragmentViewModel.class);
        vm.getMPagos().observe(getViewLifecycleOwner(), new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> pagos) {
                PagoAdapter pagoAdapter = new PagoAdapter(pagos, getLayoutInflater());
                GridLayoutManager glm = new GridLayoutManager(container.getContext(), 1, GridLayoutManager.VERTICAL, false);
                RecyclerView rv = binding.listaDePagos;
                rv.setLayoutManager(glm);
                rv.setAdapter(pagoAdapter);
            }
        });
        vm.cargarPagos(getArguments());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
