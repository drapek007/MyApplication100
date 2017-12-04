
package com.example.plmakal2.myapplication100;

import android.content.Intent;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Activity22 extends AppCompatActivity implements View.OnClickListener{



    ListView listViewTraining;
    List<User> zapisyList;
    private boolean isSuperUser;
    private FirebaseAuth mAuth;
    int quantity=0;
    int time;
    String id, place, name, date, id2, parentname;
    List<AddTraining> trainingList;
    Button buttonUpdate;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userID, usermail;

    private FirebaseAuth firebaseAuth;

    DatabaseReference datababaseTraining, datababaseTraining2, databaseReference, databaseReference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_22);

        firebaseAuth = FirebaseAuth.getInstance();

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        datababaseTraining = FirebaseDatabase.getInstance().getReference("ABRMa");


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        listViewTraining = (ListView) findViewById(R.id.listViewTraining);
        trainingList = new ArrayList<>();
        zapisyList = new ArrayList<>();

        mFirebaseDatabase.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user2 = dataSnapshot.getValue(User.class);
                isSuperUser = user2.superuser;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        listViewTraining.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AddTraining addTraining = trainingList.get(i);


                quantity = addTraining.getQuantity();
                name = addTraining.getName();
                date = addTraining.getDate();
                place = addTraining.getPlace();
                id2 = addTraining.getId();
                parentname = addTraining.getId();

                if (isSuperUser == false) {
                    showUpdateDialog(addTraining.getName(), addTraining.getPlace(), addTraining.getDate(), addTraining.getTime(), addTraining.getQuantity(), addTraining.getId(), addTraining.getParentname());
                }
                if (isSuperUser == true)  {
                    showUpdateDialog2(addTraining.getName(), addTraining.getPlace(), addTraining.getDate(), addTraining.getTime(), addTraining.getQuantity(), addTraining.getId());
                }

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

                TrainingList adapter = new TrainingList(Activity22.this, trainingList);
                listViewTraining.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showUpdateDialog(String name, String place, String date, int time, int quantity, String id, final String parentname){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.update_dialog, null);

        dialogBuilder.setView(dialogView);

        final TextView textViewName = (TextView) dialogView.findViewById(R.id.textViewName2);
        final TextView textViewPlace = (TextView) dialogView.findViewById(R.id.textViewPlace2); // zostalo przekazane cale pola klasy add training: place date id
        final TextView textViewDate = (TextView) dialogView.findViewById(R.id.textViewDate2);   // show up dialog wywoluje nowe okienko w ktorym przekazane dane wyswietlamy
        final TextView textViewTime = (TextView) dialogView.findViewById(R.id.textViewTime2);   //oprocz oczywiscie klucza childa!
        final TextView textViewQuantity = (TextView) dialogView.findViewById(R.id.textViewQuantity2);
        final String id3 = id;
        buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdate);
        //buttonUpdate.setOnClickListener(this);
        dialogBuilder.setTitle("Szczegóły treningu:");

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        textViewName.setText(name);
        textViewPlace.setText("Adres: " + place);
        textViewDate.setText("Data: " + date);
        textViewTime.setText("Godzina: " + String.valueOf(time));
        textViewQuantity.setText("Ilosc wolnych miejsc: " + String.valueOf(quantity));

        final int quantity2 = quantity;
        final int time2 = time;
        final String name2 = name;
        final String place2 = place;
        final String date2 = date;
        final String parentname2 = parentname;

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTraining(quantity2, id3);

                createListUserTraining(date2, name2, place2, time2, id3, parentname2);

            }
        });


    }

    private void showUpdateDialog2(String name, String place, String date, int time, int quantity, String id){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.update_dialog2, null);

        dialogBuilder.setView(dialogView);
        datababaseTraining2 = FirebaseDatabase.getInstance().getReference("ABRM");
        datababaseTraining2.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                zapisyList.clear();

                for(DataSnapshot zapisSnapshot : dataSnapshot.getChildren()){

                    User user3 = zapisSnapshot.getValue(User.class);
                    System.out.println("kupa + " + user3.getEmail());


                    zapisyList.add(user3);


                }

                final TextView textView1 = (TextView) dialogView.findViewById(R.id.textView1);
                final TextView textView2 = (TextView) dialogView.findViewById(R.id.textView2);
                final TextView textView3 = (TextView) dialogView.findViewById(R.id.textView3);
                final TextView textView4 = (TextView) dialogView.findViewById(R.id.textView4);
                final TextView textView5 = (TextView) dialogView.findViewById(R.id.textView5);
                final TextView textView6 = (TextView) dialogView.findViewById(R.id.textView6);
                final TextView textView7 = (TextView) dialogView.findViewById(R.id.textView7);

                try{
                    textView1.setText(zapisyList.get(0).getEmail());
                    textView2.setText(zapisyList.get(1).getEmail());
                    textView3.setText(zapisyList.get(2).getEmail());
                    textView4.setText(zapisyList.get(3).getEmail());
                    textView5.setText(zapisyList.get(4).getEmail());
                    textView6.setText(zapisyList.get(5).getEmail());
                    textView7.setText(zapisyList.get(6).getEmail());

                }catch(Exception e){
                }
                //textView4.setText(zapisyList.get(3).getEmail());
                TrainingList adapter = new TrainingList(Activity22.this, trainingList);
                listViewTraining.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });


        //final TextView textView1 = (TextView) dialogView.findViewById(R.id.textView1);
        //final TextView textView2 = (TextView) dialogView.findViewById(R.id.textView2);
        //final TextView textView3 = (TextView) dialogView.findViewById(R.id.textView3);
        //textView1.setText(zapisyList.get(0).getEmail());
        //textView2.setText(zapisyList.get(1).getEmail());
        //textView3.setText(zapisyList.get(2).getEmail());
        // textView1.setText(zapisyList.get(3).getEmail());
        //textView1.setText(zapisyList.get(4).getEmail());
        //textView1.setText(zapisyList.get(5).getEmail());
        dialogBuilder.setTitle("Lista uczestników");

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();



    }



    @Override
    public void onClick(View view) {
        //if (view == buttonUpdate) {

        //updateTraining(quantity, id3);

        //createListUserTraining(date, name, place, time, id3);

        // }
    }

    private void updateTraining(int quantity, String id){

        if(quantity == 0 ){
            Toast.makeText(this, "Brak wolnych miejsc", Toast.LENGTH_LONG).show();
            return;
        }

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ABRMa");
        databaseReference.child(id).child("quantity").setValue(quantity-1);

        System.out.println("pyszczek " + id);

        Toast.makeText(this, "Zapisane", Toast.LENGTH_LONG).show();

        //return true;
    }

    private void createListUserTraining(String date, String name, String place, int time, String id, String parentname){

        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Zapisy");
        String trainingID = databaseReference.push().getKey();

        databaseReference.child(user.getUid()).child(trainingID).child("name").setValue(name);
        databaseReference.child(user.getUid()).child(trainingID).child("place").setValue(place);
        databaseReference.child(user.getUid()).child(trainingID).child("date").setValue(date);
        databaseReference.child(user.getUid()).child(trainingID).child("time").setValue(time);
        databaseReference.child(user.getUid()).child(trainingID).child("id").setValue(trainingID);
        databaseReference.child(user.getUid()).child(trainingID).child("idtren").setValue(id);
        databaseReference.child(user.getUid()).child(trainingID).child("parentname").setValue(parentname);

        System.out.println("pyszczek " + id);


        /////////////////////////////////////////////////////////////////////////////////////
        // W tym momencie tworze wpis kolejnej listy

        databaseReference2 = FirebaseDatabase.getInstance().getReference("ABRM");
        databaseReference2.child(id).child(user.getUid()).child("email").setValue(user.getEmail());


    }

}