package com.example.gws;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import com.example.gws.databinding.FragmentBuatPertemuanBinding;
import com.example.gws.databinding.FragmentPertemuanBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class PertemuanFragment extends Fragment implements View.OnClickListener, InterfacePertemuan{
    private FragmentPertemuanBinding binding;
    private MainPresenter presenter;
    private AdapterPertemuan adapterPertemuan;
    private PertemuanDialogFragment pertemuanDialogFragment;

    public PertemuanFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentPertemuanBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        this.adapterPertemuan = new AdapterPertemuan(presenter, this);
        this.binding.listPertemuan.setAdapter(adapterPertemuan);

        SQLiteManagerPertemuan sqLiteManagerPertemuan = new SQLiteManagerPertemuan(getContext());
        Cursor data = sqLiteManagerPertemuan.loadPertemuan();

        FragmentManager fm = this.getParentFragmentManager();
        this.getParentFragmentManager().setFragmentResultListener("setPertemuan", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Log.d("debug dialog", "masuk dialog");

                String tanggal = result.getString("tanggal");
                String waktu = result.getString("waktu");
                String namapasien = result.getString("namapasien");
                String namadokter = result.getString("namadokter");
                String keluhan = result.getString("keluhan");
                String spesialis = result.getString("spesialis");
                PertemuanDialogFragment pertemuanDialogFragment = new PertemuanDialogFragment();
                pertemuanDialogFragment = pertemuanDialogFragment.newInstance("Dialog Fragment Pertemuan");
                pertemuanDialogFragment.setMessagePertemuan(tanggal, waktu, namapasien, namadokter, spesialis, keluhan);
                pertemuanDialogFragment.show(fm, "message");
            }
        });

        binding.btnAddPertemuan.setOnClickListener(this);

        while (data.moveToNext()){
            int id = data.getInt(0);
            String tanggal = data.getString(1);
            String waktu = data.getString(2);
            String namapasien = data.getString(3);
            String namadokter = data.getString(4);
            String keluhan = data.getString(5);
            String spesialis = data.getString(6);
            String gender = data.getString(7);
            String done = data.getString(8);
            boolean isDone;
            if(done.equals("Y")) isDone = true;
            else isDone=false;
            Pertemuan perT = new Pertemuan(id, tanggal, waktu, namapasien, namadokter, spesialis, keluhan, gender, isDone);
            presenter.addListPertemuan(perT);
        }

        return view;
    }

    public static PertemuanFragment newInstance(String title, MainPresenter presenter, PertemuanDialogFragment pertemuanDialogFragment){
        PertemuanFragment fragment = new PertemuanFragment();
        fragment.pertemuanDialogFragment = pertemuanDialogFragment;
        fragment.presenter = presenter;
        Bundle args = new Bundle();
        args.putString("title",title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == binding.btnAddPertemuan.getId()){
            Bundle result = new Bundle();
            result.putString("page","buatPertemuanFragment");
            this.getParentFragmentManager().setFragmentResult("changePage",result);
        }
    }

    @Override
    public void updateListPertemuan(ArrayList<Pertemuan> pertemuans) {
        this.adapterPertemuan.update(pertemuans);
    }

    @Override
    public void resetAddFormPertemuan() {

    }
}
