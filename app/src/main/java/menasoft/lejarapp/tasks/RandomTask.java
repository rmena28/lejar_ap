package menasoft.lejarapp.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import menasoft.lejarapp.dto.Random;
import menasoft.lejarapp.utils.JsonParser;

/**
 * Created by Rmena on 10/31/2015.
 */
public class RandomTask extends AsyncTask<String, Void, Random>{

    Delegable delegable;

    public RandomTask(Delegable delegable) {
        this.delegable = delegable;
    }

    @Override
    protected Random doInBackground(String... params) {
        String url = params[0];
        String userId = params[1];
        String json = null;
        try {
            json = doGenerateRandomRequest(url,userId);
        } catch (IOException e) {
            Log.e("RandomTask","IO Exception " + e.getMessage(), e);
        }
        Random value = new Gson().fromJson(json, Random.class);
        return value;
    }

    @Override
    protected void onPreExecute() {
        delegable.processStart();
    }

    @Override
    protected void onCancelled() {
        delegable.processEnd();
    }

    @Override
    protected void onPostExecute(Random s) {
        delegable.processResult(s);
        delegable.processEnd();
    }

    public String doGenerateRandomRequest(String urlString,String userId) throws IOException {
        URL url = new URL(urlString);
        StringBuilder jsonString = new StringBuilder();
        //escape the double quotes in json string

        String payload="{\"user_id\":\""+userId +"\"}";
                //"{\"jsonrpc\":\"2.0\",\"method\":\"changeDetail\",\"params\":[{\"id\":11376}],\"id\":2}";

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        writer.write(payload);
        writer.close();
        InputStream is = null;
        try{
             is = connection.getInputStream();
            Scanner sc = new Scanner(connection.getInputStream());
            if(sc.hasNext()){
                jsonString.append(sc.nextLine());
            }
        }catch (Exception ex){
            Log.e("RandomTask",ex.getMessage(),ex);
            try{
                is = connection.getErrorStream();
                Scanner sc = new Scanner(is);
                if(sc.hasNext()){
                    jsonString.append(sc.nextLine());
                }
            }catch (Exception subEx) {
                Log.e("RandomTask",subEx.getMessage(),subEx);
            }

        }
        connection.disconnect();
        return jsonString.toString();
    }
}
