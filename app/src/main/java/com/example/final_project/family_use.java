package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class family_use extends AppCompatActivity {

    private EditText year;
    private EditText month;
    private EditText date;
    private EditText machine;
    private EditText watts;
    private EditText using_time;


    private RecyclerView recyclerView;
    DataBaseHelper myDB;
    Family_use_Adapter family_use_adapter;
    ArrayList<String> db_machine;
    ArrayList<Double> db_watts,db_using_time;
    ArrayList<Integer>db_year,db_month,db_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_use);

        recyclerView = findViewById(R.id.family_use_list);

        year = findViewById(R.id.year);
        month = findViewById(R.id.month);
        date = findViewById(R.id.date);
        machine = findViewById(R.id.machine);
        watts = findViewById(R.id.watts);
        using_time = findViewById(R.id.using_time);

        myDB = new DataBaseHelper(family_use.this);
        db_year = new ArrayList<>();
        db_month = new ArrayList<>();
        db_date = new ArrayList<>();
        db_machine = new ArrayList<>();
        db_watts = new ArrayList<>();
        db_using_time = new ArrayList<>();

        StoreDataInArray(true);

        family_use_adapter = new Family_use_Adapter(family_use.this,db_year,
                db_month,db_date,db_machine,db_watts,db_using_time);
        recyclerView.setAdapter(family_use_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(family_use.this));

        year.addTextChangedListener(yearTextWatcher);
        month.addTextChangedListener(monthTextWatcher);
        date.addTextChangedListener(dateTextWatcher);
//        machine.addTextChangedListener(machineTextWatcher);
        watts.addTextChangedListener(wattsTextWatcher);
        using_time.addTextChangedListener(using_timeTextWatcher);

        SimpleDateFormat nowDate = new SimpleDateFormat("yyyy-MM-dd");
        nowDate.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
        String now_Date = nowDate.format(new Date());
        String[] now = now_Date.split("-");
        Log.v("!!!","time: "+nowDate);
        Log.v("!!!","time_year: "+now[0]);
        Log.v("!!!","time_month: "+now[1]);
        Log.v("!!!","time_date: "+now[2]);

        year.setText(now[0]);
        month.setText(now[1]);
        date.setText(now[2]);

    }


    private AdapterView.OnItemSelectedListener spnOnItemSelected
            = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            String sPos=String.valueOf(pos);
            String sInfo=parent.getItemAtPosition(pos).toString();
            //String sInfo=parent.getSelectedItem().toString();
            Log.v("!!!",sPos+ " "+sInfo);

            //tvhello.setText("選項"+sPos+":"+sInfo);


//            if("".equals(height.getText().toString().trim()) || "".equals(weight.getText().toString().trim()) || "".equals(old.getText().toString().trim())){
//                BMR.setText("請完整輸入！");
//                return;
//            }



        }
        public void onNothingSelected(AdapterView<?> parent) {
            //
        }
    };

    private TextWatcher yearTextWatcher = new TextWatcher() {
        //THE INPUT ELEMENT IS ATTACHED TO AN EDITABLE,
        //THEREFORE THESE METHODS ARE CALLED WHEN THE TEXT IS CHANGED

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.v("!!!","year changing");
            int digits= 1;
            String content = s.toString();
            if (content.startsWith(".")) {
                content = "0.";
            }
            if (content.startsWith("0")) {
                if (content.length() > 1 && !content.substring(1, 2).equals(".")) {
                    content = content.substring(1);
                }
            }
            if (content.contains(".")) {
                int pointIndex = content.indexOf(".");
                if (content.length() - 1 - pointIndex > digits) {
                    content = content.substring(0, pointIndex + digits+1);
                }
            }
            year.removeTextChangedListener(this);
            year.setText(content);
            year.setSelection(content.length());
            year.addTextChangedListener(this);
            if (content.endsWith(".")) {
                content = content.substring(0, content.indexOf("."));
            }

            //只顯示對應日期的資料庫
            StoreDataInArray(false);
            family_use_adapter.notifyDataSetChanged();
        }
        public void afterTextChanged(Editable s) {}
        public void beforeTextChanged(CharSequence s,
                                      int start, int count, int after){}
    };
    private TextWatcher monthTextWatcher = new TextWatcher() {
        //THE INPUT ELEMENT IS ATTACHED TO AN EDITABLE,
        //THEREFORE THESE METHODS ARE CALLED WHEN THE TEXT IS CHANGED

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.v("!!!","month changing");
            int digits= 1;
            String content = s.toString();
            if (content.startsWith(".")) {
                content = "0.";
            }
            if (content.startsWith("0")) {
                if (content.length() > 1 && !content.substring(1, 2).equals(".")) {
                    content = content.substring(1);
                }
            }
            if (content.contains(".")) {
                int pointIndex = content.indexOf(".");
                if (content.length() - 1 - pointIndex > digits) {
                    content = content.substring(0, pointIndex + digits+1);
                }
            }
            month.removeTextChangedListener(this);
            month.setText(content);
            month.setSelection(content.length());
            month.addTextChangedListener(this);
            if (content.endsWith(".")) {
                content = content.substring(0, content.indexOf("."));
            }

            //只顯示對應日期的資料庫
            StoreDataInArray(false);
            family_use_adapter.notifyDataSetChanged();


        }
        public void afterTextChanged(Editable s) {}
        public void beforeTextChanged(CharSequence s,
                                      int start, int count, int after){}
    };
    private TextWatcher dateTextWatcher = new TextWatcher() {
        //THE INPUT ELEMENT IS ATTACHED TO AN EDITABLE,
        //THEREFORE THESE METHODS ARE CALLED WHEN THE TEXT IS CHANGED

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.v("!!!","date changing");
            int digits= 1;
            String content = s.toString();
            if (content.startsWith(".")) {
                content = "0.";
            }
            if (content.startsWith("0")) {
                if (content.length() > 1 && !content.substring(1, 2).equals(".")) {
                    content = content.substring(1);
                }
            }
            if (content.contains(".")) {
                int pointIndex = content.indexOf(".");
                if (content.length() - 1 - pointIndex > digits) {
                    content = content.substring(0, pointIndex + digits+1);
                }
            }
            date.removeTextChangedListener(this);
            date.setText(content);
            date.setSelection(content.length());
            date.addTextChangedListener(this);
            if (content.endsWith(".")) {
                content = content.substring(0, content.indexOf("."));
            }

            //只顯示對應日期的資料庫
            StoreDataInArray(false);
            family_use_adapter.notifyDataSetChanged();

        }
        public void afterTextChanged(Editable s) {}
        public void beforeTextChanged(CharSequence s,
                                      int start, int count, int after){}
    };
    private TextWatcher wattsTextWatcher = new TextWatcher() {
        //THE INPUT ELEMENT IS ATTACHED TO AN EDITABLE,
        //THEREFORE THESE METHODS ARE CALLED WHEN THE TEXT IS CHANGED

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.v("!!!","watts changing");
            int digits= 1;
            String content = s.toString();
            if (content.startsWith(".")) {
                content = "0.";
            }
            if (content.startsWith("0")) {
                if (content.length() > 1 && !content.substring(1, 2).equals(".")) {
                    content = content.substring(1);
                }
            }
            if (content.contains(".")) {
                int pointIndex = content.indexOf(".");
                if (content.length() - 1 - pointIndex > digits) {
                    content = content.substring(0, pointIndex + digits+1);
                }
            }
            watts.removeTextChangedListener(this);
            watts.setText(content);
            watts.setSelection(content.length());
            watts.addTextChangedListener(this);
            if (content.endsWith(".")) {
                content = content.substring(0, content.indexOf("."));
            }


        }
        public void afterTextChanged(Editable s) {}
        public void beforeTextChanged(CharSequence s,
                                      int start, int count, int after){}
    };
    private TextWatcher using_timeTextWatcher = new TextWatcher() {
        //THE INPUT ELEMENT IS ATTACHED TO AN EDITABLE,
        //THEREFORE THESE METHODS ARE CALLED WHEN THE TEXT IS CHANGED

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.v("!!!","using_time changing");
            int digits= 1;
            String content = s.toString();
            if (content.startsWith(".")) {
                content = "0.";
            }
            if (content.startsWith("0")) {
                if (content.length() > 1 && !content.substring(1, 2).equals(".")) {
                    content = content.substring(1);
                }
            }
            if (content.contains(".")) {
                int pointIndex = content.indexOf(".");
                if (content.length() - 1 - pointIndex > digits) {
                    content = content.substring(0, pointIndex + digits+1);
                }
            }
            using_time.removeTextChangedListener(this);
            using_time.setText(content);
            using_time.setSelection(content.length());
            using_time.addTextChangedListener(this);
            if (content.endsWith(".")) {
                content = content.substring(0, content.indexOf("."));
            }

        }
        public void afterTextChanged(Editable s) {}
        public void beforeTextChanged(CharSequence s,
                                      int start, int count, int after){}
    };

    public void add_new_data(View view){
        if(year.getText().toString().equals("")||month.getText().toString().equals("")||
                date.getText().toString().equals("")||machine.getText().toString().equals("")||
                watts.getText().toString().equals("")||using_time.getText().toString().equals("")){
            Toast.makeText(this, "輸入不完整!", Toast.LENGTH_SHORT).show();
            return;
        }
        DataBaseHelper myDB = new DataBaseHelper(family_use.this);
        myDB.Save_user_data(Integer.valueOf(String.valueOf(year.getText()).trim())
                ,Integer.valueOf(String.valueOf(month.getText()).trim())
                ,Integer.valueOf(String.valueOf(date.getText()).trim())
                ,String.valueOf(machine.getText()).trim()
                ,Double.valueOf(String.valueOf(watts.getText()).trim())
                ,Double.valueOf(String.valueOf(using_time.getText()).trim())
                ,family_use_adapter
                ,family_use.this);

        StoreDataInArray(false); //參數用來判斷 此筆是新增的 還是 原有的
        family_use_adapter.notifyDataSetChanged(); //即時顯示出來
    }

    public void Delete_data(View view){
        Cursor cursor = myDB.readAlldata();
        cursor.moveToFirst();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
            return;
        }

        DataBaseHelper myDB = new DataBaseHelper(family_use.this);

        myDB.Delete_user_data(family_use_adapter.get_click_year(),family_use_adapter.get_click_month()
                ,family_use_adapter.get_click_date(),family_use_adapter.get_click_name()
                ,family_use_adapter,family_use.this);

        StoreDataInArray(false); //參數用來判斷 此筆是新增的 還是 原有的
        family_use_adapter.notifyDataSetChanged(); //即時顯示出來
    }

//    public void put_data(){
//        height.setText( String.valueOf(shipItem.get_height()) );
//        weight.setText( String.valueOf(shipItem.get_weight()) );
//        old.setText( String.valueOf(shipItem.get_old()) );
//        name.setText(shipItem.get_name());
//
//        if(shipItem.get_sex()==0){
//            sex.setText("男性");
//        }
//        else{
//            sex.setText("女性");
//        }
//        sp.setSelection(shipItem.get_spinner()-1);
//    }

    public void StoreDataInArray(boolean is_first){ //(is_first 可刪除了)參數用來判斷 此筆是新增的 還是 原有的

        Cursor cursor = myDB.readAlldata();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
            db_year.clear();
            db_month.clear();
            db_date.clear();
            db_machine.clear();
            db_watts.clear();
            db_using_time.clear();
        }
        else{

            db_year.clear();
            db_month.clear();
            db_date.clear();
            db_machine.clear();
            db_watts.clear();
            db_using_time.clear();

            while (cursor.moveToNext()) {

                //只顯示對應日期的資料庫
                if(year.getText().toString().equals( String.valueOf(cursor.getInt(0)))
                &&month.getText().toString().equals( String.valueOf(cursor.getInt(1)))
                &&date.getText().toString().equals( String.valueOf(cursor.getInt(2)))){

                    Log.v("!!!","store in array111");
                    db_year.add(cursor.getInt(0));
                    db_month.add(cursor.getInt(1));
                    db_date.add(cursor.getInt(2));
                    db_machine.add(cursor.getString(3));
                    db_watts.add(cursor.getDouble(4));
                    db_using_time.add(cursor.getDouble(5));
                }


            }

//            if(is_first == true) {
//
//                while (cursor.moveToNext()) {
//                    db_name.add(cursor.getString(0));
//                    db_sex.add(cursor.getString(1));
//                    db_height.add(cursor.getDouble(2));
//                    db_weight.add(cursor.getDouble(3));
//                    db_age.add(cursor.getInt(4));
//                    db_sport.add(cursor.getInt(5));
//                }
//            }
//            else{
//                cursor.moveToLast(); //新增的那一項
//                db_name.add(cursor.getString(0));
//                db_sex.add(cursor.getString(1));
//                db_height.add(cursor.getDouble(2));
//                db_weight.add(cursor.getDouble(3));
//                db_age.add(cursor.getInt(4));
//                db_sport.add(cursor.getInt(5));
//            }
        }
    }

    public void back_to_main(View view){
        Intent intent = new Intent(family_use.this,MainActivity.class);
        //intent.putExtra("energy_data",data);
        startActivity(intent);

        overridePendingTransition(R.anim.buttom_in,R.anim.above_out);

        finish();
    }
}