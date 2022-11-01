package com.example.gws;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gws.databinding.ItemListDokterBinding;
import com.example.gws.databinding.ItemListPertemuanBinding;

import java.util.ArrayList;

public class AdapterPertemuan extends BaseAdapter{
    private ArrayList<Pertemuan> pertemuans;
    private ItemListPertemuanBinding binding;
    private MainPresenter presenter;
    private PertemuanFragment fragment;

    public AdapterPertemuan(MainPresenter presenter, PertemuanFragment fragment){
        super();
        this.pertemuans = new ArrayList<>();
        this.presenter = presenter;
        this.fragment = fragment;
    }

    @Override
    public int getCount() {
        return pertemuans.size();
    }

    @Override
    public Pertemuan getItem(int i) {
        return pertemuans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        binding = ItemListPertemuanBinding.inflate(LayoutInflater.from(viewGroup.getContext()));
        if(view==null){
            view = binding.getRoot();
            viewHolder = new ViewHolder(view, binding, i, this.presenter, this.fragment);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.updateView(this.pertemuans.get(i));

        return view;
    }

    public void update(ArrayList<Pertemuan> listPertemuan){
        this.pertemuans = listPertemuan;
        notifyDataSetChanged();
    }

    private class ViewHolder implements View.OnClickListener{

        ItemListPertemuanBinding binding;
        protected TextView namaDokter;
        protected TextView waktu;
        protected Pertemuan pertemuanTemp;
        private MainPresenter presenter;
        private int position;
        private PertemuanFragment fragment;

        public ViewHolder(View view, ItemListPertemuanBinding binding, int i, MainPresenter presenter, PertemuanFragment fragment){
            this.binding = binding;
            this.position = i;
            this.presenter = presenter;
            this.fragment = fragment;

            this.binding.btnDelPertemuan.setOnClickListener(this);
            this.binding.btnCheckboxPertemuan.setOnClickListener(this);
            this.binding.listItemPertemuan.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view.getId() == binding.btnDelPertemuan.getId()){
                presenter.delListPertemuan(position);
                notifyDataSetChanged();
            }
            else if(view.getId() == binding.btnCheckboxPertemuan.getId()){

            }
            else if(view.getId() == binding.listItemPertemuan.getId()){

            }
        }

        public void updateView(Pertemuan pertemuan){
            this.pertemuanTemp = pertemuan;
            Log.d("debugPertemuanNamaDokter",this.pertemuanTemp.getNamaDokter().toString());
            Log.d("debugPertemuanWaktuDokter",this.pertemuanTemp.getWaktu().toString());
            binding.itemNamaDokter.setText(this.pertemuanTemp.getNamaDokter().toString());
            binding.itemWaktuPertemuan.setText(this.pertemuanTemp.getTanggal().toString());
        }
    }
}
