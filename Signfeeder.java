package com.example.nabhiraj.airavat;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
public class Signfeeder extends FragmentActivity {
    Button insert,extra,filechooser;
    EditText singeditext;
    File Godpath=null;
    static String actualfile="result.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signfeeder);
        singeditext = (EditText) findViewById(R.id.sign_textviewid);
        insert = (Button) findViewById(R.id.insertsign_buttonid);
        extra = (Button) findViewById(R.id.extaid);
        filechooser= (Button)findViewById(R.id.selectfile);
        filechooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                startActivityForResult(intent,0);
            }
        });
        extra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("nanu","problem in button");
                int off = Integer.parseInt(singeditext.getText().toString());
                Log.d("nanu","pathmaking");
                String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                StringBuffer mystringBuffer = new StringBuffer("");
                Log.d("nanu","filehandleing");
                try {
                    FileInputStream fileInputStream = new FileInputStream(path + "/ISDL/"+actualfile);
                    int a = 0;
                    while ((a = fileInputStream.read()) != -1) {
                        mystringBuffer = mystringBuffer.append((char) a);
                    }
                    fileInputStream.close();
                    String mystring = mystringBuffer.toString();
                    // Toast.makeText(Signfeeder.this, "" + mystring, Toast.LENGTH_SHORT).show();
                    String target = "";
                    Log.d("nanu","error in main task");
                    for (int i=0;i<off;i++) {
                        target = target + mystring.charAt(mystring.length() - i-1);
                    }
                    Log.d("nanu","is there a error in toasting");
                    Toast.makeText(Signfeeder.this, "the string is as follow : "+nabhirajreverse(target), Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                File mydir = new File(path + "/ISDL");
                mydir.mkdir();
                StringBuffer mystringBuffer = new StringBuffer("");
                try {//path + "/ISDL/nan.txt"
                    FileInputStream fileInputStream = new FileInputStream(Godpath);
                    int a = 0;
                    while ((a = fileInputStream.read()) != -1) {
                        mystringBuffer = mystringBuffer.append((char) a);
                    }
                    fileInputStream.close();
                    String mystring = mystringBuffer.toString();
                    // Toast.makeText(Signfeeder.this, "" + mystring, Toast.LENGTH_SHORT).show();
                    //now we will enter the string in the image.
                    String text = singeditext.getText().toString();
                    int textlenght = text.length();
                    int imagelenght = mystring.length();

                    for (int i = 0; i < textlenght; i++) {
                        mystringBuffer.replace(imagelenght - textlenght - 1, imagelenght, text);
                    }
                    Toast.makeText(Signfeeder.this, "procedure finised  key to the image is   " + text.length(), Toast.LENGTH_LONG).show();
                    FileOutputStream fileOutputStream = new FileOutputStream(path + "/ISDL/"+actualfile);
                    for (int i = 0; i < mystringBuffer.length(); i++) {
                        fileOutputStream.write(mystringBuffer.charAt(i));
                    }
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    Toast.makeText(Signfeeder.this, "file not found exeption", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } catch (IOException e) {

                    e.printStackTrace();
                    Toast.makeText(Signfeeder.this, "inputoutput exeption taking place", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0&&resultCode==RESULT_OK){
            Uri uri=data.getData();
            String path = Environment.getExternalStorageDirectory().getAbsolutePath();
            // path=path + "/ISDL/nan.txt";
            //Log.d("nanu",""+path);
            //Log.d("nanu",""+uri.getPath());
            //Log.d("nanu",""+uri.toString());
            //Log.d("nanu","befor creation of file");
            File file=new File(getfilepathfromnabhiraj(uri.getPath()));
            //now we will save the pointer of the gile to godpath
            Godpath=file;
            Toast.makeText(Signfeeder.this,"Godpath set",Toast.LENGTH_SHORT).show();
        }
    }
    String getfilepathfromnabhiraj(String uripath){
        int i=1;
        while(uripath.charAt(i)!='/'){
            i++;
        }
        return uripath.substring(i+1);
    }
    String nabhirajreverse(String s){
        String temp="";
        for(int i=0;i<s.length();i++){
            temp=temp+s.charAt(s.length()-1-i);
        }
        return temp;
    }
}
