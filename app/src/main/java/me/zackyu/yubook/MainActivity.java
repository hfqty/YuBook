package me.zackyu.yubook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

import me.zackyu.yubook.db.iDBHelper;

public class MainActivity extends AppCompatActivity {
    private Button button_new_record;
    private Button button_records;

    private TextView text_income ;
    private TextView text_pay;
    private TextView text_total;

    private iDBHelper iDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_new_record = findViewById(R.id.button_new_record);
        button_records = findViewById(R.id.button_records);

        iDBHelper = new iDBHelper(MainActivity.this,"MyAccount.db",null,1);

        button_new_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,NewRecordActivity.class);
                startActivity(intent);
            }
        });
        button_records .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,RecordsActivity.class);
                startActivity(intent);
            }
        });
        showData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showData();
    }

    private void showData(){
        SQLiteDatabase sqLiteDatabase = iDBHelper.getWritableDatabase();
        String sql_income = "select sum(amount) from MyAccount where amount >0";
        String sql_pay = "select sum(amount) from MyAccount where amount <0";

        Cursor cursor_income = sqLiteDatabase.rawQuery(sql_income,null);
        Cursor cursor_pay = sqLiteDatabase.rawQuery(sql_pay,null);
        Double income =  0.0;

        if(cursor_income.moveToFirst()){
            income = cursor_income.getDouble(0);
            text_income = findViewById(R.id.text_income);
            text_income.setText(new BigDecimal(income)
                    .setScale(2,BigDecimal.ROUND_HALF_UP)
                    .toString());
        }
        Double pay = 0.0;
        if(cursor_pay.moveToFirst()){
            pay = cursor_pay.getDouble(0);
            text_pay = findViewById(R.id.text_pay);
            text_pay.setText(BigDecimal.ZERO.subtract(new BigDecimal(pay))
                    .setScale(2,BigDecimal.ROUND_HALF_UP)
                    .toString());
        }
        double total = 0.0;
        if(income != null &&pay !=null) {
            total = income + pay;
        }
        text_total = findViewById(R.id.text_total);
        text_total.setText(new BigDecimal(total)
                .setScale(2,BigDecimal.ROUND_HALF_UP)
                .toString());


    }
}
