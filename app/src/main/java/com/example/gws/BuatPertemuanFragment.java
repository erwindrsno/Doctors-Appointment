package com.example.gws;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import com.example.gws.databinding.FragmentBuatPertemuanBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.zip.Inflater;

public class BuatPertemuanFragment extends Fragment implements View.OnClickListener, InterfacePertemuan{
    private FragmentBuatPertemuanBinding binding;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private DatePickerDialog.OnDateSetListener setListener;
    TimePickerDialog.OnTimeSetListener timeSetListener;
    private MainPresenter presenter;
    private int minute;
    private int hour;
//    private Spinner spinner;

    public BuatPertemuanFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentBuatPertemuanBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        binding.btnTglLahir.setOnClickListener(this);
        binding.btnPilihDokter.setOnClickListener(this);
        binding.btnWaktuPertemuan.setOnClickListener(this);

//        spinner = binding.spinnerDokter;
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BuatPertemuanFragment.this.getActivity(),
//                R.array.doctors_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);

        return view;
    }

    public static BuatPertemuanFragment newInstance(String title, MainPresenter presenter){
        BuatPertemuanFragment fragment = new BuatPertemuanFragment();
        fragment.presenter = presenter;
        Bundle args = new Bundle();
        args.putString("title",title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View view) {
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        Log.d("debughaha1","masuk1");
        if (view.getId() == binding.btnTglLahir.getId()) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(BuatPertemuanFragment.this.getActivity(),android.R.style.
                    Theme_Holo_Light_Dialog_MinWidth,setListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    month = month + 1;
                    String date = day+"/"+month+"/"+year;
                    Log.d("debughaha2","masuk2");
                    binding.btnTglLahir.setText(date);
                }
            }, year, month, day);
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        }

        else if(view.getId() == binding.btnPilihDokter.getId()){

        }

        else if(view.getId() == binding.btnWaktuPertemuan.getId()){
             timeSetListener = new TimePickerDialog.OnTimeSetListener(){
                 @Override
                 public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                     hour = selectedHour;
                     minute = selectedMinute;
                     binding.btnWaktuPertemuan.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minute));
                 }
             };
             TimePickerDialog timePickerDialog = new TimePickerDialog(this.getContext(),timeSetListener,hour,minute,true);
             timePickerDialog.show();
        }

        else if(view.getId() == binding.btnSubmit.getId()){
            Bundle result = new Bundle();
            result.putString("page","pertemuanFragment");
            //result harus add input pasien
            this.getParentFragmentManager().setFragmentResult("changePage",result);
        }
    }

    @Override
    public void updateListPertemuan(ArrayList<Pertemuan> pertemuans) {
        
    }

    @Override
    public void resetAddFormPertemuan() {

    }

}
