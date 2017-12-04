package com.example.plmakal2.myapplication100;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetActivity extends AppCompatActivity {

    EditText editTextEmail; //to get email address

    Button buttonForgotPassword;    //button for click
    FirebaseAuth firebaseAuth;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        bindView();

        firebaseAuth = FirebaseAuth.getInstance();

    /*      click event of button
    * */
        buttonForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = editTextEmail.getText().toString();

            /*  check if email address is blank
            * */
                if (TextUtils.isEmpty(email)) {
                    editTextEmail.setError("Wpisz email");       //if blank display error
                }



            /*
            *       send request for reset password
            * */
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(ResetActivity.this, "Zresetowaliśmy twoje konto, sprawdź mail", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ResetActivity.this, "Błąd wysłania, nie ma takiego maila", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }


    private void bindView() {
        editTextEmail = (EditText) findViewById(R.id.activity_reset_password_edit_text);
        buttonForgotPassword = (Button) findViewById(R.id.activity_reset_password_forgot_password_button);
    }
}
