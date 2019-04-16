package me.zackyu.myaccount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.List;

import me.zackyu.myaccount.db.Record;

public class RecordAdapter extends ArrayAdapter<Record> {

    private int resourceId;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public RecordAdapter(@NonNull Context context, int resource, List objs) {
        super(context, resource,objs);
        this.resourceId =resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Record record  =getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView text_amount =view.findViewById(R.id.text_amount);
        TextView text_type =view.findViewById(R.id.text_type);
        TextView text_account = view.findViewById(R.id.text_account);
        TextView text_time = view.findViewById(R.id.text_time);
        text_amount.setText("￥ "+record.getAmount()+"");
        text_type.setText("方式:"+record.getType());
        text_account.setText("账号:"+record.getAccount());
        text_time.setText("时间:"+simpleDateFormat.format(record.getCrttime()));
        return view;
    }
}
