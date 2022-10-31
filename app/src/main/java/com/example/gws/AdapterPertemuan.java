//package com.example.gws;
//
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.example.gws.databinding.ItemListDokterBinding;
//import com.example.gws.databinding.ItemListPertemuanBinding;
//
//import java.util.ArrayList;
//
//public class AdapterPertemuan extends BaseAdapter {
//    private ArrayList<Pertemuan> pertemuans;
//    private ItemListPertemuanBinding binding;
//    private MainPresenter presenter;
//    private PertemuanFragment fragment;
//
//    public AdapterPertemuan(MainPresenter presenter, PertemuanFragment fragment){
//        super();
//        this.pertemuans = new ArrayList<>();
//        this.presenter = presenter;
//        this.fragment = fragment;
//    }
//
//    @Override
//    public int getCount() {
//        return pertemuans.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return pertemuans.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        ViewHolder viewHolder;
//        binding = ItemListPertemuanBinding.inflate(LayoutInflater.from(viewGroup.getContext()));
//        if(view==null){
//            view = binding.getRoot();
//            viewHolder = new ViewHolder(view, binding, i, this.presenter, this.fragment);
//            view.setTag(viewHolder);
//        }
//        else{
//            viewHolder = (ViewHolder) view.getTag();
//        }
//        viewHolder.updateView(this.pertemuans.get(i));
//
//        return view;
//    }
//
//    public void update(ArrayList<Pertemuan> listPertemuan){
//        this.pertemuans = listPertemuan;
//        notifyDataSetChanged();
//    }
//
//    private class ViewHolder{
//
//        ItemListPertemuanBinding binding;
//        protected TextView namaDokter;
//        protected TextView waktu;
//        protected Pertemuan pertemuanTemp;
//        private MainPresenter presenter;
//        private int position;
//        private PertemuanFragment fragment;
//
//        public ViewHolder(View view, ItemListPertemuanBinding binding, int i, MainPresenter presenter, PertemuanFragment fragment){
//            this.binding = binding;
//            this.position = i;
//            this.presenter = presenter;
//            this.fragment = fragment;
//
//            this.binding.btnDelPertemuan.setOnClickListener(this::onClickDelete);
//            this.binding.btnCheckboxPertemuan.setOnClickListener(this::onClickCheckBox);
//            this.binding.listItemDialogPertemuan.setOnClickListener(this::onClickDetail);
////            binding.btnStarDokter.setOnClickListener(this::onClickStar);
////            binding.btnCallDokter.setOnClickListener(this::onClickCall);
////            binding.btnDelDokter.setOnClickListener(this::onClickDel);
////            binding.listItemDialog.setOnClickListener(this::onClickDetail);
//        }
//
//        private void onClickDetail(View view) {
//
//            Log.d("debug dialog", "masuk dialog set message");
//
//            Bundle result = new Bundle();
//            String nama =
////            String nama = dokterTemp.getNama();
////            String spesialis = dokterTemp.getSpesialis();
////            String nomorHP = dokterTemp.getNomorHP();
////            String detail = dokterTemp.getDetail();
//
////            result.putString("namaDokter",nama);
////            result.putString("spesialis",spesialis);
////            result.putString("nomorHP",nomorHP);
////            result.putString("detail",detail);
//
//
//            fragment.getParentFragmentManager().setFragmentResult("setMessage",result);
//        }
//
//        private void onClickDel(View view) {
//            if(view==binding.btnDelPertemuan){
//                presenter.delListDokter(position);
//            }
//            notifyDataSetChanged();
//        }
//
////        private void onClickCall(View view) {
////            String no = this.dokterTemp.getNomorHP();
////            Uri uri = Uri.parse("tel:"+no);
////            Log.d("debug call", "call"+no);
////            Intent callIntent = new Intent(Intent.ACTION_DIAL, uri);
////            fragment.startActivity(callIntent);
////        }
//
////        private void onClickStar(View view) {
////            if(view == binding.btnStarDokter){
////                if(this.dokterTemp.isFav()==true){
////                    presenter.toggleFavDokter(position);
////                    binding.btnStarDokter.setImageResource(android.R.drawable.btn_star_big_off);
////                }
////                else{
////                    presenter.toggleFavDokter(position);
////                    binding.btnStarDokter.setImageResource(android.R.drawable.btn_star_big_on);
////                }
////            }
////            notifyDataSetChanged();
////        }
//
//        public void updateView(Pertemuan pertemuan){
//            this.pertemuanTemp = pertemuan;
//            this.namaDokter.setText(this.pertemuanTemp.getNamaDokter());
//            this.waktu.setText(this.pertemuanTemp.getWaktu().toString());
//        }
//
//    }
//}
