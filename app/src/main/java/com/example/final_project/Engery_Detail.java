package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class Engery_Detail extends AppCompatActivity {

    private ArrayList<String> data;

    private RecyclerView recyclerView;
    private Spinner spinner;

    ArrayList<String> info1,info2,info3,info4,info5;
    CustomAdapter customAdapter;

    private  ArrayList<String> Energy0;
    private  ArrayList<String> Energy1;
    private  ArrayList<String> Energy2;
    private  ArrayList<String> Energy3;
    private  ArrayList<String> Energy4;
    private  ArrayList<String> Energy5;
    private  ArrayList<String> Energy6;
    private  ArrayList<String> Energy7;
    private  ArrayList<String> Energy8;
    private  ArrayList<String> Energy9;
    private  ArrayList<String> Energy10;
    private  ArrayList<String> Energy11;
    private  ArrayList<String> Energy12;
    private  ArrayList<String> Energy13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engery_detail);

        Log.v("!!!","進到Energy_Detail");

        Intent intent = getIntent();

        data =  (ArrayList<String>)intent.getSerializableExtra("energy_data");
        recyclerView = findViewById(R.id.list);

        Energy0 = new ArrayList<String>();
        Energy1 = new ArrayList<String>();
        Energy2 = new ArrayList<String>();
        Energy3 = new ArrayList<String>();
        Energy4 = new ArrayList<String>();
        Energy5 = new ArrayList<String>();
        Energy6 = new ArrayList<String>();
        Energy7 = new ArrayList<String>();
        Energy8 = new ArrayList<String>();
        Energy9 = new ArrayList<String>();
        Energy10 = new ArrayList<String>();
        Energy11 = new ArrayList<String>();
        Energy12 = new ArrayList<String>();
        Energy13 = new ArrayList<String>();

        info1 = new ArrayList<String>();
        info2 = new ArrayList<String>();
        info3 = new ArrayList<String>();
        info4 = new ArrayList<String>();
        info5 = new ArrayList<String>();

        customAdapter = new CustomAdapter(Engery_Detail.this,info1,info2,info3,info4,info5);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Engery_Detail.this));

//        String a ="機組名稱";
//        String b = "裝置容量";
//        String c = "淨發電量";
//        String d = "佔據比例";
//        String e = "備註";

        for(int k=0;k<data.size();k+=6) {
            if(data.get(k+0).equals("\"核能\"")){
                for(int z=1;z<6;z++) {
                    Energy0.add(data.get(k+z).replace("\"",""));
                }
            }
            else if(data.get(k+0).equals("\"燃煤\"")){
                for(int z=1;z<6;z++) {
                    Energy1.add(data.get(k+z).replace("\"",""));
                }
            }
            else if(data.get(k+0).equals("\"汽電共生\"")){
                for(int z=1;z<6;z++) {
                    Energy2.add(data.get(k+z).replace("\"",""));
                }
            }
            else if(data.get(k+0).equals("\"民營電廠-燃煤\"")){
                for(int z=1;z<6;z++) {
                    Energy3.add(data.get(k+z).replace("\"",""));
                }
            }
            else if(data.get(k+0).equals("\"燃氣\"")){
                for(int z=1;z<6;z++) {
                    Energy4.add(data.get(k+z).replace("\"",""));
                }
            }
            else if(data.get(k+0).equals("\"民營電廠-燃氣\"")){
                for(int z=1;z<6;z++) {
                    Energy5.add(data.get(k+z).replace("\"",""));
                }
            }
            else if(data.get(k+0).equals("\"燃油\"")){
                for(int z=1;z<6;z++) {
                    Energy6.add(data.get(k+z).replace("\"",""));
                }
            }
            else if(data.get(k+0).equals("\"輕油\"")){
                for(int z=1;z<6;z++) {
                    Energy7.add(data.get(k+z).replace("\"",""));
                }
            }
            else if(data.get(k+0).equals("\"水力\"")){
                for(int z=1;z<6;z++) {
                    Energy8.add(data.get(k+z).replace("\"",""));
                }
            }
            else if(data.get(k+0).equals("\"風力\"")){
                for(int z=1;z<6;z++) {
                    Energy9.add(data.get(k+z).replace("\"",""));
                }
            }
            else if(data.get(k+0).equals("\"太陽能\"")){
                for(int z=1;z<6;z++) {
                    Energy10.add(data.get(k+z).replace("\"",""));
                }
            }
            else if(data.get(k+0).equals("\"抽蓄發電\"")){
                for(int z=1;z<6;z++) {
                    Energy11.add(data.get(k+z).replace("\"",""));
                }
            }
            else if(data.get(k+0).equals("\"抽蓄負載\"")){
                for(int z=1;z<6;z++) {
                    Energy12.add(data.get(k+z).replace("\"",""));
                }
            }
            else if(data.get(k+0).equals("\"其它再生能源(Other Renewable Energy)<\\/b>\"")){
                for(int z=1;z<6;z++) {
                    Energy13.add(data.get(k+z).replace("\"",""));
                }
            }
        }



        spinner = findViewById(R.id.energy);
        String[] Spinner={"核能","燃煤","汽電共生","民營電廠-燃煤",
                "燃氣","民營電廠-燃氣","燃油","輕油","水力","風力",
                "太陽能","抽蓄發電","抽蓄負載","再生能源"};
        spinner=(android.widget.Spinner)findViewById(R.id.energy);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>( this, android.R.layout.select_dialog_item, Spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v("!!!","選擇 "+Spinner[i]);
                clear_info();

                if(i==0){
                    for(int w=0;w<Energy0.size();w+=5){
                        info1.add(Energy0.get(w));
                        info2.add(Energy0.get(w+1));
                        info3.add(Energy0.get(w+2));
                        info4.add(Energy0.get(w+3));
                        info5.add(Energy0.get(w+4));
                    }
                }
                else if(i==1){
                    for(int w=0;w<Energy1.size();w+=5){
                        info1.add(Energy1.get(w));
                        info2.add(Energy1.get(w+1));
                        info3.add(Energy1.get(w+2));
                        info4.add(Energy1.get(w+3));
                        info5.add(Energy1.get(w+4));
                    }

                }
                else if(i==2){
                    for(int w=0;w<Energy2.size();w+=5){
                        info1.add(Energy2.get(w));
                        info2.add(Energy2.get(w+1));
                        info3.add(Energy2.get(w+2));
                        info4.add(Energy2.get(w+3));
                        info5.add(Energy2.get(w+4));
                    }

                }
                else if(i==3){
                    for(int w=0;w<Energy3.size();w+=5){
                        info1.add(Energy3.get(w));
                        info2.add(Energy3.get(w+1));
                        info3.add(Energy3.get(w+2));
                        info4.add(Energy3.get(w+3));
                        info5.add(Energy3.get(w+4));
                    }

                }
                else if(i==4){
                    for(int w=0;w<Energy4.size();w+=5){
                        info1.add(Energy4.get(w));
                        info2.add(Energy4.get(w+1));
                        info3.add(Energy4.get(w+2));
                        info4.add(Energy4.get(w+3));
                        info5.add(Energy4.get(w+4));
                    }

                }
                else if(i==5){
                    for(int w=0;w<Energy5.size();w+=5){
                        info1.add(Energy5.get(w));
                        info2.add(Energy5.get(w+1));
                        info3.add(Energy5.get(w+2));
                        info4.add(Energy5.get(w+3));
                        info5.add(Energy5.get(w+4));
                    }

                }
                else if(i==6){
                    for(int w=0;w<Energy6.size();w+=5){
                        info1.add(Energy6.get(w));
                        info2.add(Energy6.get(w+1));
                        info3.add(Energy6.get(w+2));
                        info4.add(Energy6.get(w+3));
                        info5.add(Energy6.get(w+4));
                    }

                }
                else if(i==7){
                    for(int w=0;w<Energy7.size();w+=5){
                        info1.add(Energy7.get(w));
                        info2.add(Energy7.get(w+1));
                        info3.add(Energy7.get(w+2));
                        info4.add(Energy7.get(w+3));
                        info5.add(Energy7.get(w+4));
                    }

                }
                else if(i==8){
                    for(int w=0;w<Energy8.size();w+=5){
                        info1.add(Energy8.get(w));
                        info2.add(Energy8.get(w+1));
                        info3.add(Energy8.get(w+2));
                        info4.add(Energy8.get(w+3));
                        info5.add(Energy8.get(w+4));
                    }

                }
                else if(i==9){
                    for(int w=0;w<Energy9.size();w+=5){
                        info1.add(Energy9.get(w));
                        info2.add(Energy9.get(w+1));
                        info3.add(Energy9.get(w+2));
                        info4.add(Energy9.get(w+3));
                        info5.add(Energy9.get(w+4));
                    }

                }
                else if(i==10){
                    for(int w=0;w<Energy10.size();w+=5){
                        info1.add(Energy10.get(w));
                        info2.add(Energy10.get(w+1));
                        info3.add(Energy10.get(w+2));
                        info4.add(Energy10.get(w+3));
                        info5.add(Energy10.get(w+4));
                    }

                }
                else if(i==11){
                    for(int w=0;w<Energy11.size();w+=5){
                        info1.add(Energy11.get(w));
                        info2.add(Energy11.get(w+1));
                        info3.add(Energy11.get(w+2));
                        info4.add(Energy11.get(w+3));
                        info5.add(Energy11.get(w+4));
                    }

                }
                else if(i==12){
                    for(int w=0;w<Energy12.size();w+=5){
                        info1.add(Energy12.get(w));
                        info2.add(Energy12.get(w+1));
                        info3.add(Energy12.get(w+2));
                        info4.add(Energy12.get(w+3));
                        info5.add(Energy12.get(w+4));
                    }

                }
                else if(i==13){
                    for(int w=0;w<Energy13.size();w+=5){
                        info1.add(Energy13.get(w));
                        info2.add(Energy13.get(w+1));
                        info3.add(Energy13.get(w+2));
                        info4.add(Energy13.get(w+3));
                        info5.add(Energy13.get(w+4));
                    }

                }
                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void clear_info(){
        info1.clear();
        info2.clear();
        info3.clear();
        info4.clear();
        info5.clear();
    }

    public void back_to_map(View view){
        Intent intent = new Intent(Engery_Detail.this,MapsActivity.class);
        intent.putExtra("energy_data",data);
        startActivity(intent);

        overridePendingTransition(R.anim.right_in,R.anim.left_out);
        //


        finish();
    }

    public void back_to_main(View view){
        Intent intent = new Intent(Engery_Detail.this,MainActivity.class);
        intent.putExtra("energy_data",data);
        startActivity(intent);

        overridePendingTransition(R.anim.buttom_in,R.anim.above_out);

        finish();
    }
}