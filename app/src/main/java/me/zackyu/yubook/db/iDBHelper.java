package me.zackyu.yubook.db;

import static android.content.ContentValues.TAG;

import static me.zackyu.yubook.constant.DBConstant.NAME;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.util.jar.Attributes;

public class iDBHelper extends SQLiteOpenHelper {
    private Context icontext;
    public static final String CREATE_ACCOUNT_DB =
            "create table MyAccount (" + "id integer primary key autoincrement, " + " type text, "+ " source text,"+" account text," + " amount real , "  +" crttime NUMERIC)";


    public iDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory,int version) {
        super(context, name, factory, version);
        this.icontext = context;

/*        String databasePath = context.getDatabasePath(NAME).getPath();

        // 关闭数据库连接
        SQLiteDatabase database = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READWRITE);
        database.close();

        // 删除数据库文件
        File databaseFile = new File(databasePath);
        if (databaseFile.exists()) {
            boolean deleted = databaseFile.delete();
            if (deleted) {
                Log.d(TAG, "数据库文件已成功删除");
            } else {
                Log.d(TAG, "数据库文件删除失败");
            }
        } else {
            Log.d(TAG, "数据库文件不存在");
        }*/
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
