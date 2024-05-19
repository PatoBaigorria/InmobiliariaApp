package com.patob.inmobiliariaapp.ui.inquilino;/*package com.patob.inmobiliariaapp.ui.Contrato;

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

import com.patob.inmobiliariaapp.databinding.FragmentListaBinding;
import com.patob.inmobiliariaapp.model.Inmueble;
import com.patob.inmobiliariaapp.ui.inmueble.InmuebleAdapter;
import com.patob.inmobiliariaapp.ui.inmueble.ListaFragmentViewModel;

import java.util.List;

public class ListaContratoFragment extends Fragment {

    private FragmentListaBinding binding;
    private ListaFragmentViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ListaFragmentViewModel.class);
        vm.getMInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmueble) {
                InmuebleAdapter inmuebleAdapter = new InmuebleAdapter(inmueble, getLayoutInflater());
                GridLayoutManager glm = new GridLayoutManager(container.getContext(), 1, GridLayoutManager.VERTICAL, false);
                RecyclerView rv = binding.listaDeInmuebles;
                rv.setLayoutManager(glm);
                rv.setAdapter(inmuebleAdapter);
            }
        });
        vm.cargarInmuebles();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}*/
