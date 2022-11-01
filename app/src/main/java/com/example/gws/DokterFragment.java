package com.example.gws;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import com.example.gws.databinding.FragmentDokterBinding;

import java.util.ArrayList;

public class DokterFragment extends Fragment implements InterfaceDokter {
    FragmentDokterBinding binding;
    private MainPresenter presenter;
    private AdapterDokter adapterDokter;

    public DokterFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentDokterBinding.inflate(inflater);

        this.adapterDokter = new AdapterDokter(presenter, this);
        this.binding.listDokter.setAdapter(adapterDokter);

        FragmentManager fm = this.getParentFragmentManager();

//        this.getParentFragmentManager().setFragmentResultListener("addToListDokter", this, new FragmentResultListener() {
//            @Override
//            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
//                Log.d("debugAddDokter","masuk aman");
//                String namaD = result.getString("namaDokter");
//                String spesialis = result.getString("spesialis");
//                String nomorHP = result.getString("nomorHP");
//                String detail = result.getString("detail");
//                Dokter dokTemp = new Dokter(namaD, spesialis, nomorHP, detail);
//                presenter.addListDokter(dokTemp);
//            }
//        });


        this.getParentFragmentManager().setFragmentResultListener("setMessage", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Log.d("debug dialog", "masuk dialog");
                String namaD = result.getString("namaDokter");
                String spesialis = result.getString("spesialis");
                String nomorHP = result.getString("nomorHP");
                String detail = result.getString("detail");
                DokterDialogFragment dialogFragmentDokter = new DokterDialogFragment();
                dialogFragmentDokter = dialogFragmentDokter.newInstance("Dialog Fragment");
                dialogFragmentDokter.setMessage(namaD, spesialis, nomorHP, detail);
                dialogFragmentDokter.show(fm, "message");
            }
        });

        binding.btnAddNewDokter.setOnClickListener(this::setOnclick);

        return binding.getRoot();
    }

    private void setOnclick(View view) {
        Bundle result = new Bundle();
        if(view.getId()==binding.btnAddNewDokter.getId()){
            result.putString("page","addDokterFragment");
            this.getParentFragmentManager().setFragmentResult("changePage",result);
        }
    }

    public static DokterFragment newInstance(String title, MainPresenter presenter){
        DokterFragment fragment = new DokterFragment();
        fragment.presenter = presenter;
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void updateListDokter(ArrayList<Dokter> dokters) {
        this.adapterDokter.update(dokters);
    }

    @Override
    public void resetAddFormDokter() {

    }
}
