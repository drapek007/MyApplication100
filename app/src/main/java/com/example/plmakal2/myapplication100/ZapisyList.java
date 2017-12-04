package com.example.plmakal2.myapplication100;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by PLMAKAL2 on 2017-11-29.
 */

public class ZapisyList extends ArrayAdapter {
    private Activity context;
    private List<User> zapisyList;

    public ZapisyList(Activity context, List<User> zapisyList){
        super(context, R.layout.list_layout2, zapisyList);
        this.context = context;
        this.zapisyList = zapisyList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout2, null, true);

        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.textViewEmail);
       // TextView textViewDate = (TextView) listViewItem.findViewById(R.id.textViewDate);
        // TextView textViewTime = (TextView) listViewItem.findViewById(R.id.textViewTime);
        //  TextView textViewQuantity = (TextView) listViewItem.findViewById(R.id.textViewQuantity);

        User user5 = zapisyList.get(position);

        textViewEmail.setText(user5.getEmail());
        //textViewDate.setText(addTraining.getDate());
        // textViewTime.setText(addTraining.getTime());
        // textViewQuantity.setText(addTraining.getQuantity());

        return listViewItem;
    }

}
