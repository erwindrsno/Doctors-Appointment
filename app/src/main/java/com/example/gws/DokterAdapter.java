package com.example.gws;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gws.databinding.ItemListDokterBinding;

import java.util.ArrayList;

public class DokterAdapter extends BaseAdapter {

    private ArrayList<Dokter> dokters;
    private Activity activity;
    private ItemListDokterBinding binding;
    //presenter;

    public DokterAdapter(Activity activity){
        super();
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        binding = ItemListDokterBinding.inflate(this.activity.getLayoutInflater(),viewGroup,false);
        if(view==null){
            view = binding.getRoot();
            viewHolder = new ViewHolder(view, binding, i);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.
    }

    public class ViewHolder{
        ItemListDokterBinding binding;
        protected TextView nama;
        protected TextView spesialis;
        protected String nomorHP;
        protected int position;
        //presenter

        public ViewHolder(View view, ItemListDokterBinding binding, int i) {
            this.binding = binding;
            this.nama = binding.itemNameDokter;
            this.spesialis = binding.itemSpesialisDokter;
            this.position = i;

            //setonclick
        }

        public void updateView(){

        }
    }
}
