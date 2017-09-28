package com.example.nabhiraj.airavat;

import android.support.v4.app.FragmentActivity;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FreezerActivity extends FragmentActivity {
    EditText domain,portno;
    Button execute,stop,info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freezer);
        domain=(EditText)findViewById(R.id.domain_edittext);
        portno=(EditText)findViewById(R.id.portnumberid);
        execute=(Button)findViewById(R.id.attackbutton);
        stop=(Button)findViewById(R.id.stopthreadbuttonid);
        info=(Button)findViewById(R.id.info_connection);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FreezerActivity.this,"total number of theads : "+Domaifreezer.numberofthreads+" \n total num" +
                        "ber of connection" +
                        "are : "+Domaifreezer.numberofsockets,Toast.LENGTH_SHORT).show();
            }
        });
        execute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Domaifreezer.attackflag=true;
                Domaifreezer.ip=domain.getText().toString();
                Domaifreezer.portno=Integer.parseInt(portno.getText().toString());
                Domaifreezer.attack();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Domaifreezer.attackflag=false;
            }
        });


    }
}
