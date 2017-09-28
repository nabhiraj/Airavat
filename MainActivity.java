package com.example.nabhiraj.airavat;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {
    Button insertsig,freez,about,help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insertsig=(Button)findViewById(R.id.insertsignatureid);
        freez=(Button)findViewById(R.id.freezebuttonid);
        help=(Button)findViewById(R.id.help);
        about=(Button)findViewById(R.id.about);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Help.class);
                startActivity(intent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Aboutus.class);
                startActivity(intent);
            }
        });
        freez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,FreezerActivity.class);
                startActivity(i);
            }
        });
        insertsig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stagno=new Intent(MainActivity.this,Signfeeder.class);
                startActivity(stagno);

            }
        });
    }
}
