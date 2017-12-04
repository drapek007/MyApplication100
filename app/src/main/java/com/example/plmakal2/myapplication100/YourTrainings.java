package com.example.plmakal2.myapplication100;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class YourTrainings extends AppCompatActivity {

    List<AddTraining> trainingList;
    Button buttonUpdate2;
    ListView listViewTraining;
    private FirebaseAuth firebaseAuth;
    DatabaseReference datababaseTraining, databaseReference, databaseReference2;
    String id, value;
    int quantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_trainings);


        listViewTraining = (ListView) findViewById(R.id.listViewTraining2);
        trainingList = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Zapisy");
        datababaseTraining = FirebaseDatabase.getInstance().getReference("Zapisy").child(user.getUid());

        listViewTraining.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AddTraining addTraining = trainingList.get(i);

                quantity=addTraining.getQuantity(); //zbedne
                //name = addTraining.getName();
                //id = addTraining.getDate();
                //place = addTraining.getPlace();
                showUpdateDialog(addTraining.getName(), addTraining.getPlace(), addTraining.getDate(), addTraining.getTime(), addTraining.getId(), addTraining.getParentname());

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


                    trainingList.add(addtraining);



                }

                TrainingList adapter = new TrainingList(YourTrainings.this, trainingList);
                listViewTraining.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showUpdateDialog(String name, String place, String date, int time, String id, String parentname){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.remove_dialog, null);

        dialogBuilder.setView(dialogView);

        final String id1 = id;
        final String parentname1 = parentname;
        final TextView textViewName = (TextView) dialogView.findViewById(R.id.textViewName3);
        final TextView textViewPlace = (TextView) dialogView.findViewById(R.id.textViewPlace3); // zostalo przekazane cale pola klasy add training: place date id
        final TextView textViewDate = (TextView) dialogView.findViewById(R.id.textViewDate3);   // show up dialog wywoluje nowe okienko w ktorym przekazane dane wyswietlamy
        final TextView textViewTime = (TextView) dialogView.findViewById(R.id.textViewTime3);   //oprocz oczywiscie klucza childa!

        final Button buttonUpdate2 = (Button) dialogView.findViewById(R.id.buttonUpdate2);
        //buttonUpdate2.setOnClickListener(this);
        dialogBuilder.setTitle("Szczegóły treningu:");

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        textViewName.setText(date);
        textViewPlace.setText("Adres: " + place);
        textViewDate.setText("Data: " + name);
        textViewTime.setText("Godzina: " + String.valueOf(time));

        FirebaseUser user3 = firebaseAuth.getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Zapisy").child(user3.getUid()).child(id);
        System.out.println("rakietka1 + " + value);
        ref.addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(DataSnapshot dataSnapshot){

                value = dataSnapshot.child("idtren").getValue(String.class); //This is a1

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


        buttonUpdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeTraining(id1, parentname1, value);
            }
        });
    }

 /*   @Override
    public void onClick(View view) {
        if (view == buttonUpdate2) {
            removeTraining(id);

        }
    }
*/
    private void removeTraining(String id, String parentname, String value) {
        FirebaseUser user3 = firebaseAuth.getCurrentUser();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Zapisy").child(user3.getUid()).child(id);

/*        ref.addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                System.out.println("rakietka3 + " + value);
                value = dataSnapshot.child("idtren").getValue(String.class); //This is a1
                System.out.println("rakietka4 + " + value);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

*/

        System.out.println("rakietka5 + " + user3.getUid());
        System.out.println("rakietka5 + " + id);

        datababaseTraining = FirebaseDatabase.getInstance().getReference("Zapisy").child(user3.getUid());
        DatabaseReference dr =datababaseTraining.child(id);
        dr.removeValue();

        Toast.makeText(this, "Wypisałeś się z treningu", Toast.LENGTH_LONG).show();
        System.out.println("rakietka6 + " + parentname);
        System.out.println("rakietka6 + " + value);
        System.out.println("rakietka6 + " + user3.getUid());
       databaseReference2 = FirebaseDatabase.getInstance().getReference(parentname).child(value);
        DatabaseReference dr2 = databaseReference2.child(user3.getUid());
        dr2.removeValue();



    }


}

