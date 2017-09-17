package com.nicebin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LeaderBoardActivity extends AppCompatActivity {

    DatabaseHandler DB;
    ArrayList<String>[] group = new ArrayList[2];
    ArrayList<String> scores;
    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        listView = (ListView) findViewById(R.id.list);

        DB = new DatabaseHandler(this);

        group[0] = new ArrayList<>();group[1] = new ArrayList<>(); scores = new ArrayList();

        group = DB.getAllLeaders();


        for (int i =0; i<group[0].size(); i++){
            scores.add("Name: "+group[0].get(i)+"\nScore: "+group[1].get(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, scores);


        // Assign adapter to ListView
        listView.setAdapter(adapter);



    }
}
