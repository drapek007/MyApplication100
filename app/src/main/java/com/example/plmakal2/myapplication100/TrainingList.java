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
 * Created by PLMAKAL2 on 2017-11-19.
 */

public class TrainingList extends ArrayAdapter<AddTraining> {

    private Activity context;
    private List<AddTraining> trainingList;

    public TrainingList(Activity context, List<AddTraining> trainingList){
        super(context, R.layout.list_layout, trainingList);
        this.context = context;
        this.trainingList = trainingList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewPlace);
        TextView textViewDate = (TextView) listViewItem.findViewById(R.id.textViewDate);
       // TextView textViewTime = (TextView) listViewItem.findViewById(R.id.textViewTime);
      //  TextView textViewQuantity = (TextView) listViewItem.findViewById(R.id.textViewQuantity);

        AddTraining addTraining = trainingList.get(position);

        textViewName.setText(addTraining.getName());
        textViewDate.setText(addTraining.getDate());
       // textViewTime.setText(addTraining.getTime());
       // textViewQuantity.setText(addTraining.getQuantity());

        return listViewItem;
    }
}
