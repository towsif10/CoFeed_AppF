package com.example.cofeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText userEmail; // email text field initialisation.
    EditText userPassword; // password text field initialisation.
    Button loginButton; // the  login button initialisation.
    Button signButton;
    //ProgressBar progressBar; // progress bar initialisation.

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        userEmail = findViewById(R.id.Email_log);
        userPassword = findViewById(R.id.Password_log);
        loginButton = findViewById(R.id.Btn_log);
        signButton = findViewById(R.id.btn_init_sign);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailcheck = userEmail.getText().toString().trim();// storing the email to another string to compare and validate.
                String passwordcheck = userPassword.getText().toString().trim();
                // the below mentioned if block makes sure if the validation details mentioned are not empty and sends the text back asking for the email.
                if (TextUtils.isEmpty(emailcheck)) {
                    //email is empty
                    Toast.makeText(MainActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();

                    //stopping execution further
                    return;
                }
                // the below mentioned if segment checks if the password entered is not empty and then asks the user to enter the password.
                if (TextUtils.isEmpty(passwordcheck)) {
                    //password is empty
                    Toast.makeText(MainActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();

                }

                firebaseAuth.signInWithEmailAndPassword(userEmail.getText().toString(),
                        userPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    startActivity(new Intent(MainActivity.this, Homepage.class));
                                    userEmail.setText("");
                                    userPassword.setText("");

                                } else
                                {
                                    Toast.makeText(MainActivity.this, task.getException().getMessage()
                                            , Toast.LENGTH_LONG).show();

                                }
                            }
                        });
            }
        });

        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
        });
    }

}// End