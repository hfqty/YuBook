package me.zackyu.yubook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class iDBHelper extends SQLiteOpenHelper {
    private Context icontext;
    public static final String CREATE_ACCOUNT_DB =
            "create table MyAccount (" + "id integer primary key autoincrement, " + "type text, "+"account text," + "amount real, "  + "remark text,"+"crttime NUMERIC)";


    public iDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory,int version) {
        super(context, name, factory, version);
        this.icontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ACCOUNT_DB);
        Toast.makeText(icontext,"创建成功",Toast.LENGTH_LONG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
