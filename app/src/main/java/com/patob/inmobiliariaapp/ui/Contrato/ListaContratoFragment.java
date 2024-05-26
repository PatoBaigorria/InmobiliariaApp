package com.patob.inmobiliariaapp.ui.contrato;

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

import com.patob.inmobiliariaapp.R;
import com.patob.inmobiliariaapp.databinding.FragmentListaContratosBinding;
import com.patob.inmobiliariaapp.model.Contrato;
import com.patob.inmobiliariaapp.model.Inmueble;
import com.patob.inmobiliariaapp.ui.inmueble.InmuebleAdapter;
import com.patob.inmobiliariaapp.ui.inmueble.SpaceItemDecoration;

import java.util.List;

public class ListaContratoFragment extends Fragment {

    private FragmentListaContratosBinding binding;
    private ListaContratoFragmentViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListaContratosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ListaContratoFragmentViewModel.class);
        vm.getMInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmueble) {
                ContratoAdapter contratoAdapter = new ContratoAdapter(inmueble, getLayoutInflater());
                GridLayoutManager glm = new GridLayoutManager(container.getContext(), 1, GridLayoutManager.VERTICAL, false);
                RecyclerView rv = binding.listaDeContratos;
                int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
                rv.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
                rv.setLayoutManager(glm);
                rv.setAdapter(contratoAdapter);
            }
        });
        vm.cargarContratos();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
