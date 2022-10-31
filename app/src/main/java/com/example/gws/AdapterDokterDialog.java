package com.example.gws;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gws.databinding.ItemListDokterBinding;
import com.example.gws.databinding.ItemListDokterDialogBinding;

import java.util.ArrayList;

public class AdapterDokterDialog extends BaseAdapter {

    private ArrayList<Dokter> dokters;
    private MainPresenter presenter;
    private  ListDokterDialogFragment fragment;
    ItemListDokterDialogBinding binding;

    public AdapterDokterDialog(ArrayList<Dokter> dokters, ListDokterDialogFragment fragment){
        super();
        this.dokters = dokters;
        this.fragment = fragment;
    }

    @Override
    public int getCount() {
        return dokters.size();
    }

    @Override
    public Object getItem(int i) {
        return dokters.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        this.binding = ItemListDokterDialogBinding.inflate(LayoutInflater.from(viewGroup.getContext()));

        if(view==null){
            view = binding.getRoot();
            viewHolder = new ViewHolder(view, binding, i, this.fragment);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.updateView(this.dokters.get(i));

        return null;
    }

    private class ViewHolder{
        ItemListDokterDialogBinding binding;
        protected TextView nama;
        protected TextView spesialis;
        protected Dokter dokterTemp;
        private MainPresenter presenter;
        private int position;
        private ListDokterDialogFragment fragment;

        public ViewHolder(View view, ItemListDokterDialogBinding binding, int i, ListDokterDialogFragment fragment){
            this.binding = binding;
            this.nama = binding.tvDokterNameDialog;
            this.spesialis = binding.tvDokterSpesialisDialog;
            this.position = i;
            this.fragment = fragment;

            this.binding.dialogDetailDokter.setOnClickListener(this::onClickPilih);
        }

        private void onClickPilih(View view) {
            Bundle dokterD = new Bundle();
            String nama = dokterTemp.getNama();
            String spesialis = dokterTemp.getSpesialis();
            dokterD.putString("namaDokter", nama);
            dokterD.putString("spesialisDokter", spesialis);

            fragment.getParentFragmentManager().setFragmentResult("pilihDokter",dokterD);
        }

        public void updateView(Dokter dokter){
            this.dokterTemp = dokter;
            this.nama.setText(dokterTemp.getNama());
            this.spesialis.setText(dokterTemp.getSpesialis());
        }
    }

}
