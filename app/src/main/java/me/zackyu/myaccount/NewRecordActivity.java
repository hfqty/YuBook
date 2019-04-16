package me.zackyu.myaccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import me.zackyu.myaccount.db.iDBHelper;

public class NewRecordActivity extends AppCompatActivity {

    private iDBHelper iDBHelper ;
    private Button button_income;
    private Button button_pay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);
        setTitle("记账");
        iDBHelper = new iDBHelper(this,"MyAccount.db",null,1);


        button_income = findViewById(R.id.button_income);
        button_pay = findViewById(R.id.button_pay);

        button_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iDBHelper.getWritableDatabase();
                Toast.makeText(NewRecordActivity.this,"记账"+iDBHelper.getDatabaseName(),Toast.LENGTH_LONG);
                Intent intent = new Intent();
                intent.setClass(NewRecordActivity.this,NewIncomeActivity.class);
                startActivity(intent);
            }
        });
        button_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(NewRecordActivity.this,NewPayActivity.class);
                startActivity(intent);
            }
        });
    }
}
