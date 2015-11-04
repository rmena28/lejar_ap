package menasoft.lejarapp.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import menasoft.lejarapp.dto.Dashboard;
import menasoft.lejarapp.utils.JsonParser;

/**
 * Created by Rmena on 10/29/2015.
 */
public class DashboardTask extends AsyncTask<String, Void, Dashboard>{
    private Delegable delegable;

    public void setDelegable(Delegable delegable) {
        this.delegable = delegable;
    }

    @Override
    protected Dashboard doInBackground(String... params) {
        Dashboard dashboard = null;
        try {
            String url = params[0];
            String json = JsonParser.toJson(url);
            Log.i("DashboardTask",json);
            dashboard = new Gson().fromJson(json,Dashboard.class);
        }catch (Exception ex){
            Log.e("DashboardTask","Error getting data", ex);
        }
        return dashboard;
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
    protected void onPostExecute(Dashboard dashboards) {
        delegable.processResult(dashboards);
        delegable.processEnd();
    }
}
