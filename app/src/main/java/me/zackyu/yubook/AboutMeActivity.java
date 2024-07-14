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

    private TextView textWechatAccount;
    private TextView textQqAccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        textWechatAccount = findViewById(R.id.text_wechat_account);
        setUpCopyClickListener(textWechatAccount, "微信");

        textQqAccount = findViewById(R.id.text_qq_account);
        setUpCopyClickListener(textQqAccount, "QQ");
    }

    private void setUpCopyClickListener(TextView textView, String appName) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(textView.getText());
                Toast.makeText(AboutMeActivity.this, "复制成功，打开" + appName + "即可粘贴", Toast.LENGTH_LONG).show();
            }
        });
    }
}