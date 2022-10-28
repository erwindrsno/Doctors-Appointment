package com.example.gws;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.gws.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private FragmentHomeBinding binding;

    public HomeFragment(){
        //Konstruktor harus kosong
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentHomeBinding.inflate(inflater);
        View view = binding.getRoot();
//        binding.btSubmit.setOnClickListener(this);
        //untuk mengubah text dari argumen yang dikirimkan saat instansiasi fragment
//        this.binding.tvTitleSecondFragment.setText(this.getArguments().getString("title"));
        binding.btnBuatPertemuan.setOnClickListener(this);
        return view;
    }

    public static HomeFragment newInstance(String title){
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle(); //digunakan untuk menyimpan kuimpulan data
        args.putString("title",title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View view){
        Bundle result = new Bundle();
        if(view.getId() == binding.btnBuatPertemuan.getId()){
            result.putString("page","buatPertemuanFragment");
            this.getParentFragmentManager().setFragmentResult("changePage",result);
        }
//        result.putString("page","buatPertemuanFragment");
//        result.putString("message",binding.etMessage.getText().toString());
//        this.getParentFragmentManager().setFragmentResult("changePage",result);
//        this.getParentFragmentManager().setFragmentResult("setMessage",result);
    }
}