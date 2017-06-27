package com.example.cy.nicebins;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

public class RegisterActivity extends AppCompatActivity {

    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x001:
                    Toast.makeText(RegisterActivity.this, "success!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    RegisterActivity.this.startActivity(intent);
                    break;
                case 0x002:
                    Toast.makeText(RegisterActivity.this, "try again", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText etConfirm = (EditText) findViewById(R.id.etConfirm);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etVoicecode = (EditText) findViewById(R.id.etVoicecode);

        final Button bRegister = (Button) findViewById(R.id.bRegister);
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    public void run(){
                        String resp = PostUtils.RegisterByPost(etName.getText().toString(),etUsername.getText().toString(),etEmail.getText().toString(),
                                etPassword.getText().toString(),etConfirm.getText().toString(),etVoicecode.getText().toString());
                        if (resp.equals("success")){
                            handler.sendEmptyMessage(0x001);
                        }
                        else{
                            handler.sendEmptyMessage(0x002);
                        }
                    }
                }.start();
            }
        });
    }
}