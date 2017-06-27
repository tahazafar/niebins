package com.example.cy.nicebins;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x001:
                    Toast.makeText(LoginActivity.this, "login success!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,UserAreaActivity.class);
                    intent.putExtras(msg.getData());
                    LoginActivity.this.startActivity(intent);
                    break;
                case 0x002:
                    Toast.makeText(LoginActivity.this, "wrong username or password", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;

            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegister);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    public void run() {
                        Bundle resp = PostUtils.LoginByPost(etUsername.getText().toString(), etPassword.getText().toString());
                        if (!resp.isEmpty()) {
                            Message msg = new Message();
                            msg.what = 0x001;
                            msg.setData(resp);
                            handler.sendMessage(msg);
                        } else {
                            handler.sendEmptyMessage(0x002);
                        }
                    }
                }.start();
            }
        });
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
    }
}
