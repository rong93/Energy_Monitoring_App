package com.example.final_project;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Get_data_from_internet {

    private ArrayList<String> energy_infomation;

    public Get_data_from_internet(){
        Log.v("!!!","物件定義");
        energy_infomation = null;
    };

    public ArrayList<String> get_Data_from_Internet(){

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

//                    int count = 0;
//                    for(int i=0;i<sss_arr.length;i++) {
//                        if(i%6==0) {
//                            count ++;
//                            Log.v("!!!", "" + "能源 " + sss_arr[i] + " " + sss_arr[i+1]+ " " + sss_arr[i+2]
//                                    + " " + sss_arr[i+3]+ " " + sss_arr[i+4]+ " " + sss_arr[i+5]);
//                        }
//                        //Log.v("!!!", "" + "INFO " + sss_arr[i]);
//                    }
//                    Log.v("!!!", "" + "INFO(總量) " + count);

                    //energy_infomation = sss_arr;
                    for(int c=0;c<sss_arr.length;c++){
                        energy_infomation.add(sss_arr[c]);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return energy_infomation;
    }

    public int get_informatoin_length(){
        return energy_infomation.size();
    }

    public String get_informatoin(int index){
        return energy_infomation.get(index);
    }
}
