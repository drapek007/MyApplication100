package com.example.plmakal2.myapplication100;

/**
 * Created by PLMAKAL2 on 2017-11-06.
 */


import android.content.Intent;
import android.nfc.Tag;
import android.provider.ContactsContract;
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


public class BeforeProfileActivity extends AppCompatActivity implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView textViewUserEmail2;
    private EditText inputName;

    private Button buttonAdd;

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
        setContentView(R.layout.activity_before_profile);


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
        inputName = (EditText) findViewById(R.id.editText7);

        textViewUserEmail2 = (TextView) findViewById(R.id.textViewUserEmail2);

        //displaying logged in user name
        textViewUserEmail2.setText("Witaj "+user.getEmail());

        //adding listener to button
//        buttonLogout.setOnClickListener(this);
  //      buttonChangePsswd.setOnClickListener(this);
    //    buttonTwojeTreningi.setOnClickListener(this);
     //   buttonRemove.setOnClickListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");



        buttonAdd = (Button) findViewById(R.id.button7);



        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = inputName.getText().toString();
                saveUserInformation(name);
                Intent mIntent = new Intent(v.getContext(), ProfileActivity.class);
                startActivity(mIntent);

            }


        });

        mAuth = FirebaseAuth.getInstance();

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        //myRef = mFirebaseDatabase.getReference();
        FirebaseUser user2 = mAuth.getCurrentUser();
        userID = user2.getUid();



    }

    public void saveUserInformation(String name){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String name2 = name;
        User user2 = new User(user.getEmail(), superuser, counter, name);


        databaseReference.child(user.getUid()).setValue(user2);
        Toast.makeText(this,"Użytkownik dodany", Toast.LENGTH_LONG).show();
    }



    @Override
    public void onClick(View view) {
        //if logout is pressed



    }
    public void MenuAct(View view){
        //saveUserInformation();
        startActivity(new Intent(this, Lista.class));
    }
    //public void CreateUser(View view){
     //   saveUserInformation();
    //}
}