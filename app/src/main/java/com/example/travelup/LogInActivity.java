package com.example.travelup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {

    //Declarations

    LinearLayout linearLayout;
    AnimationDrawable animationDrawable;
    TextView tvNewAccount, tvForgotPassword;
    Button btnLogIn;
    TextInputEditText Email, Password;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private static final String TAG = "LogInActivity";
    final int REGISTER_ACTIVITY = 1;
    String fname = "",lname = "", number = "", city = "", email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);



        //TextInputEditText

        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);

        //This is for the background animation.

        linearLayout = findViewById(R.id.layout);
        animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        //TextView

        tvNewAccount = findViewById(R.id.tvNewAccount);

        String text = "Create your new account here";

        SpannableString ss = new SpannableString(text);
        ForegroundColorSpan fcBlue = new ForegroundColorSpan(Color.WHITE);
        ForegroundColorSpan fcBlack = new ForegroundColorSpan(Color.BLACK);
        ss.setSpan(fcBlack, 0,23, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(fcBlue, 23,28, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvNewAccount.setText(ss);

        tvNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivityForResult(intent, REGISTER_ACTIVITY);
            }
        });

        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        //Button

        btnLogIn = findViewById(R.id.btnLogIn);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if(email.isEmpty())
                {
                    Email.setError("Please enter your email again.");
                    Email.requestFocus();
                }
                else if (password.isEmpty())
                {
                    Password.setError("Please enter your email again.");
                    Password.requestFocus();
                }
                else if (!(email.isEmpty() && password.isEmpty()))
                {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(LogInActivity.this, "Log In error occured.", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(LogInActivity.this, "Error occured.", Toast.LENGTH_SHORT).show();
                }


            }
        });



        //Firebase

        mAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
                if (mFirebaseUser != null)
                {
                    Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                }
            }
        };

    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }
}
