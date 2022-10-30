package com.example.gws;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.gws.databinding.FragmentDrawerBinding;
import com.example.gws.databinding.ItemListMenuBinding;

import java.util.ArrayList;

class MenuAdapter extends BaseAdapter {
    private ArrayList<String> listMenu;
    private ItemListMenuBinding binding;
    private DrawerFragment drawerFragment;
    private LayoutInflater inflater;
    private Activity activity;

    public MenuAdapter(DrawerFragment drawerFragment, LayoutInflater inflater){
        this.listMenu = new ArrayList<>();
        this.drawerFragment = drawerFragment;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return listMenu.size();
    }

    @Override
    public String getItem(int i) {
        return this.listMenu.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            binding = ItemListMenuBinding.inflate(this.inflater);
            view = binding.getRoot();
            viewHolder = new ViewHolder(binding);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
//        Log.d("debugGetItem","getView aman bro");
        viewHolder.updateView(this.getItem(i));
        return view;
    }

    public void addList(String str){
        this.listMenu.add(str);
        this.notifyDataSetChanged();
    }

    private class ViewHolder implements View.OnClickListener{
        protected ItemListMenuBinding binding;

        public ViewHolder(ItemListMenuBinding binding){
            this.binding= binding;
            this.binding.tvMenu.setOnClickListener(this);
        }

        public void updateView(String menu){
            Log.d("debugUpdateView","masuk bos aman");
            this.binding.tvMenu.setText(menu);
        }

        @Override
        public void onClick(View view){
            Bundle result = new Bundle();
            Log.d("debugMasukOnClick","masukOnClick");
            if(view.getId() == binding.tvMenu.getId()){
                if(binding.tvMenu.getText().equals("Home")){
                    Log.d("debugHome","masukHome");
                    result.putString("page","homeFragment");
                }
                else if(binding.tvMenu.getText().equals("Pertemuan")){
                    result.putString("page","pertemuanFragment");
                }
                else if(binding.tvMenu.getText().equals("Dokter")){
                    result.putString("page","dokterFragment");
                }
                else if(binding.tvMenu.getText().equals("Exit")){
                    result.putString("page","exit");
                }
            }
            notifyDataSetChanged();
        }
    }
}