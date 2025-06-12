package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.sql.Time;
import java.util.ArrayList;

public class chart extends AppCompatActivity {

    private ArrayList<String> data;

    private Spinner energy;
    private Spinner year_month;

    private BarChart barChart;
    private BarData barData;
    private BarDataSet barDataSet;
    private ArrayList barEntriesArrayList;

    private int now_spinner_energy, now_spinner_year_month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        Intent intent = getIntent();
        data = (ArrayList<String>)intent.getSerializableExtra("energy_data");

        barChart = findViewById(R.id.BarChart);

        now_spinner_energy = 0;
        now_spinner_year_month = 2;

        String[] Spinner_energy={"水力","風力","太陽能"};
        energy=(Spinner)findViewById(R.id.energy);
        ArrayAdapter<String> adapter_energy=new ArrayAdapter<String>( this, android.R.layout.select_dialog_item, Spinner_energy);
        energy.setAdapter(adapter_energy);
        energy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                now_spinner_energy = i;

                barEntriesArrayList = new ArrayList();
                barEntriesArrayList.clear();
                boolean have_data_or_not = getBarEntries(now_spinner_energy,now_spinner_year_month);
                if(have_data_or_not == false){
                    barChart.setNoDataText("沒有數據");
                    barChart.setNoDataTextColor(Color.BLUE);
                    barChart.invalidate();
                    return;
                }

                barDataSet = new BarDataSet(barEntriesArrayList, "能源");
                barDataSet.setDrawValues(false);


                barData = new BarData(barDataSet);

                barChart.setData(barData);

                barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

                barDataSet.setValueTextColor(Color.BLACK);

                barDataSet.setValueTextSize(16f);
                barChart.getDescription().setEnabled(false);

                barChart.animateY(1000, Easing.Linear);

                barChart.invalidate();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String[] Spinner_year_month={"107","108","109","110","111"};
        year_month=(Spinner)findViewById(R.id.year_month);
        ArrayAdapter<String> adapter_year_month=new ArrayAdapter<String>( this, android.R.layout.select_dialog_item, Spinner_year_month);
        year_month.setAdapter(adapter_year_month);
        year_month.setSelection(2);
        year_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                now_spinner_year_month = i;

                barEntriesArrayList = new ArrayList();
                barEntriesArrayList.clear();
                boolean have_data_or_not = getBarEntries(now_spinner_energy,now_spinner_year_month);
                if(have_data_or_not == false){
                    barChart.setNoDataText("沒有數據");
                    barChart.setNoDataTextColor(Color.BLUE);
                    Log.v("!!!","NODATA");
                    return;
                }

                barDataSet = new BarDataSet(barEntriesArrayList, "能源");
                barDataSet.setDrawValues(false);


                barData = new BarData(barDataSet);

                barChart.setData(barData);

                barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

                barDataSet.setValueTextColor(Color.BLACK);

                barDataSet.setValueTextSize(16f);
                barChart.getDescription().setEnabled(false);

                barChart.animateY(1000, Easing.Linear);

                barChart.invalidate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private boolean getBarEntries(int sp_energy, int sp_year_month) {
        if(sp_energy== 0 && sp_year_month == 0 ) { //水力 107
            return false;
        }
        else if(sp_energy== 0 && sp_year_month == 1 ){ //水力 108
            return false;
        }
        else if(sp_energy== 0 && sp_year_month == 2 ){ //水力 109
            barEntriesArrayList.add(new BarEntry(5f, 251424154));
            barEntriesArrayList.add(new BarEntry(6f, 289961076));
            barEntriesArrayList.add(new BarEntry(7f, 203507832));
            barEntriesArrayList.add(new BarEntry(8f, 157864658));
            barEntriesArrayList.add(new BarEntry(9f, 175369600));
            barEntriesArrayList.add(new BarEntry(10f, 252078417));
            barEntriesArrayList.add(new BarEntry(11f, 186613179));
            barEntriesArrayList.add(new BarEntry(12f, 179602976));
        }
        else if(sp_energy== 0 && sp_year_month == 3 ){ //水力 110
            barEntriesArrayList.add(new BarEntry(1f, 181942780));
            barEntriesArrayList.add(new BarEntry(2f, 151230193));
            barEntriesArrayList.add(new BarEntry(3f, 136049594));
            barEntriesArrayList.add(new BarEntry(4f, 106743380));
            barEntriesArrayList.add(new BarEntry(5f, 115484329));
            barEntriesArrayList.add(new BarEntry(6f, 248979801));
            barEntriesArrayList.add(new BarEntry(7f, 233338865));
            barEntriesArrayList.add(new BarEntry(8f, 430235147));
            barEntriesArrayList.add(new BarEntry(9f, 279477518));
            barEntriesArrayList.add(new BarEntry(10f, 410704875));
            barEntriesArrayList.add(new BarEntry(11f, 232195171));
            barEntriesArrayList.add(new BarEntry(12f, 186822962));
        }
        else if(sp_energy== 0 && sp_year_month == 4 ){ //水力 111
            barEntriesArrayList.add(new BarEntry(1f, 227980695));
            barEntriesArrayList.add(new BarEntry(2f, 484551653));
            barEntriesArrayList.add(new BarEntry(3f, 500588235));
            barEntriesArrayList.add(new BarEntry(4f, 501380877));
            barEntriesArrayList.add(new BarEntry(5f, 694438289));
            barEntriesArrayList.add(new BarEntry(6f, 707220789));
            barEntriesArrayList.add(new BarEntry(7f, 347417839));
            barEntriesArrayList.add(new BarEntry(8f, 179560905));
            barEntriesArrayList.add(new BarEntry(9f, 349521933));
            barEntriesArrayList.add(new BarEntry(10f, 355645556));
            barEntriesArrayList.add(new BarEntry(11f, 273194302));
        }
        else if(sp_energy== 1 && sp_year_month ==0 ) { //風力 107
            barEntriesArrayList.add(new BarEntry(1f, 107658125));
            barEntriesArrayList.add(new BarEntry(2f, 90499629));
            barEntriesArrayList.add(new BarEntry(3f, 58492005));
            barEntriesArrayList.add(new BarEntry(4f, 31884508));
            barEntriesArrayList.add(new BarEntry(5f, 26896944));
            barEntriesArrayList.add(new BarEntry(6f, 41393979));
            barEntriesArrayList.add(new BarEntry(7f, 27678941));
            barEntriesArrayList.add(new BarEntry(8f, 18038590));
            barEntriesArrayList.add(new BarEntry(9f, 53861415));
            barEntriesArrayList.add(new BarEntry(10f, 96715884));
            barEntriesArrayList.add(new BarEntry(11f, 64870533));
            barEntriesArrayList.add(new BarEntry(12f, 113760929));
        }
        else if(sp_energy== 1 && sp_year_month == 1 ){ //風力 108
            barEntriesArrayList.add(new BarEntry(1f, 110131297));
            barEntriesArrayList.add(new BarEntry(2f, 63969124));
            barEntriesArrayList.add(new BarEntry(3f, 56843585));
            barEntriesArrayList.add(new BarEntry(4f, 39351586));
            barEntriesArrayList.add(new BarEntry(5f, 38713227));
            barEntriesArrayList.add(new BarEntry(6f, 23839655));
            barEntriesArrayList.add(new BarEntry(7f, 34096207));
            barEntriesArrayList.add(new BarEntry(8f, 36710042));
            barEntriesArrayList.add(new BarEntry(9f, 109155623));
            barEntriesArrayList.add(new BarEntry(10f, 84547494));
            barEntriesArrayList.add(new BarEntry(11f, 109155623));
            barEntriesArrayList.add(new BarEntry(12f, 92795276));
        }
        else if(sp_energy== 1 && sp_year_month == 2 ){ //風力 109
            barEntriesArrayList.add(new BarEntry(1f, 79902762));
            barEntriesArrayList.add(new BarEntry(2f, 70714627));
            barEntriesArrayList.add(new BarEntry(3f, 60289420));
            barEntriesArrayList.add(new BarEntry(4f, 64345079));
            barEntriesArrayList.add(new BarEntry(5f, 23824983));
            barEntriesArrayList.add(new BarEntry(6f, 33028787));
            barEntriesArrayList.add(new BarEntry(7f, 24259658));
            barEntriesArrayList.add(new BarEntry(8f, 22685929));
            barEntriesArrayList.add(new BarEntry(9f, 43348224));
            barEntriesArrayList.add(new BarEntry(10f, 126669482));
            barEntriesArrayList.add(new BarEntry(11f, 100791303));
            barEntriesArrayList.add(new BarEntry(12f, 125362087));
        }
        else if(sp_energy== 1 && sp_year_month == 3 ){ //風力 110
            barEntriesArrayList.add(new BarEntry(1f, 94611010));
            barEntriesArrayList.add(new BarEntry(2f, 62447923));
            barEntriesArrayList.add(new BarEntry(3f, 63159731));
            barEntriesArrayList.add(new BarEntry(4f, 61966902));
            barEntriesArrayList.add(new BarEntry(5f, 25288056));
            barEntriesArrayList.add(new BarEntry(6f, 31122648));
            barEntriesArrayList.add(new BarEntry(7f, 33700082));
            barEntriesArrayList.add(new BarEntry(8f, 18490430));
            barEntriesArrayList.add(new BarEntry(9f, 17443401));
            barEntriesArrayList.add(new BarEntry(10f, 86409669));
            barEntriesArrayList.add(new BarEntry(11f, 113579288));
            barEntriesArrayList.add(new BarEntry(12f, 153355237));
        }
        else if(sp_energy== 1 && sp_year_month == 4 ){ //風力 111
            barEntriesArrayList.add(new BarEntry(1f, 163021821));
            barEntriesArrayList.add(new BarEntry(2f, 138565446));
            barEntriesArrayList.add(new BarEntry(3f, 82118131));
            barEntriesArrayList.add(new BarEntry(4f, 84907022));
            barEntriesArrayList.add(new BarEntry(5f, 79577160));
            barEntriesArrayList.add(new BarEntry(6f, 32327847));
            barEntriesArrayList.add(new BarEntry(7f, 21391177));
            barEntriesArrayList.add(new BarEntry(8f, 11132036));
            barEntriesArrayList.add(new BarEntry(9f, 77762601));
            barEntriesArrayList.add(new BarEntry(10f, 122303767));
            barEntriesArrayList.add(new BarEntry(11f, 86535602));
        }
        else if(sp_energy== 2 && sp_year_month ==0 ) { //太陽能 107
            barEntriesArrayList.add(new BarEntry(1f, 1553524));
            barEntriesArrayList.add(new BarEntry(2f, 1410431));
            barEntriesArrayList.add(new BarEntry(3f, 2321726));
            barEntriesArrayList.add(new BarEntry(4f, 2196746));
            barEntriesArrayList.add(new BarEntry(5f, 2624441));
            barEntriesArrayList.add(new BarEntry(6f, 2169943));
            barEntriesArrayList.add(new BarEntry(7f, 2235815));
            barEntriesArrayList.add(new BarEntry(8f, 1796033));
            barEntriesArrayList.add(new BarEntry(9f, 2188872));
            barEntriesArrayList.add(new BarEntry(10f, 2190171));
            barEntriesArrayList.add(new BarEntry(11f, 1928528));
            barEntriesArrayList.add(new BarEntry(12f, 2162390));
        }
        else if(sp_energy== 2 && sp_year_month == 1 ){ //太陽能 108
            barEntriesArrayList.add(new BarEntry(1f, 2118814));
            barEntriesArrayList.add(new BarEntry(2f, 2269541));
            barEntriesArrayList.add(new BarEntry(3f, 2280226));
            barEntriesArrayList.add(new BarEntry(4f, 2648253));
            barEntriesArrayList.add(new BarEntry(5f, 8650335));
            barEntriesArrayList.add(new BarEntry(6f, 14936554));
            barEntriesArrayList.add(new BarEntry(7f, 16256854));
            barEntriesArrayList.add(new BarEntry(8f, 13049572));
            barEntriesArrayList.add(new BarEntry(9f, 16757586));
            barEntriesArrayList.add(new BarEntry(10f, 15145842));
            barEntriesArrayList.add(new BarEntry(11f, 12312423));
            barEntriesArrayList.add(new BarEntry(12f, 11561883));
        }
        else if(sp_energy== 2 && sp_year_month == 2 ){ //太陽能 109
            barEntriesArrayList.add(new BarEntry(1f, 13699570));
            barEntriesArrayList.add(new BarEntry(2f, 12613389));
            barEntriesArrayList.add(new BarEntry(3f, 14619475));
            barEntriesArrayList.add(new BarEntry(4f, 19078297));
            barEntriesArrayList.add(new BarEntry(5f, 21924811));
            barEntriesArrayList.add(new BarEntry(6f, 27184598));
            barEntriesArrayList.add(new BarEntry(7f, 30612671));
            barEntriesArrayList.add(new BarEntry(8f, 30606439));
            barEntriesArrayList.add(new BarEntry(9f, 25749897));
            barEntriesArrayList.add(new BarEntry(10f, 15002296));
            barEntriesArrayList.add(new BarEntry(11f, 19177533));
            barEntriesArrayList.add(new BarEntry(12f, 23973377));
        }
        else if(sp_energy== 2 && sp_year_month == 3 ){ //太陽能 110
            barEntriesArrayList.add(new BarEntry(1f, 29894224));
            barEntriesArrayList.add(new BarEntry(2f, 31584735));
            barEntriesArrayList.add(new BarEntry(3f, 23503152));
            barEntriesArrayList.add(new BarEntry(4f, 36755696));
            barEntriesArrayList.add(new BarEntry(5f, 42307745));
            barEntriesArrayList.add(new BarEntry(6f, 33375936));
            barEntriesArrayList.add(new BarEntry(7f, 41941535));
            barEntriesArrayList.add(new BarEntry(8f, 34747473));
            barEntriesArrayList.add(new BarEntry(9f, 41766296));
            barEntriesArrayList.add(new BarEntry(10f, 35165305));
            barEntriesArrayList.add(new BarEntry(11f, 29657349));
            barEntriesArrayList.add(new BarEntry(12f, 28137421));
        }
        else if(sp_energy== 2 && sp_year_month == 4 ){ //太陽能 111
            barEntriesArrayList.add(new BarEntry(1f, 28234521));
            barEntriesArrayList.add(new BarEntry(2f, 24551587));
            barEntriesArrayList.add(new BarEntry(3f, 27575937));
            barEntriesArrayList.add(new BarEntry(4f, 40647345));
            barEntriesArrayList.add(new BarEntry(5f, 30447546));
            barEntriesArrayList.add(new BarEntry(6f, 38109621));
            barEntriesArrayList.add(new BarEntry(7f, 43914712));
            barEntriesArrayList.add(new BarEntry(8f, 41841705));
            barEntriesArrayList.add(new BarEntry(9f, 38108740));
            barEntriesArrayList.add(new BarEntry(10f, 32335637));
            barEntriesArrayList.add(new BarEntry(11f, 28455618));

        }
        return true;
    }

    public void back_to_map(View view){
        Intent intent = new Intent(chart.this,MapsActivity.class);
        intent.putExtra("energy_data",data);
        startActivity(intent);

        overridePendingTransition(R.anim.left_in,R.anim.right_out);

        finish();
    }

    public void back_to_main(View view){
        Intent intent = new Intent(chart.this,MainActivity.class);
        intent.putExtra("energy_data",data);
        startActivity(intent);

        overridePendingTransition(R.anim.buttom_in,R.anim.above_out);

        finish();
    }
}