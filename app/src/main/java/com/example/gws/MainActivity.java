package com.example.gws;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.gws.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private ActivityMainBinding binding;
    private HomeFragment homeFragment;
    private BuatPertemuanFragment buatPertemuanFragment;
    private PertemuanFragment pertemuanFragment;
    private DokterFragment dokterFragment;
    private AddDokterFragment addDokterFragment;
    private FragmentManager fm;

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle abdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //inisialiasi fragment
        this.homeFragment = HomeFragment.newInstance("homeFragment");
        this.buatPertemuanFragment = BuatPertemuanFragment.newInstance("buatPertemuanFragment");
        this.pertemuanFragment = PertemuanFragment.newInstance("pertemuanFragment");
        this.dokterFragment = DokterFragment.newInstance("dokterFragment");
        this.addDokterFragment = AddDokterFragment.newInstance("addDokterFragment");
        //inisialisasi fragment

        //Toolbar
        this.toolbar = binding.toolbar;
        this.setSupportActionBar(toolbar);

        this.drawer = binding.drawerLayout;

        //tombol garis tiga
        abdt = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open_drawer,R.string.close_drawer);
        drawer.addDrawerListener(abdt);
        abdt.syncState();
        //Toolbar

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
                drawer.closeDrawers();
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
                ft.add(binding.fragmentContainer.getId(),this.homeFragment);
            }
            if(this.buatPertemuanFragment.isAdded()){
                ft.hide(this.buatPertemuanFragment);
            }
            if(this.pertemuanFragment.isAdded()){
                ft.hide(this.pertemuanFragment);
            }
            if(this.dokterFragment.isAdded()){
                ft.hide(this.dokterFragment);
            }
            if(this.addDokterFragment.isAdded()){
                ft.hide(this.addDokterFragment);
            }
        }
        else if(page.equals("buatPertemuanFragment")){
            if(this.buatPertemuanFragment.isAdded()){
                ft.show(this.buatPertemuanFragment);
            }
            else{
                ft.add(binding.fragmentContainer.getId(),this.buatPertemuanFragment)
                        .addToBackStack(null);
            }
            if(this.homeFragment.isAdded()){
                ft.hide(this.homeFragment);
            }
            if(this.pertemuanFragment.isAdded()){
                ft.hide(this.pertemuanFragment);
            }
            if(this.dokterFragment.isAdded()){
                ft.hide(this.dokterFragment);
            }
            if(this.addDokterFragment.isAdded()){
                ft.hide(this.addDokterFragment);
            }
        }
        else if(page.equals("pertemuanFragment")){
            if(this.pertemuanFragment.isAdded()){
                ft.show(this.pertemuanFragment);
            }
            else{
                ft.add(binding.fragmentContainer.getId(),this.pertemuanFragment)
                        .addToBackStack(null);
            }
            if(this.homeFragment.isAdded()) {
                ft.hide(this.homeFragment);
            }
            if(this.buatPertemuanFragment.isAdded()){
                ft.hide(this.buatPertemuanFragment);
            }
            if(this.dokterFragment.isAdded()){
                ft.hide(this.dokterFragment);
            }
            if(this.addDokterFragment.isAdded()){
                ft.hide(this.addDokterFragment);
            }
        }
        else if(page.equals("dokterFragment")){
            if(this.dokterFragment.isAdded()){
                ft.show(this.dokterFragment);
            }
            else{
                ft.add(binding.fragmentContainer.getId(),this.dokterFragment)
                        .addToBackStack(null);
            }
            if(this.homeFragment.isAdded()) {
                ft.hide(this.homeFragment);
            }
            if(this.buatPertemuanFragment.isAdded()){
                ft.hide(this.buatPertemuanFragment);
            }
            if(this.pertemuanFragment.isAdded()){
                ft.hide(this.pertemuanFragment);
            }
            if(this.addDokterFragment.isAdded()){
                ft.hide(this.addDokterFragment);
            }
        }
        else if(page.equals("addDokterFragment")){
            if(this.addDokterFragment.isAdded()){
                ft.show(this.addDokterFragment);
            }
            else{
                ft.add(binding.fragmentContainer.getId(),this.addDokterFragment)
                        .addToBackStack(null);
            }
            if(this.homeFragment.isAdded()) {
                ft.hide(this.homeFragment);
            }
            if(this.buatPertemuanFragment.isAdded()){
                ft.hide(this.buatPertemuanFragment);
            }
            if(this.pertemuanFragment.isAdded()){
                ft.hide(this.pertemuanFragment);
            }
            if(this.dokterFragment.isAdded()){
                ft.hide(this.dokterFragment);
            }
        }
        else if(page.equals("exit")){
            closeApplication();
        }
        ft.commit();
    }

    public void closeApplication(){
        this.moveTaskToBack(true);
        this.finish();

    }
}