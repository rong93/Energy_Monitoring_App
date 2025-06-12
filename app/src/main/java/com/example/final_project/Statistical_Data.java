package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Statistical_Data extends AppCompatActivity {

    private EditText year;
    private EditText month;

    private TextView work_num;
    private TextView rent_total_num;

    private EditText rent_num;

    DataBaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistical_data);

        year = findViewById(R.id.year);
        month = findViewById(R.id.month);
        work_num = (TextView)findViewById(R.id.work_num);
        rent_total_num =(TextView)findViewById(R.id.rent_total_num);
        rent_num = (EditText)findViewById(R.id.rent_num);

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
        //date.setText(now[2]);
        rent_num.setText("5");

        year.addTextChangedListener(yearTextWatcher);
        month.addTextChangedListener(monthTextWatcher);
        rent_num.addTextChangedListener(rent_numTextWatcher);

        myDB = new DataBaseHelper(Statistical_Data.this);

        work_calculation_and_set();
        rent_calculation_and_set();

    }
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


            work_calculation_and_set();
            rent_calculation_and_set();
            //只顯示對應日期的資料庫
//            StoreDataInArray(false);
//            family_use_adapter.notifyDataSetChanged();
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


            work_calculation_and_set();
            rent_calculation_and_set();
            //只顯示對應日期的資料庫
//            StoreDataInArray(false);
//            family_use_adapter.notifyDataSetChanged();


        }
        public void afterTextChanged(Editable s) {}
        public void beforeTextChanged(CharSequence s,
                                      int start, int count, int after){}
    };
    private TextWatcher rent_numTextWatcher = new TextWatcher() {
        //THE INPUT ELEMENT IS ATTACHED TO AN EDITABLE,
        //THEREFORE THESE METHODS ARE CALLED WHEN THE TEXT IS CHANGED

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.v("!!!","rent_num changing");
            int digits= 1;
            if(s==""){
                return;
            }
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
            rent_num.removeTextChangedListener(this);
            rent_num.setText(content);
            rent_num.setSelection(content.length());
            rent_num.addTextChangedListener(this);
            if (content.endsWith(".")) {
                content = content.substring(0, content.indexOf("."));
            }


            work_calculation_and_set();
            rent_calculation_and_set();
            //只顯示對應日期的資料庫
//            StoreDataInArray(false);
//            family_use_adapter.notifyDataSetChanged();


        }
        public void afterTextChanged(Editable s) {}
        public void beforeTextChanged(CharSequence s,
                                      int start, int count, int after){}
    };

    public void work_calculation_and_set(){

        Double total_degree = 0.0;
        Double temp_total_degree = 0.0;
        Double total_dollar = 0.0;
        Cursor cursor = myDB.readAlldata();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()) {
                //只顯示對應日期的資料庫
                if(year.getText().toString().equals( String.valueOf(cursor.getInt(0)))
                        &&month.getText().toString().equals( String.valueOf(cursor.getInt(1)))){

                    total_degree += cursor.getDouble(4) *cursor.getDouble(5);
                }
            }
            total_degree = total_degree/1000; //瓦變度
            Log.v("!!!","total_degree: "+total_degree.toString());
            temp_total_degree = total_degree;

            if(month.getText().toString().equals("6")||month.getText().toString().equals("7")
                    ||month.getText().toString().equals("8")||month.getText().toString().equals("9")){
                if(total_degree<=120.0){
                    Log.v("!!!","1");
                    total_dollar = 1.63*temp_total_degree;
                }
                if(total_degree>=120.1 && total_degree<=330.0){
                    Log.v("!!!","2");
                    total_dollar = 1.63*120 +
                                    2.38*(temp_total_degree-120.0);
                }
                if(total_degree>=330.1 &&total_degree<=500.0){
                    Log.v("!!!","3");
                    total_dollar = 1.63*120 +
                                    2.38*209.1 +
                                    3.52*(temp_total_degree-330.1);
                }
                if(total_degree>=500.1 &&total_degree<=700.0){
                    Log.v("!!!","4");
                    total_dollar = 1.63*120 +
                                    2.38*209.1 +
                                    3.52*169.9+
                                    4.8*(temp_total_degree-500.1);
                }
                if(total_degree>=700.1 &&total_degree<=1000.0){
                    Log.v("!!!","5");
                    total_dollar = 1.63*120 +
                                2.38*209.1 +
                                3.52*169.9+
                                4.8*199.9+
                                5.66*(temp_total_degree-700.1);
                }
                if(1000.1<=total_degree){
                    Log.v("!!!","6");
                    total_dollar = 1.63*120 +
                                2.38*209.1 +
                                3.52*169.9+
                                4.8*199.9+
                                5.66*299.9+
                                6.41*(temp_total_degree-1000.1);

                }
                Log.v("!!!","total_dollar: "+total_dollar.toString());
                total_dollar = Math.round(total_dollar * 100.0) / 100.0;
            }
            else{
                if(total_degree<=120.0){
                    Log.v("!!!","1");
                    total_dollar = 1.63*temp_total_degree;
                }
                if(total_degree>=120.1 && total_degree<=330.0){
                    Log.v("!!!","2");
                    total_dollar = 1.63*120 +
                            2.1*(temp_total_degree-120.0);
                }
                if(total_degree>=330.1 &&total_degree<=500.0){
                    Log.v("!!!","3");
                    total_dollar = 1.63*120 +
                            2.1*209.1 +
                            2.89 *(temp_total_degree-330.1);
                }
                if(total_degree>=500.1 &&total_degree<=700.0){
                    Log.v("!!!","4");
                    total_dollar = 1.63*120 +
                            2.1*209.1 +
                            2.89 *169.9+
                            3.94 *(temp_total_degree-500.1);
                }
                if(total_degree>=700.1 &&total_degree<=1000.0){
                    Log.v("!!!","5");
                    total_dollar = 1.63*120 +
                            2.1*209.1 +
                            2.89 *169.9+
                            3.94 *199.9+
                            4.6 *(temp_total_degree-700.1);
                }
                if(1000.1<=total_degree){
                    Log.v("!!!","6");
                    total_dollar = 1.63*120 +
                            2.1*209.1 +
                            2.89 *169.9+
                            3.94 *199.9+
                            4.6 *299.9+
                            5.03 *(temp_total_degree-1000.1);

                }
                Log.v("!!!","total_dollar: "+total_dollar.toString());
                total_dollar = Math.round(total_dollar * 100.0) / 100.0;
            }
        }
        work_num.setText(total_dollar.toString());
    }

    public void rent_calculation_and_set(){
        Double total_degree = 0.0;
        Double temp_total_degree = 0.0;
        Double total_dollar = 0.0;
        Cursor cursor = myDB.readAlldata();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()) {
                //只顯示對應日期的資料庫
                if(year.getText().toString().equals( String.valueOf(cursor.getInt(0)))
                        &&month.getText().toString().equals( String.valueOf(cursor.getInt(1)))){

                    total_degree += cursor.getDouble(4) *cursor.getDouble(5);
                }
            }

            total_degree = total_degree/1000; //瓦變度
            Log.v("!!!","total_degree: "+total_degree.toString());
            if( rent_num.getText().toString().equals("")){
                total_dollar = 0.0;
            }
            else {
                total_dollar = total_degree * Double.valueOf(rent_num.getText().toString());
                total_dollar = Math.round(total_dollar * 100.0) / 100.0;
            }

        }
        rent_total_num.setText(total_dollar.toString());
    }


    public void back_to_main(View view){
        Intent intent = new Intent(Statistical_Data.this,MainActivity.class);
        //intent.putExtra("energy_data",data);
        startActivity(intent);

        overridePendingTransition(R.anim.buttom_in,R.anim.above_out);

        finish();
    }
}