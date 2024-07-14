package me.zackyu.yubook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.List;

import me.zackyu.yubook.db.Record;

public class RecordAdapter extends ArrayAdapter<Record> {

    private int resourceId;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public RecordAdapter(@NonNull Context context, int resource, @NonNull List<Record> objs) {
        super(context, resource, objs);
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Record record = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        }

        TextView textAmount = convertView.findViewById(R.id.text_amount);
        TextView textType = convertView.findViewById(R.id.text_type);
        TextView textAccount = convertView.findViewById(R.id.text_account);
        TextView textTime = convertView.findViewById(R.id.text_time);
        TextView textSource = convertView.findViewById(R.id.text_source);

        textAmount.setText("￥ " + record.getAmount());
        textSource.setText("来源: " + record.getSource());
        textType.setText("去向: " + record.getType());
        textAccount.setText("账号: " + record.getAccount());
        textTime.setText("时间: " + simpleDateFormat.format(record.getCrttime()));

        return convertView;
    }
}