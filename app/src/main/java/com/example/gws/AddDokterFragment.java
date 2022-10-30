package com.example.gws;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.gws.databinding.FragmentAddDokterBinding;

public class AddDokterFragment extends Fragment {
    private FragmentAddDokterBinding binding;
    MainPresenter presenter;

    public AddDokterFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentAddDokterBinding.inflate(inflater);
        //this.presenter = new MainPresenter(this);
        binding.btnAddDokter.setOnClickListener(this::setOnClick);

        return binding.getRoot();
    }

    public static AddDokterFragment newInstance(String title){
        AddDokterFragment fragment = new AddDokterFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    private void setOnClick(View view) {
//        Bundle result = new Bundle();
//        if(view.getId()==binding.btnAddDokter.getId()){
//            result.putString("page","dokterFragment");
//            this.getParentFragmentManager().setFragmentResult("changePage",result);
//        }
        if(view.getId()==binding.btnAddDokter.getId()){
            String namaD = binding.etNamaDokter.getText().toString();
            String spesialis = binding.etSpesialisDokter.getText().toString();
            String nomorHP = binding.etNomorDokter.getText().toString();
            String detail = binding.etDetailDokter.getText().toString();
            //this.presenter.addListDokter(namaD, spesialis, nomorHP,detail);
            Bundle result = new Bundle();
            result.putString("page","dokterFragment");
            result.putString("namaDokter",namaD);
            result.putString("spesialis",spesialis);
            result.putString("nomorHP",nomorHP);
            result.putString("detail",detail);
            this.getParentFragmentManager().setFragmentResult("changePage",result);
            this.getParentFragmentManager().setFragmentResult("addToListDokter",result);

            binding.etNamaDokter.setText("");
            binding.etSpesialisDokter.setText("");
            binding.etNomorDokter.setText("");
            binding.etDetailDokter.setText("");

        }
    }
}
