package com.example.final_project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Family_use_Adapter extends RecyclerView.Adapter<Family_use_Adapter.MyViewHolder>{
    private Context context;
    private ArrayList db_year,db_month, db_date,db_machine,db_watts,db_using_time;
    private boolean is_first_click_view = true;
    private MyViewHolder last_holder;
    private int now_position;
    private String click_name;
    private String click_year;
    private String click_month;
    private String click_date;



    Family_use_Adapter(Context context,
                  ArrayList db_year,
                  ArrayList db_month,
                  ArrayList db_date,
                  ArrayList db_machine,
                  ArrayList db_watts,
                  ArrayList db_using_time){
        this.context = context;
        this.db_year = db_year;
        this.db_month = db_month;
        this.db_date = db_date;
        this.db_machine = db_machine;
        this.db_watts = db_watts;
        this.db_using_time = db_using_time;
        Log.v("!!!","!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView db_year_txt,db_month_txt,db_date_txt,
                db_machine_txt,db_watts_txt,db_using_time_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            db_year_txt = itemView.findViewById(R.id.db_year_txt);
            //db_month_txt = itemView.findViewById(R.id.db_month_txt);
            //db_date_txt = itemView.findViewById(R.id.db_date_txt);
            db_machine_txt = itemView.findViewById(R.id.db_machine_txt);
            db_watts_txt = itemView.findViewById(R.id.db_watts_txt);
            db_using_time_txt = itemView.findViewById(R.id.db_using_time_txt);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_family_using,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String year_month_date = String.valueOf(db_year.get(position))+"-\n"+String.valueOf(db_month.get(position))+"-"+String.valueOf(db_date.get(position));
        holder.db_year_txt.setText(year_month_date);
//        holder.db_year_txt.setText(String.valueOf(db_year.get(position)) );
//        holder.db_month_txt.setText(String.valueOf(db_month.get(position)) );
//        holder.db_date_txt.setText(String.valueOf(db_date.get(position)) );
        holder.db_machine_txt.setText(String.valueOf(db_machine.get(position)) );
        holder.db_watts_txt.setText(String.valueOf(db_watts.get(position)) );
        holder.db_using_time_txt.setText(String.valueOf(db_using_time.get(position)) );

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //now_position = position;
                click_name = holder.db_machine_txt.getText().toString();
//                Log.v("!!!","click_name: "+click_name);

                //year_month_date為連在一起的 所以要分開
                String[] temp = holder.db_year_txt.getText().toString().split("-");

                click_year = temp[0];
                click_month = temp[1].split("\n")[1];
                click_date = temp[2];

                Log.v("!!!","click_name: "+click_name);
                Log.v("!!!","click_year: "+click_year);
                Log.v("!!!","click_month: "+click_month);
                Log.v("!!!","click_date: "+click_date);

//                if(is_first_click_view == true){
//                    holder.db_name_txt.setBackgroundColor(Color.parseColor("#e8f48c"));
//                    last_holder = holder;
//                    is_first_click_view = false;
//                }
//                else{
//                    last_holder.db_name_txt.setBackgroundColor(Color.parseColor("#c4dded"));
//                    holder.db_name_txt.setBackgroundColor(Color.parseColor("#e8f48c"));
//                    last_holder = holder;
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return db_year.size();
    }

    public String get_click_name(){return click_name; }
    public String get_click_year(){return click_year; }
    public String get_click_month(){return click_month; }
    public String get_click_date(){return click_date; }
}
