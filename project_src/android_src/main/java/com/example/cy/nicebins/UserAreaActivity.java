package com.example.cy.nicebins;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.view.View;
import android.view.ViewGroup.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.TypedValue;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class UserAreaActivity extends AppCompatActivity {
    ArrayList<Integer> insert_ele_id = new ArrayList<>();
    String username = "";
    String points = "";
    String voicecode = "";
    ArrayList<String> posts = new ArrayList<>();
    final Handler comment_handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x001:
                    Toast.makeText(UserAreaActivity.this, "subimitted!", Toast.LENGTH_SHORT).show();

                    interpretJson(msg.getData().getString("content"));

                    LinearLayout parent = (LinearLayout) findViewById(R.id.LinearLayout);
                    for (int i = 0; i < insert_ele_id.size(); i++){
                        View inserted_view = findViewById(insert_ele_id.get(i));
                        if (null != inserted_view){
                            parent.removeView(inserted_view);
                        }
                    }

                    refreshUI();
                    break;
                case 0x002:
                    Toast.makeText(UserAreaActivity.this, "failed please try again", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;

            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        Bundle data = getIntent().getExtras();
        String s = data.getString("content");

        interpretJson(s);

        refreshUI();

        final Button btn_submit = (Button) findViewById(R.id.submit);
        final EditText comment = (EditText) findViewById(R.id.commentinput);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    public void run(){
                        Bundle resp = PostUtils.SubmitCommentByPost(username, comment.getText().toString());
                        if (!resp.isEmpty()) {
                            Message msg = new Message();
                            msg.what = 0x001;
                            msg.setData(resp);
                            comment_handler.sendMessage(msg);
                        } else {
                            comment_handler.sendEmptyMessage(0x002);
                        }
                    }
                }.start();
            }
        });
    }

    protected void interpretJson(String s){
        try{
            posts.clear();
            JSONObject jsonObject = new JSONObject(s);
            username = jsonObject.getString("username");
            points = jsonObject.getString("points");
            voicecode = jsonObject.getString("voicecode");
            JSONArray ja = jsonObject.getJSONArray("posts");
            for (int i = 0; i < ja.length(); i++){
                JSONObject jo = (JSONObject) ja.get(i);
                posts.add(jo.getString("username"));
                posts.add(jo.getString("comment"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(username);
    }

    protected void refreshUI(){
        insert_ele_id.clear();
        LinearLayout myLinear = (LinearLayout) findViewById(R.id.LinearLayout);
        TextView welcome = (TextView) findViewById(R.id.welcome);
        welcome.setTextColor(0xff76C50B);
        TextView current_username = (TextView) findViewById(R.id.username);
        current_username.setText("Username: "+username);
        TextView current_points = (TextView) findViewById(R.id.points);
        current_points.setText("Points: "+points);
        TextView current_voicecode = (TextView) findViewById(R.id.voicecode);
        current_voicecode.setText("Voice Code: "+voicecode);
        EditText comment_input = (EditText) findViewById(R.id.commentinput);
        Button butt = (Button) findViewById(R.id.submit);
        butt.setTextColor(0xffffffff);
        //butt.setBackgroundColor(0xff1E80A4);
        comment_input.setText("");
        int gen_id = 0;
        for (int i = 0; i < posts.size(); i+=2){
            TextView username = new TextView(this);
            username.setText(posts.get(i)+":");
            username.setTextColor(0xff987f23);
            username.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            gen_id = MyUtils.generateViewId();
            username.setId(gen_id);
            insert_ele_id.add(gen_id);

            TextView comment = new TextView(this);
            comment.setText(posts.get(i+1));
            comment.setTextColor(0xff983C23);
            comment.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            gen_id = MyUtils.generateViewId();
            comment.setId(gen_id);
            insert_ele_id.add(gen_id);

            View break_line = new View(this);
            break_line.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 10));
            //break_line.setBackgroundColor(0xff000000);
            gen_id = MyUtils.generateViewId();
            break_line.setId(gen_id);
            insert_ele_id.add(gen_id);

            myLinear.addView(username);
            myLinear.addView(comment);
            myLinear.addView(break_line);
        }
    }
}
