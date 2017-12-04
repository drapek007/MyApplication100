package com.example.plmakal2.myapplication100;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity implements View.OnClickListener {

    EditText e1;
    FirebaseAuth auth;
    ProgressDialog dialog;
    private Button buttonChange2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        e1 = (EditText) findViewById(R.id.editTextPassword);
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
        buttonChange2 = (Button) findViewById(R.id.buttonChange);

        buttonChange2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == buttonChange2){
            change();
        }
    }

    public void change()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            dialog.setMessage("Zmiana hasła, proszę czkeać");
            dialog.show();
           user.updatePassword(e1.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
               @Override
               public void onComplete(Task<Void> task) {
                   if(task.isSuccessful()){
                       dialog.dismiss();
                       Toast.makeText(getApplicationContext(),"Hasło zostało zmienione", Toast.LENGTH_LONG);
                       auth.signOut();
                       finish();
                       Intent i = new Intent(ChangePassword.this, MainActivity.class);
                       startActivity(i);
                   }
                   else
                   {
                       dialog.dismiss();
                       Toast.makeText(getApplicationContext(),"Zmiana hasła nie powiodła się", Toast.LENGTH_LONG);
                   }

               }
           });
        }
    }


}
