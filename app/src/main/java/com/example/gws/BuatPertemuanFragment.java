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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import com.example.gws.databinding.FragmentBuatPertemuanBinding;
import com.example.gws.databinding.FragmentListDokterDialogBinding;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.zip.Inflater;

public class BuatPertemuanFragment extends Fragment implements View.OnClickListener{
    private FragmentBuatPertemuanBinding binding;
    private FragmentListDokterDialogBinding binding2;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private DatePickerDialog.OnDateSetListener setListener;
    TimePickerDialog.OnTimeSetListener timeSetListener;
    private MainPresenter presenter;
    protected ListDokterDialogFragment listDokterDialogFragment;
    private int minute;
    private int hour;
    private String date;
    private String time;
    private Date convertedDate;
    private Date convertedTime;

    private DateFormat formatterTime;
    private DateFormat formatterDate;
//    private Spinner spinner;

    public BuatPertemuanFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentBuatPertemuanBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        binding.btnTglPertemuan.setOnClickListener(this);
        binding.btnPilihDokter.setOnClickListener(this);
        binding.btnWaktuPertemuan.setOnClickListener(this);
        binding.btnSubmit.setOnClickListener(this);

        this.formatterTime = new SimpleDateFormat("HH:mm");
        this.formatterDate = new SimpleDateFormat("dd/MM/yyyy");

//        spinner = binding.spinnerDokter;
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BuatPertemuanFragment.this.getActivity(),
//                R.array.doctors_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);

        return view;
    }

    public static BuatPertemuanFragment newInstance(String title, MainPresenter presenter, ListDokterDialogFragment listDokterDialogFragment){
        BuatPertemuanFragment fragment = new BuatPertemuanFragment();
        fragment.presenter = presenter;
        fragment.listDokterDialogFragment = listDokterDialogFragment;
        Bundle args = new Bundle();
        args.putString("title",title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == binding.btnTglPertemuan.getId()) {
            Calendar calendar = Calendar.getInstance();
            final int year = calendar.get(Calendar.YEAR);
            final int month = calendar.get(Calendar.MONTH);
            final int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(BuatPertemuanFragment.this.getActivity(),android.R.style.
                    Theme_Holo_Light_Dialog_MinWidth,setListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month = month + 1;
                    date = day+"/"+month+"/"+year;
//                    try{
//                        convertedDate = formatterDate.parse(date);
//                    }catch(Exception ex){
//                        Log.d("debugTDate","error ngab");
//                    }
                    Log.d("debughaha2","masuk2");
                    binding.btnTglPertemuan.setText(date);
                }
            }, year, month, day);
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        }

        else if(view == binding.btnPilihDokter){
            FragmentManager fm = getParentFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            this.listDokterDialogFragment.show(fm, "listDokterDialogFragment");

            this.getParentFragmentManager().setFragmentResultListener("pilihDokter", this, new FragmentResultListener() {
                @Override
                public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                    String namaD = result.getString("namaDokter");
                    String spesialis = result.getString("spesialisDokter");
                    binding.tvPilihDokter.setText(namaD);
                    binding.tvPilihSpesialis.setText(spesialis);
                }
            });
            if(this.presenter.getSizeDokter()<1){
                binding.btnSubmit.setClickable(false);
            }
        }

        else if(view.getId() == binding.btnWaktuPertemuan.getId()){
             timeSetListener = new TimePickerDialog.OnTimeSetListener(){
                 @Override
                 public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                     hour = selectedHour;
                     minute = selectedMinute;
                     time = selectedHour+":"+selectedMinute;
//                     try{
//                         convertedTime = formatterTime.parse(time);
//                     }catch(Exception ex){
//                         Log.d("debugTime","error ngab");
//                     }
                     binding.btnWaktuPertemuan.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minute));
                 }
             };
             TimePickerDialog timePickerDialog = new TimePickerDialog(this.getContext(),timeSetListener,hour,minute,true);
             timePickerDialog.show();
        }

        else if(view.getId() == binding.btnSubmit.getId()){
                SQLiteManagerPertemuan sqLiteManagerPertemuan = new SQLiteManagerPertemuan(getContext());
                Bundle result = new Bundle();
                result.putString("page", "pertemuanFragment");
                String nama = binding.etInputNama.getText().toString();
                RadioGroup rg = binding.rgGender;
                int selectedId = rg.getCheckedRadioButtonId();
                String gender = "";
                if (selectedId == binding.Pria.getId()) {
                    gender += "Pria";
                } else if (selectedId == binding.Wanita.getId()) {
                    gender += "Wanita";
                }
                String keluhan = binding.etKeluhanPasien.getText().toString();
                String namaDokter = binding.tvPilihDokter.getText().toString();
                String spesialis = binding.tvPilihSpesialis.getText().toString();

                if(!nama.equals("") && !namaDokter.equals("") && !spesialis.equals("") && !date.equals("") && !time.equals("")){
                    int max = 999;
                    int min = 1;
                    int range = max - min + 1;
                    int rand = (int) (Math.random()*range)+min;

                    int id = presenter.getSizePertemuan()+rand;

                    Log.d("time ",time);

                    sqLiteManagerPertemuan.addPertemuanDatabase(date, time, nama, namaDokter, keluhan, spesialis, gender, "N");
                    Pertemuan pertemuanTemp = new Pertemuan(id, date, time, nama, namaDokter, keluhan, spesialis, gender, false);
                    presenter.addListPertemuan(pertemuanTemp);
                    //result harus add input pasien
                    this.getParentFragmentManager().setFragmentResult("changePage", result);

                    binding.etInputNama.setText("");
                    binding.rgGender.clearCheck();
                    binding.btnTglPertemuan.setText("");
                    binding.btnWaktuPertemuan.setText("");
                    binding.etKeluhanPasien.setText("");
                    binding.tvPilihDokter.setText("");
                    binding.tvPilihSpesialis.setText("");
                }
                else if(nama.trim().equals("")) {
                    binding.etInputNama.setError("Nama pasien tidak boleh kosong");
                }
                else if(namaDokter.trim().equals("")&&spesialis.trim().equals("")){
                    binding.tvPilihDokter.setError("Dokter tidak boleh kosong");
                    binding.tvPilihSpesialis.setError("Spesialis tidak boleh kosong");
                }
        }
    }
}
