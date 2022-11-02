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

import java.util.ArrayList;

public class AdapterDokter extends BaseAdapter {
    private ArrayList<Dokter> dokters;
    private ItemListDokterBinding binding;
    private MainPresenter presenter;
    private DokterFragment fragment;

    public AdapterDokter(MainPresenter presenter, DokterFragment fragment){
        super();
        this.dokters = new ArrayList<>();
        this.presenter = presenter;
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
        binding = ItemListDokterBinding.inflate(LayoutInflater.from(viewGroup.getContext()));
        if(view==null){
            view = binding.getRoot();
            viewHolder = new ViewHolder(view, binding, i, this.presenter, this.fragment);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.updateView(this.dokters.get(i));

        return view;
    }

    public void update(ArrayList<Dokter> listDokter){
        this.dokters = listDokter;
        notifyDataSetChanged();
    }

    private class ViewHolder{

        ItemListDokterBinding binding;
        protected TextView nama;
        protected TextView spesialis;
        protected Dokter dokterTemp;
        private MainPresenter presenter;
        private int position;
        private DokterFragment fragment;

        public ViewHolder(View view, ItemListDokterBinding binding, int i, MainPresenter presenter, DokterFragment fragment){
            this.binding = binding;
            this.nama = binding.itemNameDokter;
            this.spesialis = binding.itemSpesialisDokter;
            this.position = i;
            this.presenter = presenter;
            this.fragment = fragment;

            binding.btnStarDokter.setOnClickListener(this::onClickStar);
            binding.btnCallDokter.setOnClickListener(this::onClickCall);
            binding.btnDelDokter.setOnClickListener(this::onClickDel);
            binding.listItemDialog.setOnClickListener(this::onClickDetail);
            binding.btnEditDokter.setOnClickListener(this::onClickEdit);
        }

        private void onClickEdit(View view) {
            Bundle result = new Bundle();
            result.putString("page","editDokterFragment");
            result.putInt("position", this.position);
            fragment.getParentFragmentManager().setFragmentResult("editPosition", result);
            fragment.getParentFragmentManager().setFragmentResult("changePage",result);
        }

        private void onClickDetail(View view) {

            Log.d("debug dialog", "masuk dialog set message");

            Bundle result = new Bundle();
            String nama = dokterTemp.getNama();
            String spesialis = dokterTemp.getSpesialis();
            String nomorHP = dokterTemp.getNomorHP();
            String detail = dokterTemp.getDetail();

            result.putString("namaDokter",nama);
            result.putString("spesialis",spesialis);
            result.putString("nomorHP",nomorHP);
            result.putString("detail",detail);


            fragment.getParentFragmentManager().setFragmentResult("setMessage",result);
        }

        private void onClickDel(View view) {
            if(view==binding.btnDelDokter){
                SQLiteManagerDokter sqLiteManagerDokter = new SQLiteManagerDokter(fragment.getContext());
                presenter.delListDokter(position);
                int id = dokterTemp.getId();
                sqLiteManagerDokter.deleteDokterDatabase(id);
            }
            notifyDataSetChanged();
        }

        private void onClickCall(View view) {
            String no = this.dokterTemp.getNomorHP();
            Uri uri = Uri.parse("tel:"+no);
            Log.d("debug call", "call"+no);
            Intent callIntent = new Intent(Intent.ACTION_DIAL, uri);
            fragment.startActivity(callIntent);
        }

        private void onClickStar(View view) {
            if(view == binding.btnStarDokter){
                if(this.dokterTemp.isFav()==true){
                    presenter.toggleFavDokter(position);
                    binding.btnStarDokter.setImageResource(android.R.drawable.btn_star_big_off);
                }
                else{
                    presenter.toggleFavDokter(position);
                    binding.btnStarDokter.setImageResource(android.R.drawable.btn_star_big_on);
                }
            }
            notifyDataSetChanged();
        }

        public void updateView(Dokter dokter){
            this.dokterTemp = dokter;
            this.nama.setText(dokterTemp.getNama());
            this.spesialis.setText(dokterTemp.getSpesialis());
        }

    }
}
