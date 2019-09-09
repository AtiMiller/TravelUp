package com.example.travelup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Declarations

    EditText etFName, etLName, etAddress1, etAddress2, etCity, etPhoneNumber, etEmail, etPassword,
    etRePassword;
    Button btnSignUp;
    FirebaseAuth mAuth;
    char c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Firebase

        mAuth = FirebaseAuth.getInstance();

        //EditText

        etFName = findViewById(R.id.etFName);
        etLName = findViewById(R.id.etLName);
        etAddress1 = findViewById(R.id.etAddress1);
        etAddress2 = findViewById(R.id.etAddress2);
        etCity = findViewById(R.id.etCity);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etRePassword = findViewById(R.id.etRePassword);

        final String password1 = etPassword.getText().toString();
        final String password2 = etRePassword.getText().toString();

        //Button

        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etFName.getText().toString().isEmpty() || etLName.getText().toString().isEmpty() || etAddress1.getText().toString().isEmpty()
                        || etCity.getText().toString().isEmpty() ||
                        etPhoneNumber.getText().toString().isEmpty() || etEmail.getText().toString().isEmpty() ||
                        etPassword.getText().toString().isEmpty() || etRePassword.getText().toString().isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
                }
                else if (password1.length()>6)
                {
                    Toast.makeText(RegisterActivity.this, "Your password is to short.", Toast.LENGTH_SHORT).show();
                }
                for (int i = 0; i < password1.length(); i++)
                {
                    c = password1.charAt(i);
                    if(!(c >= 'a' && c <= 'z'))
                    {
                        Toast.makeText(RegisterActivity.this, "Your password need at least one lowercase letter.", Toast.LENGTH_SHORT).show();
                    }
                    else if (!(c >= 'A' && c <= 'Z'))
                    {
                        Toast.makeText(RegisterActivity.this, "Your password need at least one capital letter.", Toast.LENGTH_SHORT).show();
                    }
                    else if (!(c >= '0' && c <= '9'))
                    {
                        Toast.makeText(RegisterActivity.this, "Your password need at least one number", Toast.LENGTH_SHORT).show();
                    }
                    else if (c >= '_' && c <= '@')
                    {
                        Toast.makeText(RegisterActivity.this, "Your password contains wrong symbols.", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Please re-enter your password.", Toast.LENGTH_SHORT).show();
                    }
                }

                if (password1.equals(password2))
                {
                    String email = etEmail.getText().toString().trim();
                    String password = etPassword.getText().toString().trim();

                    if (!(email.isEmpty() && password.isEmpty()))
                        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful())
                                {
                                    Toast.makeText(RegisterActivity.this, "Sign Up is unsuccessful, please try again.", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Intent intent = new Intent();
                                    intent.putExtra("fname", etFName.getText().toString().trim());
                                    intent.putExtra("lname", etLName.getText().toString().trim());
                                    intent.putExtra("city", etCity.getText().toString().trim());
                                    intent.putExtra("number", etPhoneNumber.getText().toString().trim());
                                    intent.putExtra("email", etEmail.getText().toString().trim());
                                    setResult(RESULT_OK, intent);
                                    RegisterActivity.this.finish();
                                }
                            }
                        });

                }
                else {
                    Toast.makeText(RegisterActivity.this, "The password is not matching.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //ScrollView

        ScrollView scroll = (ScrollView) this.findViewById(R.id.scroll);
        scroll.scrollTo(0, scroll.getBottom());

        //Countries Spinner

        Spinner spinnerc = findViewById(R.id.Countries);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Countries, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerc.setAdapter(adapter);
        spinnerc.setOnItemSelectedListener(this);

//        //Country Code Spinner
//
//        Spinner spinnercc = findViewById(R.id.CountryCode);
//        ArrayAdapter<CharSequence> adaptercc = ArrayAdapter.createFromResource(this, R.array.CountriesPhone, android.R.layout.simple_dropdown_item_1line);
//        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//        spinnercc.setAdapter(adaptercc);
//        spinnercc.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private void updateUI(FirebaseUser currentUser) {
        if(currentUser != null){
            startActivity(new Intent(this,MainActivity.class));
        }else {
            Toast.makeText(this,"U Didnt signed in",Toast.LENGTH_LONG).show();
        }
    }
}
