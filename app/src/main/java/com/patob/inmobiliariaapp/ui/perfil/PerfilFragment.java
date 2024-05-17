package com.patob.inmobiliariaapp.ui.perfil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.patob.inmobiliariaapp.R;
import com.patob.inmobiliariaapp.databinding.FragmentPerfilBinding;
import com.patob.inmobiliariaapp.model.Propietario;

public class PerfilFragment extends Fragment {
    private PerfilFragmentViewModel vm;
    private FragmentPerfilBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(PerfilFragmentViewModel.class);
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm.getMPropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                binding.etDNI.setText(propietario.getDni());
                binding.etNombre.setText(propietario.getNombre());
                binding.etApellido.setText(propietario.getApellido());
                binding.etTelefono.setText(propietario.getTelefono());
                binding.etEmailPerfil.setText(propietario.getEmail());
            }
        });
        vm.getMGuardar().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.btnEditar.setText(s);
            }
        });
        vm.getMHabilitar().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.etDNI.setEnabled(aBoolean);
                binding.etNombre.setEnabled(aBoolean);
                binding.etApellido.setEnabled(aBoolean);
                binding.etTelefono.setEnabled(aBoolean);
                binding.etEmailPerfil.setEnabled(aBoolean);
            }
        });

        vm.getMVisible().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer i) {
                binding.tvPassword.setVisibility(i);
                binding.etPasswordPerfil.setVisibility(i);
            }
        });
        binding.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Propietario p = new Propietario();
                p.setDni(binding.etDNI.getText().toString());
                p.setNombre(binding.etNombre.getText().toString());
                p.setApellido(binding.etApellido.getText().toString());
                p.setTelefono(binding.etTelefono.getText().toString());
                p.setEmail(binding.etEmailPerfil.getText().toString());
                p.setPassword(binding.etPasswordPerfil.getText().toString());
                binding.etPasswordPerfil.setText("");
                vm.editarDatos(binding.btnEditar.getText().toString(), p);
            }
        });
        vm.miPerfil();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}