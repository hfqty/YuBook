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

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import me.zackyu.yubook.constant.DBConstant;
import me.zackyu.yubook.db.iDBHelper;
import me.zackyu.yubook.util.NeutralDialogFragment;

public class NewPayActivity extends AppCompatActivity {

    private Button button_new_pay_record;
    private Spinner new_pay_type;


    private Spinner new_pay_source;
    private EditText new_pay_account;
    private EditText new_pay_amount;

    private String type;
    private String account;
    private Double amount;
    private String source;
    private String crttime;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Date new_pay_crttime=new Date();

    private iDBHelper iDBHelper ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pay_record);

        button_new_pay_record = findViewById(R.id.button_new_pay_record);
        new_pay_source = findViewById(R.id.new_pay_sources);
        new_pay_type = findViewById(R.id.new_pay_type);
        new_pay_account = findViewById(R.id.new_pay_account);
        new_pay_amount = findViewById(R.id.new_pay_amount);

        //2023年9月18日 11点53分
        new_pay_source.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type =  NewPayActivity.this.getResources().getStringArray(R.array.pay_from)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //2023年9月18日 11点54分
        new_pay_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                source =  NewPayActivity.this.getResources().getStringArray(R.array.pay_for)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button_new_pay_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewPayActivity.this,"显示",Toast.LENGTH_SHORT);
                account = new_pay_account.getText().toString();
                String str_amount = new_pay_amount.getText().toString();
                if (str_amount.isEmpty()){
                    amount=0d;
                }else{
                    amount = Double.parseDouble(str_amount);
                    String str_big_amount=BigDecimal.ZERO.subtract(new BigDecimal(amount))
                            .setScale(2,BigDecimal.ROUND_HALF_UP).toString();
                    amount = Double.parseDouble(str_big_amount);

                }


                crttime = simpleDateFormat.format(new_pay_crttime);
                iDBHelper = new iDBHelper(NewPayActivity.this, DBConstant.NAME,null,1);
                SQLiteDatabase sqLiteDatabase = iDBHelper.getWritableDatabase();
               //准备数据
                ContentValues contentValues  = new ContentValues();
                contentValues.put(DBConstant.SOURCE, source);
                contentValues.put(DBConstant.TYPE,type);
                contentValues.put(DBConstant.ACCOUNT,account);
                contentValues.put(DBConstant.AMOUNT,amount);
                contentValues.put(DBConstant.CRTTIME,crttime);
                if(account==null || "".equals(account)){
                    NeutralDialogFragment neutralDialogFragment = new NeutralDialogFragment();
                    neutralDialogFragment.show("提示", "请输入账号！", "确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }, getFragmentManager());
                }else {
                    if(amount==0||amount>0) {
                        NeutralDialogFragment neutralDialogFragment = new NeutralDialogFragment();
                        neutralDialogFragment.show("提示", "请检查输入的金额！", "确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }, getFragmentManager());
                    }else{
                        sqLiteDatabase.insert(DBConstant.TNAME, null, contentValues);
                        NeutralDialogFragment neutralDialogFragment = new NeutralDialogFragment();
                        neutralDialogFragment.show("结果", "成功了", "确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(NewPayActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, getFragmentManager());
                    }
                }
                //插入数据
            }
        });
    }
}
