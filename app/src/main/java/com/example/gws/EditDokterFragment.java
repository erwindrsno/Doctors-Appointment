package com.example.gws;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.gws.databinding.FragmentEditDokterBinding;

public class EditDokterFragment extends Fragment {
    FragmentEditDokterBinding binding;
    MainPresenter presenter;
    private int position;

    public EditDokterFragment(){}

    public static EditDokterFragment newInstance(String title, MainPresenter presenter){
        EditDokterFragment fragment = new EditDokterFragment();
        fragment.presenter = presenter;
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentEditDokterBinding.inflate(inflater);

        this.getParentFragmentManager().setFragmentResultListener("editPosition", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                position = result.getInt("position");
                Dokter dokTemp = presenter.getDokter(position);

                binding.etNamaDokter.setText(dokTemp.getNama());
                binding.etSpesialisDokter.setText(dokTemp.getSpesialis());
                binding.etNomorDokter.setText(dokTemp.getNomorHP());
                binding.etDetailDokter.setText(dokTemp.getDetail());
            }
        });

        this.binding.btnEditDokter.setOnClickListener(this::onClickSave);

        return binding.getRoot();
    }

    private void onClickSave(View view) {
        if(position>=0) {
            SQLiteManagerDokter sqLiteManagerDokter = new SQLiteManagerDokter(getContext());
            String namaD = binding.etNamaDokter.getText().toString();
            String spesialis = binding.etSpesialisDokter.getText().toString();
            String nomorHP = binding.etNomorDokter.getText().toString();
            String detail = binding.etDetailDokter.getText().toString();

            int id = presenter.getDokter(position).getId();

            sqLiteManagerDokter.editDokterDatabase(id, namaD, spesialis, nomorHP, detail);
            presenter.editDokter(position, namaD, spesialis, nomorHP, detail);

            Bundle result = new Bundle();
            result.putString("page","dokterFragment");
            this.getParentFragmentManager().setFragmentResult("changePage",result);
        }
    }
}
