package com.example.project_calendar.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.project_calendar.R;

public class RemoteFetch {

    String txtJson;
    private static String OPEN_WEATHER_MAP_API =
            "https://api.openweathermap.org/data/2.5/weather?q=";
    private static  String OPEN_WEATHER_MAP_API2 ="&appid=abc9c08bcfce13fc8d1c18999e40a00e&units=metric";
   // private static  String OPEN_WEATHER_MAP_API3 ="https://api.openweathermap.org/data/2.5/weather?q=middletown,pa,us&appid=abc9c08bcfce13fc8d1c18999e40a00e&units=metric";
   // private static final String OPEN_WEATHER_MAP_BY_ZIP="https://api.openweathermap.org/data/2.5/weather?zip={zip code},{country code}&appid={API key}";
    public static JSONObject getJSON(Context context, String city){
        try {
            System.out.println("city"+ city);
            //java.net.URL url = new java.net.URL(OPEN_WEATHER_MAP_API);
            String str=OPEN_WEATHER_MAP_API+city+OPEN_WEATHER_MAP_API2;
            URL url = new URL(OPEN_WEATHER_MAP_API+city+OPEN_WEATHER_MAP_API2);
            //URL url=new URL(OPEN_WEATHER_MAP_API3);
            System.out.println("URL:"+ url);
            System.out.println("Stringurl "+ str);
            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();
            //connection.addRequestProperty("x-api-key", "abc9c08bcfce13fc8d1c18999e40a00e");
            //connection.addRequestProperty("x-api-key",
              //      context.getString(R.string.open_weather_maps_app_id));
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp="";
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            // This value will be 404 if the request was not
            // successful
            if(data.getInt("cod") != 200){
                return null;
            }

            return data;
        }catch(Exception e){
            System.out.println("json exception "+ e);
            return null;
        }
    }



}