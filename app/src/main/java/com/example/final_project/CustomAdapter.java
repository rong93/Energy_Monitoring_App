package com.example.final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList info1,info2,info3,info4,info5;

    CustomAdapter(Context context,
                  ArrayList info1,
                  ArrayList info2,
                  ArrayList info3,
                  ArrayList info4,
                  ArrayList info5) {
        this.context = context;
        this.info1 = info1;
        this.info2 = info2;
        this.info3 = info3;
        this.info4 = info4;
        this.info5 = info5;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView info1_txt,info2_txt,info3_txt,info4_txt,info5_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            info1_txt = itemView.findViewById(R.id.info1);
            info2_txt = itemView.findViewById(R.id.info2);
            info3_txt = itemView.findViewById(R.id.info3);
            info4_txt = itemView.findViewById(R.id.info4);
            info5_txt = itemView.findViewById(R.id.info5);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.info1_txt.setText(String.valueOf(info1.get(position)) );
        holder.info2_txt.setText(String.valueOf(info2.get(position)) );
        holder.info3_txt.setText(String.valueOf(info3.get(position)) );
        holder.info4_txt.setText(String.valueOf(info4.get(position)) );
        holder.info5_txt.setText(String.valueOf(info5.get(position)) );

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //now_position = position;
//                click_name = holder.db_name_txt.getText().toString();
//                System.out.println(click_name);


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
        return info1.size();
    }
}
