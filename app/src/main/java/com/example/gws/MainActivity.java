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

public class MainActivity extends AppCompatActivity implements InterfaceDokter, InterfacePertemuan{
    private ActivityMainBinding binding;
    private HomeFragment homeFragment;
    private BuatPertemuanFragment buatPertemuanFragment;
    private PertemuanFragment pertemuanFragment;
    private DokterFragment dokterFragment;
    private AddDokterFragment addDokterFragment;
    private ListDokterDialogFragment listDokterDialogFragment;
    private EditDokterFragment editDokterFragment;
    private PertemuanDialogFragment pertemuanDialogFragment;
    private FragmentManager fm;

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle abdt;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        this.presenter = new MainPresenter(this, this);

        //inisialiasi fragment dokter
        this.homeFragment = HomeFragment.newInstance("homeFragment");
        this.dokterFragment = DokterFragment.newInstance("dokterFragment", this.presenter);
        this.addDokterFragment = AddDokterFragment.newInstance("addDokterFragment", this.presenter);
        this.editDokterFragment = EditDokterFragment.newInstance("editDokterFragment", this.presenter);
        //inisialisasi fragment dokter

        //inisialisasi fragment pertemuan
        this.pertemuanDialogFragment = PertemuanDialogFragment.newInstance("pertemuanDialogFragment");
        this.listDokterDialogFragment = ListDokterDialogFragment.newInstance("listDokterDialogFragment", this.presenter);
        this.buatPertemuanFragment = BuatPertemuanFragment.newInstance("buatPertemuanFragment",this.presenter, this.listDokterDialogFragment);
        this.pertemuanFragment = PertemuanFragment.newInstance("pertemuanFragment",this.presenter, pertemuanDialogFragment);
        //inisialisasi fragment pertemuan

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
            if(this.editDokterFragment.isAdded()){
                ft.hide(this.editDokterFragment);
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
            if(this.editDokterFragment.isAdded()){
                ft.hide(this.editDokterFragment);
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
            if(this.editDokterFragment.isAdded()){
                ft.hide(this.editDokterFragment);
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
            if(this.editDokterFragment.isAdded()){
                ft.hide(this.editDokterFragment);
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
            if(this.editDokterFragment.isAdded()){
                ft.hide(this.editDokterFragment);
            }
        }
        else if(page.equals("editDokterFragment")){
            if(this.editDokterFragment.isAdded()){
                ft.show(this.editDokterFragment);
            }
            else {
                ft.add(R.id.fragment_container, this.editDokterFragment);
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
            if(this.addDokterFragment.isAdded()){
                ft.hide(this.addDokterFragment);
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

    @Override
    public void updateListDokter(ArrayList<Dokter> dokters) {
        dokterFragment.updateListDokter(dokters);
        listDokterDialogFragment.updateListDokter(dokters);
    }

    @Override
    public void resetAddFormDokter() {

    }

    @Override
    public void updateListPertemuan(ArrayList<Pertemuan> pertemuans) {
        pertemuanFragment.updateListPertemuan(pertemuans);
    }

    @Override
    public void resetAddFormPertemuan() {

    }
}