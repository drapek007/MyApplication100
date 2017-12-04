package com.example.plmakal2.myapplication100;

/**
 * Created by PLMAKAL2 on 2017-11-06.
 */


import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.TwitterAuthCredential;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private Button buttonRemove;
    private Button buttonMenu;
    private Button buttonChangePsswd, buttonTwojeTreningi;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private String userID;
    private boolean isSuperUser;

    private FirebaseAuth mAuth;

    private DatabaseReference databaseReference;

    public boolean superuser;
    public int counter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //initializing views
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonRemove = (Button) findViewById(R.id.button5);
        buttonMenu = (Button) findViewById(R.id.buttonMenu);
        buttonChangePsswd = (Button) findViewById(R.id.button3);
        buttonTwojeTreningi = (Button) findViewById(R.id.button4);
        //displaying logged in user name
        textViewUserEmail.setText("Witaj "+user.getEmail());

        //adding listener to button
        buttonLogout.setOnClickListener(this);
        buttonChangePsswd.setOnClickListener(this);
        buttonTwojeTreningi.setOnClickListener(this);
        buttonRemove.setOnClickListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");






        mAuth = FirebaseAuth.getInstance();

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        //myRef = mFirebaseDatabase.getReference();
        FirebaseUser user2 = mAuth.getCurrentUser();
        userID = user2.getUid();



    }

    public void saveUserInformation(){
        FirebaseUser user = firebaseAuth.getCurrentUser();


        //User user2 = new User(user.getEmail(), superuser, counter);


        //databaseReference.child(user.getUid()).setValue(user2);
        Toast.makeText(this,"Użytkownik dodany", Toast.LENGTH_LONG).show();
    }

    public void remove(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>(){
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
    }

    @Override
    public void onClick(View view) {
        //if logout is pressed
        if (view == buttonLogout) {
            //logging out the user

                firebaseAuth.signOut();
                //closing activity
                finish();
                //starting login activity
                startActivity(new Intent(this, LoginActivity.class));

        }
        if (view == buttonRemove) {

            remove();
            Toast.makeText(this,"Uzytkownik usuniety",Toast.LENGTH_LONG);

            finish();

        }
        if (view == buttonChangePsswd) {
            //logging out the user

            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, ChangePassword.class));
        }
        if (view == buttonTwojeTreningi) {
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

            if(isSuperUser == false) {
                finish();
                //starting login activity
                startActivity(new Intent(this, YourTrainings.class));
            }
            if(isSuperUser == true){
                Toast.makeText(this,"Nie jestes zwyklem uzytkownikiem",Toast.LENGTH_LONG);
            }

        }
    }
    public void MenuAct(View view){
        //saveUserInformation();
        startActivity(new Intent(this, Lista.class));
    }
    public void CreateUser(View view){
        saveUserInformation();
    }
}