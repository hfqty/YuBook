package me.zackyu.myaccount;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.zackyu.myaccount.constant.DBConstant;
import me.zackyu.myaccount.db.Record;
import me.zackyu.myaccount.db.iDBHelper;

public class RecordsActivity extends AppCompatActivity {

    private TextView text_title ;
    private ListView record_listview;

    private RecordAdapter recordAdapter;
    private iDBHelper iDBHelper;
    private List<Record> records ;
    private List<Record> records_income ;
    private List<Record> records_pay ;

    private Button button_records_all;
    private Button button_records_income;
    private Button button_records_pay;



    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        setTitle("记录");
        button_records_all = findViewById(R.id.button_records_all);
        button_records_income = findViewById(R.id.button_records_income);
        button_records_pay = findViewById(R.id.button_records_pay);
        text_title = findViewById(R.id.text_title);


        record_listview = findViewById(R.id.record_list);
        iDBHelper = new iDBHelper(RecordsActivity.this, DBConstant.NAME,null,1);
        getAllRecords();
        recordAdapter = new RecordAdapter(this,R.layout.record_item,records);
        record_listview.setAdapter(recordAdapter);

        button_records_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_title.setText("所有");
                getAllRecords();
                recordAdapter = new RecordAdapter(RecordsActivity.this,R.layout.record_item,records);
                record_listview.setAdapter(recordAdapter);
            }
        });
        button_records_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_title.setText("收入");
                getIncomeRecords();
                recordAdapter = new RecordAdapter(RecordsActivity.this,R.layout.record_item,records_income);
                record_listview.setAdapter(recordAdapter);
            }
        });
        button_records_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_title.setText("支出");
                getPayRecords();
                recordAdapter = new RecordAdapter(RecordsActivity.this,R.layout.record_item,records_pay);
                record_listview.setAdapter(recordAdapter);
            }
        });

    }

    public void getAllRecords(){
        records = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = iDBHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query("MyAccount",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                Record record = new Record();
                int id = cursor.getInt(0);
                String type = cursor.getString(1);
                String account = cursor.getString(2);
                double amount = cursor.getDouble(3);
                String remark = cursor.getString(4);
                String date = cursor.getString(5);
                Date crttime =new Date();
                try {
                    crttime = simpleDateFormat.parse(date);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                record.setType(type);
                record.setAccount(account);
                record.setAmount(amount);
                record.setId(id);
                record.setCrttime(crttime);
                record.setRemark(remark);
                records.add(record);
            }while (cursor.moveToNext());
        }

    }

    public void getIncomeRecords(){
        records_income = new ArrayList<>();
        String sql_income = "select  * from MyAccount where amount >0;";
        SQLiteDatabase sqLiteDatabase = iDBHelper.getWritableDatabase();
        Cursor cursor_income = sqLiteDatabase.rawQuery(sql_income,null);
        if(cursor_income.moveToFirst()){
            do {
                Record record = new Record();
                int id = cursor_income.getInt(0);
                String type = cursor_income.getString(1);
                String account = cursor_income.getString(2);
                double amount = cursor_income.getDouble(3);
                String remark = cursor_income.getString(4);
                String date = cursor_income.getString(5);
                Date crttime =new Date();
                try {
                    crttime = simpleDateFormat.parse(date);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                record.setType(type);
                record.setAccount(account);
                record.setAmount(amount);
                record.setId(id);
                record.setCrttime(crttime);
                record.setRemark(remark);
                records_income.add(record);
            }while (cursor_income.moveToNext());
        }

    }

    public void getPayRecords(){
        records_pay = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = iDBHelper.getWritableDatabase();
        String sql_pay = "select * from MyAccount where amount < 0;";

        Cursor cursor_pay = sqLiteDatabase.rawQuery(sql_pay,null);
        if(cursor_pay.moveToFirst()){
            do {
                Record record = new Record();
                int id = cursor_pay.getInt(0);
                String type = cursor_pay.getString(1);
                String account = cursor_pay.getString(2);
                double amount = cursor_pay.getDouble(3);
                String remark = cursor_pay.getString(4);
                String date = cursor_pay.getString(5);
                Date crttime =new Date();
                try {
                    crttime = simpleDateFormat.parse(date);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                record.setType(type);
                record.setAccount(account);
                record.setAmount(amount);
                record.setId(id);
                record.setCrttime(crttime);
                record.setRemark(remark);
                records_pay.add(record);
            }while (cursor_pay.moveToNext());
        }
    }
}
