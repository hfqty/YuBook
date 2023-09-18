package me.zackyu.yubook;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import me.zackyu.yubook.constant.DBConstant;
import me.zackyu.yubook.db.iDBHelper;
import me.zackyu.yubook.util.NeutralDialogFragment;

public class NewIncomeActivity extends AppCompatActivity {

    private Button button_new_income_record;
    private Spinner new_income_type;
    private EditText new_income_account;
    private EditText new_income_amount;
    private Spinner new_income_source;

    private String type;
    private String account;
    private Double amount;
    private String source;
    private Date crttime = new Date();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    private iDBHelper iDBHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_income_record);
        button_new_income_record = findViewById(R.id.button_new_income_record);
        new_income_source = findViewById(R.id.new_income_source);
        new_income_type = findViewById(R.id.new_income_type);
        new_income_account = findViewById(R.id.new_income_account);
        new_income_amount = findViewById(R.id.new_income_amount);

       new_income_source.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = NewIncomeActivity.this.getResources().getStringArray(R.array.income_from)[position];

           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
        new_income_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                source =  NewIncomeActivity.this.getResources().getStringArray(R.array.income_to)[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button_new_income_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewIncomeActivity.this,"显示",Toast.LENGTH_SHORT);
                account = new_income_account.getText().toString();
                String str_amount = new_income_amount.getText().toString();
                if (str_amount==null || "".equals(str_amount)){
                    amount=0d;
                }else{
                    amount = Double.parseDouble(str_amount);
                }


                iDBHelper = new iDBHelper(NewIncomeActivity.this, DBConstant.NAME,null,1);
                SQLiteDatabase sqLiteDatabase = iDBHelper.getWritableDatabase();
                ContentValues contentValues  = new ContentValues();
                contentValues.put(DBConstant.SOURCE,source);
                contentValues.put(DBConstant.TYPE,type);
                contentValues.put(DBConstant.ACCOUNT,account);
                contentValues.put(DBConstant.AMOUNT,amount);
                contentValues.put(DBConstant.CRTTIME,simpleDateFormat.format(crttime));
                if(account==null || "".equals(account)){
                    NeutralDialogFragment neutralDialogFragment = new NeutralDialogFragment();
                    neutralDialogFragment.show("提示", "请输入账号！", "确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(NewIncomeActivity.this, "点击了按钮 " , Toast.LENGTH_SHORT).show();
                        }
                    }, getFragmentManager());
                }else {
                if(amount==0||amount<0) {
                    NeutralDialogFragment neutralDialogFragment = new NeutralDialogFragment();
                    neutralDialogFragment.show("提示", "请检查输入的金额！", "确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                          //  Toast.makeText(NewIncomeActivity.this, "点击了按钮 " , Toast.LENGTH_SHORT).show();
                        }
                    }, getFragmentManager());
                }else{

                        sqLiteDatabase.insert(DBConstant.TNAME, null, contentValues);
                        NeutralDialogFragment neutralDialogFragment = new NeutralDialogFragment();
                        neutralDialogFragment.show("结果", "添加成功", "确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               // Toast.makeText(NewIncomeActivity.this, "点击了按钮 ", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(NewIncomeActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                            }
                        }, getFragmentManager());
                    }
                }


            }
        });

    }
}
