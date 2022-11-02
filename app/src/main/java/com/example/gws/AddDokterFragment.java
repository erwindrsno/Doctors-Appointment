package com.example.gws;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.gws.databinding.FragmentAddDokterBinding;

public class AddDokterFragment extends Fragment {
    private FragmentAddDokterBinding binding;
    private MainPresenter presenter;

    public AddDokterFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentAddDokterBinding.inflate(inflater);
        //this.presenter = new MainPresenter(this);
        binding.btnAddDokter.setOnClickListener(this::setOnClick);

        return binding.getRoot();
    }

    public static AddDokterFragment newInstance(String title, MainPresenter presenter){
        AddDokterFragment fragment = new AddDokterFragment();
        fragment.presenter = presenter;
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    private void setOnClick(View view) {
        if(view.getId()==binding.btnAddDokter.getId()){
            SQLiteManagerDokter sqLiteManagerDokter = new SQLiteManagerDokter(getContext());
            String namaD = binding.etNamaDokter.getText().toString();
            String spesialis = binding.etSpesialisDokter.getText().toString();
            String nomorHP = binding.etNomorDokter.getText().toString();
            String detail = binding.etDetailDokter.getText().toString();

            int max = 999;
            int min = 1;
            int range = max - min + 1;
            int rand = (int) (Math.random()*range)+min;

            int id = presenter.getSizeDokter()+rand;

            sqLiteManagerDokter.addDokterToDatabase(namaD, spesialis, nomorHP, detail);
            Dokter tempDokter = new Dokter(id, namaD,spesialis, nomorHP, detail);
            presenter.addListDokter(tempDokter);
            Bundle result = new Bundle();
            result.putString("page","dokterFragment");
            this.getParentFragmentManager().setFragmentResult("changePage",result);

            binding.etNamaDokter.setText("");
            binding.etSpesialisDokter.setText("");
            binding.etNomorDokter.setText("");
            binding.etDetailDokter.setText("");

        }
    }
}
