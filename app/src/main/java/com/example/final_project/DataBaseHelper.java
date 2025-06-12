package com.example.final_project;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "FAMILY_USE.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "user_data";
    //private static final String COLUMN_ID = "id";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_MONTH = "month";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_MACHINE = "machine";
    private static final String COLUMN_WATTS = "watts";
    private static final String COLUMN_USING_TIME = "using_time";
//    private static final String COLUMN_SEX = "sex";
//    private static final String COLUMN_SPORT = "sport";



    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ TABLE_NAME +
                " (" + COLUMN_YEAR + " INTEGER, " +
                COLUMN_MONTH + " INTEGER, " +
                COLUMN_DATE + " INTEGER, " +
                COLUMN_MACHINE + " TEXT PRIMARY KEY, " +
                COLUMN_WATTS + " REAL, " +
                COLUMN_USING_TIME + " REAL); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void Save_user_data(Integer year,Integer month,Integer date, String machine, Double watts, Double using_time,
                               Family_use_Adapter family_use_adapter,family_use familyUse  ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        long result = -1;

        cv.put(COLUMN_YEAR, year);
        cv.put(COLUMN_MONTH, month);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_MACHINE, machine);
        cv.put(COLUMN_WATTS, watts);
        cv.put(COLUMN_USING_TIME, using_time);


        //處理重複name (詢問是否要更新)
        Cursor c = readAlldata();
        c.moveToFirst();
        boolean is_name_repeat = false;
        for(int i=0;i<c.getCount();i++){
            System.out.println(c.getString(0)+ "/// "+machine+"///");
            if(c.getString(0).equals(year.toString()) && c.getString(1).equals(month.toString())
                    && c.getString(2).equals(date.toString())&& c.getString(3).equals(machine)){
                //彈出視窗
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("資料已存在");
                alert.setMessage("是否要覆寫?");
                alert.create();
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("YES");
                        long result = db.update(TABLE_NAME,cv,"year=? AND month=? AND date=? AND machine=?"
                                ,new String[]{String.valueOf(year) ,String.valueOf(month),String.valueOf(date),machine});
                        family_use a = familyUse;
                        Family_use_Adapter c = family_use_adapter;
                        a.StoreDataInArray(false);
                        c.notifyDataSetChanged();

                        if(result == -1){
                            Toast.makeText(context,"Save_Fail (Updata)",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(context,"Save_Success (Updata)",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("NO");
                    }
                });
                alert.show();

//                if(override_or_not[0] == false){ //當一按下sava會整個跑 為了
//                    System.out.println("override_or_not");
//                    return;
//                }
                return;

                //is_name_repeat = true;
                //result = db.update(TABLE_NAME,cv,"name=?",new String[]{name});
                //break;
            }
            c.moveToNext();
        }

        if(is_name_repeat == false) {
            result = db.insert(TABLE_NAME, null, cv);
        }

        if(result == -1){
            Toast.makeText(context,"Save_Fail (Insert)",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Save_Success (Insert)",Toast.LENGTH_SHORT).show();
        }
    }

    void Delete_user_data(String click_year,String click_month, String click_date
            ,String click_name, Family_use_Adapter family_use_adapter,family_use familyuse){
        SQLiteDatabase db = this.getWritableDatabase();

        //彈出視窗
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("資料刪除");
        alert.setMessage("是否要刪除"+" name = "+click_name+"\n"+
                "日期 = "+click_year+"-"+click_month+"-"+click_date+" ?");
        alert.create();
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("YES");
                long result = db.delete(
                        TABLE_NAME,"year=? AND month=? AND date=? AND machine=?"
                        ,new String[]{click_year,click_month,click_date,click_name});
                family_use a = familyuse;
                Family_use_Adapter c = family_use_adapter;
                a.StoreDataInArray(false);
                c.notifyDataSetChanged();

                if(result == -1){
                    Toast.makeText(context,"Delete_Fail",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context,"Delete_Success",Toast.LENGTH_SHORT).show();
                }

            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("NO");
            }
        });
        alert.show();

    }

    void Return_user_data(String click_name){
        Cursor c = readAlldata();
        c.moveToFirst();
        for(int i=0;i<c.getCount();i++){
            if(c.getString(0).equals(click_name)){

            }
            c.moveToNext();
        }
    }

    Cursor readAlldata(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
}
