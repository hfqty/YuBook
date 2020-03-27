package me.zackyu.yubook;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AboutMeActivity extends AppCompatActivity {

    private TextView text_wechat_account;
    private TextView text_qq_account;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_about_me);
        text_wechat_account = findViewById(R.id.text_wechat_account);
        text_wechat_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(text_wechat_account.getText());
                Toast.makeText(AboutMeActivity.this, "复制成功，打开微信即可粘贴",Toast.LENGTH_LONG).show();//自定义的toast
            }
        });
        text_qq_account = findViewById(R.id.text_qq_account);
        text_qq_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(text_qq_account.getText());
                Toast.makeText(AboutMeActivity.this, "复制成功，打开qq即可粘贴",Toast.LENGTH_LONG).show();//自定义的toast
            }
        });
    }
}
