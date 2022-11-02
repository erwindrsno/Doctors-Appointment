package com.example.gws;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

import com.example.gws.databinding.FragmentListDokterDialogBinding;

import java.util.ArrayList;

public class ListDokterDialogFragment extends DialogFragment implements InterfaceDokter{
    private String nama;
    private String spesialis;
    private MainPresenter presenter;
    FragmentListDokterDialogBinding binding;
    AdapterDokterDialog adapterDokterDialog;

    public ListDokterDialogFragment(){}

    public static ListDokterDialogFragment newInstance(String title, MainPresenter presenter){
        ListDokterDialogFragment fragment = new ListDokterDialogFragment();
        fragment.presenter = presenter;
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentListDokterDialogBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        SQLiteManagerDokter sqLiteManagerDokter = new SQLiteManagerDokter(getContext());
        Cursor data = sqLiteManagerDokter.loadDokters();
        ArrayList<Dokter> dokters = new ArrayList<>();

        while (data.moveToNext()){
            int id = data.getInt(0);
            String nama = data.getString(1);
            String spesialis = data.getString(2);
            String nomorHP = data.getString(3);
            String detail = data.getString(4);
            Dokter dt = new Dokter(id, nama, spesialis, nomorHP, detail);
            dokters.add(dt);
        }

        Log.d("debugSizeDokter", dokters.size()+"");
        this.adapterDokterDialog = new AdapterDokterDialog(this);
        this.adapterDokterDialog.update(dokters);
        this.binding.lvListDokterDialog.setAdapter(adapterDokterDialog);



        return view;
    }


    @Override
    public void updateListDokter(ArrayList<Dokter> dokters) {
        if(this.adapterDokterDialog==null){
            adapterDokterDialog = new AdapterDokterDialog(this);
            adapterDokterDialog.update(dokters);
        }
//        adapterDokterDialog.update(dokters);
    }

    @Override
    public void resetAddFormDokter() {

    }
}
