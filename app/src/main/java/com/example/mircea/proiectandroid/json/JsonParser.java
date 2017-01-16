package com.example.mircea.proiectandroid.json;

import com.example.mircea.proiectandroid.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mircea on 16/1/2017.
 */

public class JsonParser {

    public String getQuote(String data){
        String s="";
        try{

            JSONObject jsonObject=new JSONObject(data);
        s= Util.getString("quote",jsonObject)+" - "+Util.getString("author",jsonObject);

        }catch (JSONException e){
            e.printStackTrace();
        }
        return s;
    }
}
