package com.nicebin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    TextView signup;
    EditText email, password;
    Button login;
    Users DB;
    ArrayList<String> AllUsers;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if (sharedpreferences.getBoolean("login", false)){
            Intent i = new Intent(this, SelectLeaderMapActivity.class);
            startActivity(i);
            finish();
        }

        AllUsers = new ArrayList();
        DB = new Users(this);
        AllUsers = DB.getAllData();

        login = (Button) findViewById(R.id.login);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().equals("") || password.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, "Please enter correct details", Toast.LENGTH_LONG).show();
                }else {

                    for (int i=0; i<AllUsers.size(); i+=6){
                        if (email.getText().toString().equals(AllUsers.get(i+2)) && password.getText().toString().equals(AllUsers.get(i+4))) {
                            Intent ii =new Intent(LoginActivity.this, SelectLeaderMapActivity.class);
                            startActivity(ii);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putBoolean("login", true);
                            editor.commit();
                            finish();
                            break;
                        }else if(email.getText().toString().equals(AllUsers.get(i+3)) && password.getText().toString().equals(AllUsers.get(i+4))) {
                            Intent ii =new Intent(LoginActivity.this, SelectLeaderMapActivity.class);
                            startActivity(ii);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putBoolean("login", true);
                            editor.commit();

                            finish();
                            break;
                        }

                    }

                    email.setText("");
                    password.setText("");
                    email.setText("Wrong email or password");

                }
            }
        });

        signup = (TextView) findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
}
