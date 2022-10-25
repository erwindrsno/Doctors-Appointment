package com.example.gws;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.gws.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = binding.getRoot();
        setContentView(view);

        binding.btnBuatPertemuan.setOnClickListener(this);
    }

    @Override
    protected void OnClick(View view){
        if(view == binding.btnBuatPertemuan){
            //pindah halaman bla3
        }
    }
}