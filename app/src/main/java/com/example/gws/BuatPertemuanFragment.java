package com.example.gws;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.gws.databinding.FragmentBuatPertemuanBinding;

import java.util.zip.Inflater;

public class BuatPertemuanFragment extends Fragment implements View.OnClickListener{
    private FragmentBuatPertemuanBinding binding;

    public BuatPertemuanFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentBuatPertemuanBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }

    public static BuatPertemuanFragment newInstance(String title){
        BuatPertemuanFragment fragment = new BuatPertemuanFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        fragment.setArguments(args);
        return fragment;
    }

    public void onClick(View view){
        Bundle result = new Bundle();
        result.putString("page","1");
        this.getParentFragmentManager().setFragmentResult("changePage",result);
//        if(view == btnHome){
//            result.putInt("page",1);
//            this.getParentFragmentManager().setFragmentResult("changePage",result);
//        }
//        else if(view == btnPage2){
//            result.putInt("page",2);
//        }
//        else{
//            result.putInt("page",2);
//            this.getParentFragmentManager().setFragmentResult("changePage",result);
//        }
    }
}
