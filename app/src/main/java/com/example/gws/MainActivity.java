package com.example.gws;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.gws.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{
    private ActivityMainBinding binding;
    private HomeFragment homeFragment;
    private BuatPertemuanFragment buatPertemuanFragment;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        this.homeFragment = HomeFragment.newInstance("homeFragment");
        this.buatPertemuanFragment = BuatPertemuanFragment.newInstance("buatPertemuanFragment");

        this.fm = this.getSupportFragmentManager();
        FragmentTransaction ft = this.fm.beginTransaction();

        ft.add(binding.fragmentContainer.getId(), this.homeFragment)
                .addToBackStack(null)
                .commit();

        this.getSupportFragmentManager().setFragmentResultListener("changePage", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String page = result.getString("page");
                Log.d("debugPage",page);
                changePage(page);
            }
        });
    }

    public void changePage(String page){
        FragmentTransaction ft = this.fm.beginTransaction();
        if(page.equals("homeFragment")){
            if(this.homeFragment.isAdded()){
                ft.show(this.homeFragment);
            }
            else{
                ft.add(R.id.fragment_container,this.homeFragment);
            }
            if(this.buatPertemuanFragment.isAdded()){
                ft.hide(this.buatPertemuanFragment);
            }
        }
        else if(page.equals("buatPertemuanFragment")){
            Log.d("debugBuatPertemuanFragment","sya disini");
            if(this.buatPertemuanFragment.isAdded()){
                ft.show(this.buatPertemuanFragment);
            }
            else{
                ft.add(R.id.fragment_container,this.buatPertemuanFragment);
            }
            if(this.homeFragment.isAdded()){
                ft.hide(this.homeFragment);
            }
        }
        ft.commit();
    }
}