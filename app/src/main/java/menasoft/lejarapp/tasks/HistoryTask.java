package menasoft.lejarapp.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import menasoft.lejarapp.dto.Entry;
import menasoft.lejarapp.utils.JsonParser;

/**
 * Created by Rmena on 11/1/2015.
 */
public class HistoryTask extends AsyncTask<String, Void, Entry>{

    Delegable delegable;

    public HistoryTask(Delegable delegable) {
        this.delegable = delegable;
    }

    @Override
    protected Entry doInBackground(String... params) {
        Entry entries = null;
        try{
            String url = params[0];
            String json = JsonParser.toJson(url);
            Type collectionType = new TypeToken<Entry>(){}.getType();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
            entries = gson.fromJson(json, collectionType);
        }catch(Exception ex){
            Log.e("HistoryTask",ex.getMessage(), ex);
        }
        return entries;
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
    protected void onPostExecute(Entry entries) {
        delegable.processResult(entries);
    }
}
