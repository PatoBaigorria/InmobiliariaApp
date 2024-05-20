package com.patob.inmobiliariaapp.ui.inquilino;

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

import com.patob.inmobiliariaapp.databinding.FragmentListaInquilinosBinding;
import com.patob.inmobiliariaapp.model.Inmueble;
import com.patob.inmobiliariaapp.model.Inquilino;
import com.patob.inmobiliariaapp.ui.inquilino.InquilinoAdapter;
import com.patob.inmobiliariaapp.ui.inquilino.ListaInquilinoFragmentViewModel;

import java.util.List;

public class ListaInquilinoFragment extends Fragment {

    private FragmentListaInquilinosBinding binding;
    private ListaInquilinoFragmentViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListaInquilinosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ListaInquilinoFragmentViewModel.class);
        vm.getMInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmueble) {
                InquilinoAdapter inquilinoAdapter = new InquilinoAdapter(inmueble, getLayoutInflater());
                GridLayoutManager glm = new GridLayoutManager(container.getContext(), 1, GridLayoutManager.VERTICAL, false);
                RecyclerView rv = binding.listaDeInquilinos;
                rv.setLayoutManager(glm);
                rv.setAdapter(inquilinoAdapter);
            }
        });
        vm.cargarInquilinos();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
