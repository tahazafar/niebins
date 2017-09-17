package com.nicebin;

import android.app.IntentService;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {
    EditText name, email, userName, password;

    Button signup;

    Users DB;
    ArrayList<String> allEmail;
    ArrayList<String> Allusernames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        allEmail = new ArrayList();
        Allusernames = new ArrayList();

        DB = new Users(this);
        allEmail.addAll(DB.getAllEmail());
        Allusernames.addAll(DB.getAllUserNames());
       // Toast.makeText(SignupActivity.this, "Lines: "+DB.numberOfRows(), Toast.LENGTH_LONG).show();

        signup =(Button) findViewById(R.id.done);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        userName = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userName.getText().toString().equals("") || name.getText().toString().equals("") ||
                        email.getText().toString().equals("") || password.getText().toString().equals("")){
                    Toast.makeText(SignupActivity.this, "Fill all the fields first", Toast.LENGTH_LONG).show();
                }else if (allEmail.contains(email.getText().toString()) ){
                    Toast.makeText(SignupActivity.this, "Email already exist", Toast.LENGTH_LONG).show();
                }else if (Allusernames.contains(userName.getText().toString()) ){
                    Toast.makeText(SignupActivity.this, "Username already exist", Toast.LENGTH_LONG).show();
                }else {
                    DB.insertContact(name.getText().toString(), userName.getText().toString()
                    , email.getText().toString(), password.getText().toString());

                    Intent i =new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}
