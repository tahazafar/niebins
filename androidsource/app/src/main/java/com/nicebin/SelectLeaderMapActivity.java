package com.nicebin;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectLeaderMapActivity extends AppCompatActivity {

    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_leader_map);

        Button map = (Button) findViewById(R.id.map);
        Button leader = (Button) findViewById(R.id.leaderboard);
        Button visit = (Button) findViewById(R.id.visit);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(SelectLeaderMapActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });

        leader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(SelectLeaderMapActivity.this, LeaderBoardActivity.class);
                startActivity(i);
            }
        });

        visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ami-2017.github.io/NICE-bins/"));
                startActivity(browserIntent);
            }
        });
    }
}
