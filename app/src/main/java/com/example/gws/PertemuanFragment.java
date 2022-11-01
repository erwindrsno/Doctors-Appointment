package com.example.gws;

import android.app.DatePickerDialog;
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

import androidx.fragment.app.Fragment;

import com.example.gws.databinding.FragmentBuatPertemuanBinding;
import com.example.gws.databinding.FragmentPertemuanBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class PertemuanFragment extends Fragment implements View.OnClickListener, InterfacePertemuan{
    private FragmentPertemuanBinding binding;
    private MainPresenter presenter;
    private AdapterPertemuan adapterPertemuan;

    public PertemuanFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentPertemuanBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        this.adapterPertemuan = new AdapterPertemuan(presenter, this);
        this.binding.listPertemuan.setAdapter(adapterPertemuan);

        binding.btnAddPertemuan.setOnClickListener(this);
        return view;
    }

    public static PertemuanFragment newInstance(String title, MainPresenter presenter){
        PertemuanFragment fragment = new PertemuanFragment();
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
