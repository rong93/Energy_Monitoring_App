package com.example.final_project;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.final_project.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private Spinner spinner;
    private ArrayList<String> data;
    Map<Integer, String> map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Log.v("!!!","進入到MapActivity");

        Intent intent = getIntent();
        data =  (ArrayList<String>)intent.getSerializableExtra("energy_data");


        //將每項分類
        for(int i=0;i<data.size();i++) {
            if(i%6==0) {
                Log.v("!!!", "" + "能源 " + data.get(i) + " " +
                        data.get(i+1)+ " " + data.get(i+2) + " " +
                        data.get(i+3)+ " " + data.get(i+4)+ " " +
                        data.get(i+5));
            }
        }


        map = new HashMap<Integer, String>();
        //核能
        map.put(0,"25.2070051757201, 121.6599597804264");
        map.put(1,"22.02690185464536, 120.74352934160417");

        //燃煤
        map.put(4,"25.11956708195257, 121.29856888233118");
        map.put(7,"24.216352022269415, 120.49240837892714");
        map.put(17,"25.2070051757201, 121.6599597804264");
        map.put(21,"22.53161531294631, 120.33643086560645");


        //燃氣
        map.put(32,"25.03349854054823, 121.05004291302971");
        map.put(39,"25.2070051757201, 121.6599597804264");
        map.put(45,"22.85654706762695, 120.19694439764318");
        map.put(50,"22.601071002757394, 120.30055559763981");
        map.put(54,"22.53349184122947, 120.33329038229495");

        //燃油
        map.put(68,"25.157426117795954, 121.73947658418382");
        map.put(70,"23.564892295817504, 119.66092223892683");

        //水力
        map.put(82,"24.254626711197858, 121.16549853999064");
        map.put(85,"24.249591117009988, 121.10638889766248");
        map.put(89,"24.209589469395684, 121.02289992698357");
        map.put(93,"24.1849442555184, 120.92221664232787");
        map.put(98,"24.182926159783932, 120.83994736560643");
        map.put(100,"24.350463912290063, 120.88142141904929");
        map.put(102,"23.97766202005354, 121.13720178626345");
        map.put(106,"23.96622274375996, 121.08764204973782");
        map.put(107,"23.855192359859203, 120.87032910318068");
        map.put(112,"23.8186269341557, 120.85877921006634");
        map.put(114,"23.835771487092536, 120.86888889765659");
        map.put(115,"24.15589350406558, 121.62721298095074");
        map.put(116,"24.003807104329, 121.42647472834706");
        map.put(118,"24.336046490290876, 121.67628190370498");
        map.put(119,"24.93863663777816, 121.54298661111498");
        map.put(124,"24.909972384208814, 121.57756986508211");
        map.put(125,"24.813414125105336, 121.24521713334491");
        map.put(127,"23.248496807885008, 120.533396203705");
        map.put(128,"24.77800678043195, 121.36451333334493");
        map.put(129,"23.835306021924502, 120.71211736403349");
        map.put(131,"24.325924357267102, 120.72822240390197");
        map.put(132,"22.822946510238868, 121.08671414023058");
        map.put(133,"23.042170776734654, 121.16605644127921");

        //風力
        map.put(135,"25.06889176654417, 121.08352438504167");
        map.put(136,"24.30511725497144, 120.54407354232787");
        map.put(137,"23.9847558830022, 120.34146987885347");
        map.put(138,"24.1668820321008, 120.43335475533347");
        map.put(139,"23.81567336774845, 120.26307705532832");
        map.put(140,"23.63353969351414, 120.13324746696584");
        map.put(149,"24.709445423094365, 120.81513387301642");
        map.put(150,"23.650582620695467, 120.14650225767213");

        //太陽能
        map.put(156,"24.106103348221552, 120.3950071018525");

        //抽蓄負載
        map.put(168,"23.854470867102595, 120.87073673813298");
        map.put(172,"23.83638586838448, 120.86804486882056");

        //地熱
        map.put(189,"24.613108532435103, 121.6381526719678");




//        try {
//            Log.v("!!!",map.get("168"));
//            Log.v("!!!",map.get("173"));
//        }
//        catch (Exception e){
//            Log.v("!!!","not find!");
//        }





        spinner = findViewById(R.id.energy);
        String[] Spinner={"核能","燃煤", "燃氣","水力","風力","太陽能","抽蓄發電","地熱"};
        spinner=(Spinner)findViewById(R.id.energy);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>( this, android.R.layout.select_dialog_item, Spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v("!!!","選擇 "+Spinner[i]);
                if(i==0){
                    mMap.clear();
                    LatLng taiwan_center = new LatLng(23.97915712558235, 120.98061714799411);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(taiwan_center,7));

                    int count = 0;
                    for(int k=0;k<data.size();k+=6){
                        if(data.get(k+0).equals("\"核能\"")){
                            //map.get(i)
                            Log.v("!!!",data.get(k+0)+" "+data.get(k+1)+" "+k);
                            try {
                                Log.v("!!!",map.get(k/6));
                                String[] tudes = map.get(k/6).split(",");
                                Double tude0 = Double.valueOf(tudes[0]);
                                Double tude1 = Double.valueOf(tudes[1]);
                                LatLng location = new LatLng(tude0, tude1);
                                mMap.addMarker(new MarkerOptions().position(location).title(data.get(k+1)+" 淨電量"+data.get(k+3)));
                                Log.v("!!!","Find!!!");
                                count++;
                            }
                            catch (Exception e){
                                Log.v("!!!","Not found");
                            }
                        }
                    }
                    Log.v("!!!", String.valueOf(count));

                }
                else if (i==1){
                    mMap.clear();
                    LatLng taiwan_center = new LatLng(23.97915712558235, 120.98061714799411);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(taiwan_center,7));

                    int count = 0;
                    for(int k=0;k<data.size();k+=6){
                        if(data.get(k+0).equals("\"燃煤\"")){
                            //map.get(i)
                            Log.v("!!!",data.get(k+0)+" "+data.get(k+1)+" "+k);
                            try {
                                Log.v("!!!",map.get(k/6));
                                String[] tudes = map.get(k/6).split(",");
                                Double tude0 = Double.valueOf(tudes[0]);
                                Double tude1 = Double.valueOf(tudes[1]);
                                LatLng location = new LatLng(tude0, tude1);
                                mMap.addMarker(new MarkerOptions().position(location).title(data.get(k+1)+" 淨電量"+data.get(k+3)));
                                Log.v("!!!","Find!!!");
                                count++;
                            }
                            catch (Exception e){
                                Log.v("!!!","Not found");
                            }
                        }
                    }
                    Log.v("!!!", String.valueOf(count));
                }
                else if (i==2){
                    mMap.clear();
                    LatLng taiwan_center = new LatLng(23.97915712558235, 120.98061714799411);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(taiwan_center,7));

                    int count = 0;
                    for(int k=0;k<data.size();k+=6){
                        if(data.get(k+0).equals("\"燃氣\"")){
                            //map.get(i)
                            Log.v("!!!",data.get(k+0)+" "+data.get(k+1)+" "+k);
                            try {
                                Log.v("!!!",map.get(k/6));
                                String[] tudes = map.get(k/6).split(",");
                                Double tude0 = Double.valueOf(tudes[0]);
                                Double tude1 = Double.valueOf(tudes[1]);
                                LatLng location = new LatLng(tude0, tude1);
                                mMap.addMarker(new MarkerOptions().position(location).title(data.get(k+1)+" 淨電量"+data.get(k+3)));
                                Log.v("!!!","Find!!!");
                                count++;
                            }
                            catch (Exception e){
                                Log.v("!!!","Not found");
                            }
                        }
                    }
                    Log.v("!!!", String.valueOf(count));
                }
                else if (i==3){
                    mMap.clear();
                    LatLng taiwan_center = new LatLng(23.97915712558235, 120.98061714799411);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(taiwan_center,7));

                    int count = 0;
                    for(int k=0;k<data.size();k+=6){
                        if(data.get(k+0).equals("\"水力\"")){
                            //map.get(i)
                            Log.v("!!!",data.get(k+0)+" "+data.get(k+1)+" "+k);
                            try {
                                Log.v("!!!",map.get(k/6));
                                String[] tudes = map.get(k/6).split(",");
                                Double tude0 = Double.valueOf(tudes[0]);
                                Double tude1 = Double.valueOf(tudes[1]);
                                LatLng location = new LatLng(tude0, tude1);
                                mMap.addMarker(new MarkerOptions().position(location).title(data.get(k+1)+" 淨電量"+data.get(k+3)));
                                Log.v("!!!","Find!!!");
                                count++;
                            }
                            catch (Exception e){
                                Log.v("!!!","Not found");
                            }
                        }
                    }
                    Log.v("!!!", String.valueOf(count));
                }
                else if (i==4){
                    mMap.clear();
                    LatLng taiwan_center = new LatLng(23.97915712558235, 120.98061714799411);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(taiwan_center,7));

                    int count = 0;
                    for(int k=0;k<data.size();k+=6){
                        if(data.get(k+0).equals("\"風力\"")){
                            //map.get(i)
                            Log.v("!!!",data.get(k+0)+" "+data.get(k+1)+" "+k);
                            try {
                                Log.v("!!!",map.get(k/6));
                                String[] tudes = map.get(k/6).split(",");
                                Double tude0 = Double.valueOf(tudes[0]);
                                Double tude1 = Double.valueOf(tudes[1]);
                                LatLng location = new LatLng(tude0, tude1);
                                mMap.addMarker(new MarkerOptions().position(location).title(data.get(k+1)+" 淨電量"+data.get(k+3)));
                                Log.v("!!!","Find!!!");
                                count++;
                            }
                            catch (Exception e){
                                Log.v("!!!","Not found");
                            }
                        }
                    }
                    Log.v("!!!", String.valueOf(count));

                }
                else if (i==5){
                    mMap.clear();
                    LatLng taiwan_center = new LatLng(23.97915712558235, 120.98061714799411);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(taiwan_center,7));

                    int count = 0;
                    for(int k=0;k<data.size();k+=6){
                        if(data.get(k+0).equals("\"太陽能\"")){
                            //map.get(i)
                            Log.v("!!!",data.get(k+0)+" "+data.get(k+1)+" "+k);
                            try {
                                Log.v("!!!",map.get(k/6));
                                String[] tudes = map.get(k/6).split(",");
                                Double tude0 = Double.valueOf(tudes[0]);
                                Double tude1 = Double.valueOf(tudes[1]);
                                LatLng location = new LatLng(tude0, tude1);
                                mMap.addMarker(new MarkerOptions().position(location).title(data.get(k+1)+" 淨電量"+data.get(k+3)));
                                Log.v("!!!","Find!!!");
                                count++;
                            }
                            catch (Exception e){
                                Log.v("!!!","Not found");
                            }
                        }
                    }
                    Log.v("!!!", String.valueOf(count));

                }
                else if (i==6){
                    mMap.clear();
                    LatLng taiwan_center = new LatLng(23.97915712558235, 120.98061714799411);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(taiwan_center,7));

                    int count = 0;
                    for(int k=0;k<data.size();k+=6){
                        if(data.get(k+0).equals("\"抽蓄發電\"")){
                            //map.get(i)
                            Log.v("!!!",data.get(k+0)+" "+data.get(k+1)+" "+k);
                            try {
                                Log.v("!!!",map.get(k/6));
                                String[] tudes = map.get(k/6).split(",");
                                Double tude0 = Double.valueOf(tudes[0]);
                                Double tude1 = Double.valueOf(tudes[1]);
                                LatLng location = new LatLng(tude0, tude1);
                                mMap.addMarker(new MarkerOptions().position(location).title(data.get(k+1)+" 淨電量"+data.get(k+3)));
                                Log.v("!!!","Find!!!");
                                count++;
                            }
                            catch (Exception e){
                                Log.v("!!!","Not found");
                            }
                        }
                    }
                    Log.v("!!!", String.valueOf(count));

                }
                else if (i==7){
                    mMap.clear();
                    LatLng taiwan_center = new LatLng(23.97915712558235, 120.98061714799411);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(taiwan_center,7));

                    int count = 0;
                    for(int k=0;k<data.size();k+=6){
                        if(data.get(k+0).equals("\"其它再生能源(Other Renewable Energy)<\\/b>\"")){
                            //map.get(i)
                            Log.v("!!!",data.get(k+0)+" "+data.get(k+1)+" "+k);
                            try {
                                Log.v("!!!",map.get(k/6));
                                String[] tudes = map.get(k/6).split(",");
                                Double tude0 = Double.valueOf(tudes[0]);
                                Double tude1 = Double.valueOf(tudes[1]);
                                LatLng location = new LatLng(tude0, tude1);
                                mMap.addMarker(new MarkerOptions()
                                        .position(location).title(data.get(k+1)+" 淨電量"+data.get(k+3)));
                                Log.v("!!!","Find!!!");
                                count++;
                            }
                            catch (Exception e){
                                Log.v("!!!","Not found");
                            }
                        }
                    }
                    Log.v("!!!", String.valueOf(count));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        LatLng taiwan_center = new LatLng(23.97915712558235, 120.98061714799411);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(taiwan_center,7));

    }

    public void back_to_main(View view) {
        Intent intent = new Intent(MapsActivity.this,MainActivity.class);
        startActivity(intent);

        overridePendingTransition(R.anim.buttom_in,R.anim.above_out);

        finish();
    }

    public void energy_detail(View view){
        Intent intent = new Intent(MapsActivity.this,Engery_Detail.class);
        intent.putExtra("energy_data",data);
        startActivity(intent);

        overridePendingTransition(R.anim.left_in,R.anim.right_out);
        //overridePendingTransition(R.anim.above_in,R.anim.buttom_out);


        finish();
    }

    public void go_to_chart(View view){
        Intent intent = new Intent(MapsActivity.this,chart.class);
        intent.putExtra("energy_data",data);
        startActivity(intent);

        overridePendingTransition(R.anim.right_in,R.anim.left_out);
        //overridePendingTransition(R.anim.above_in,R.anim.buttom_out);

        finish();
    }
}