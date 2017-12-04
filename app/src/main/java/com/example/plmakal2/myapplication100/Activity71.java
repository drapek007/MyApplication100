package com.example.plmakal2.myapplication100;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Activity71 extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private Button btnSave;
    private EditText inputPlace, inputDate, inputTime, inputQuantity;
    private String trainingID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_61);


        inputPlace = (EditText) findViewById(R.id.editText);
        inputDate = (EditText) findViewById(R.id.editText2);
        inputTime = (EditText) findViewById(R.id.editText3);
        inputQuantity = (EditText) findViewById(R.id.editText4);
        btnSave = (Button) findViewById(R.id.button2);

        databaseReference = FirebaseDatabase.getInstance().getReference("PaczekBadminton");


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String place = inputPlace.getText().toString();
                String date = inputDate.getText().toString();
                String value= inputTime.getText().toString();
                int time=Integer.parseInt(value);

                String value2= inputQuantity.getText().toString();
                int quantity=Integer.parseInt(value2);

                //addTraining(place, date, time, quantity);


            }
        });

    }

  /*  private void addTraining(String place, String date, int time, int quantity) {


        if(TextUtils.isEmpty(place)){
            Toast.makeText(this,"Dodaj miejsce treningu",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(date)){
            Toast.makeText(this,"Dodaj date treningu",Toast.LENGTH_LONG).show();
            return;
        }


        trainingID = databaseReference.push().getKey();


        AddTraining addtr = new AddTraining(place, date, time, quantity, trainingID);

        databaseReference.child(trainingID).setValue(addtr);




        Toast.makeText(this, "Trening dodany",Toast.LENGTH_LONG).show();

    }

*/
}
