package com.example.mircea.proiectandroid.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Mircea on 16/1/2017.
 */

public class HttpClient {

    public String getDefaultList(String my_URL){
        HttpsURLConnection connection=null;
        InputStream inputStream=null;
        try{
            connection=(HttpsURLConnection)(new URL(my_URL)).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            //reading response
            StringBuffer stringBuffer=new StringBuffer();
            inputStream =connection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line=null;
            while((line=bufferedReader.readLine())!=null){
                stringBuffer.append((line+"\r\n"));
            }
            inputStream.close();
            connection.disconnect();
            return  stringBuffer.toString();

        }catch(IOException ioe){
            ioe.printStackTrace();
        }

        return null;
    }
}