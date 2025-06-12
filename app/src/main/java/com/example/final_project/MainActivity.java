package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Get_data_from_internet data;
    ArrayList<String> energy_infomation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        energy_infomation = new ArrayList<String>();

        Log.v("!!!","進入到MainActivity");

        //data = new Get_data_from_internet();

        ////////////////////////
        String url = "https://data.taipower.com.tw/opendata01/apply/file/d006001/001.txt";

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();

        Request request = new Request.Builder().url(url).get().build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.v("!!!","取得網路資料失敗");
            }

            String str;

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.v("!!!", "取得網路資料成功");


                str = response.body().string();
                Log.v("!!!", str);
                System.out.println(str);

                try {
                    JSONObject obj = new JSONObject(str);
                    Log.v("!!!",obj.getString(""));

                    //Log.v("!!!",obj.getString("aaData").getClass().toString());

                    String str = obj.getString("aaData");
                    Log.v("!!!",str);

                    StringBuilder sb = new StringBuilder(str);
                    sb.deleteCharAt(0);
                    sb.deleteCharAt(sb.length()-1);
                    Log.v("!!!","刪除完成 "+sb);

                    //String a = "\"],[\"";
                    String sss = sb.toString().replace("[","").replace("]","");
                    String [] sss_arr = sss.split(",");
                    Log.v("!!!",sss);
                    Log.v("!!!","長度: "+sss_arr.length);


                    //energy_infomation = sss_arr;
                    for(int c=0;c<sss_arr.length;c++){
                        energy_infomation.add(sss_arr[c]);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        ////////////////////////


//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }

    }


    public void go_to_map(View view){
        Intent intent = new Intent(MainActivity.this,MapsActivity.class);
        intent.putExtra("energy_data",energy_infomation);

        startActivity(intent);

        overridePendingTransition(R.anim.above_in,R.anim.buttom_out);
    }

    public void go_to_family_use(View view){
        Intent intent = new Intent(MainActivity.this,family_use.class);
        //intent.putExtra("energy_data",energy_infomation);

        startActivity(intent);

        overridePendingTransition(R.anim.above_in,R.anim.buttom_out);
    }

    public void go_to_statistical(View view){
        Intent intent = new Intent(MainActivity.this,Statistical_Data.class);
        //intent.putExtra("energy_data",energy_infomation);

        startActivity(intent);

        overridePendingTransition(R.anim.above_in,R.anim.buttom_out);
    }
}