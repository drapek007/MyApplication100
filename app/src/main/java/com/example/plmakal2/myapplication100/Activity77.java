package com.example.plmakal2.myapplication100;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Activity77 extends AppCompatActivity implements View.OnClickListener{



    ListView listViewTraining;
    int quantity=0;
    String id;
    List<AddTraining> trainingList;
    Button buttonUpdate;


    DatabaseReference datababaseTraining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_77);



        datababaseTraining = FirebaseDatabase.getInstance().getReference("PowerBad");

        listViewTraining = (ListView) findViewById(R.id.listViewTraining);
        trainingList = new ArrayList<>();

        listViewTraining.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AddTraining addTraining = trainingList.get(i);

                quantity=addTraining.getQuantity();
                showUpdateDialog(addTraining.getPlace(), addTraining.getDate(), addTraining.getTime(), addTraining.getQuantity(), addTraining.getId());

                return false;
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        datababaseTraining.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                trainingList.clear();

                for(DataSnapshot trainingSnapshot : dataSnapshot.getChildren()){
                    id = trainingSnapshot.getKey();

                    AddTraining addtraining = trainingSnapshot.getValue(AddTraining.class);


                    System.out.println("kupa " + addtraining.getPlace());
                    System.out.println("kupa " + addtraining.getTime());
                    System.out.println("kupa " + addtraining.getDate());
                    System.out.println("kupa " + addtraining.getQuantity());
                    System.out.println("kupa " + addtraining.getId());
                    trainingList.add(addtraining);



                }

                TrainingList adapter = new TrainingList(Activity77.this, trainingList);
                listViewTraining.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    //czy globalne nazwy = ok?
    private void showUpdateDialog(String place, String date, int time, int quantity, String id){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.update_dialog, null);

        dialogBuilder.setView(dialogView);

        final TextView textViewPlace = (TextView) dialogView.findViewById(R.id.textViewPlace2); // zostalo przekazane cale pola klasy add training: place date id
        final TextView textViewDate = (TextView) dialogView.findViewById(R.id.textViewDate2);   // show up dialog wywoluje nowe okienko w ktorym przekazane dane wyswietlamy
        final TextView textViewTime = (TextView) dialogView.findViewById(R.id.textViewTime2);   //oprocz oczywiscie klucza childa!
        final TextView textViewQuantity = (TextView) dialogView.findViewById(R.id.textViewQuantity2);
        buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(this);
        dialogBuilder.setTitle("Szczegóły treningu:");

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        textViewPlace.setText(place);
        textViewDate.setText("Data: " + date);
        textViewTime.setText("Godzina: " + String.valueOf(time));
        textViewQuantity.setText("Ilosc wolnych miejsc: " + String.valueOf(quantity));




    /*    buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        // wywolujemy teraz metode na klikneicie przycisku zapisz

           updateTraining(, id);

            alertDialog.dismiss();
        }
        });
*/
    }

    @Override
    public void onClick(View view) {
        if (view == buttonUpdate) {
            updateTraining(quantity, id);

        }
    }

    private void updateTraining( int quantity, String id){

        if(quantity == 0 ){
            Toast.makeText(this, "Brak wolnych miejsc", Toast.LENGTH_LONG).show();
            return;
        }
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PowerBad");
        databaseReference.child(id).child("quantity").setValue(quantity-1);

        Toast.makeText(this, "Zapisane", Toast.LENGTH_LONG).show();

        //return true;
    }

}