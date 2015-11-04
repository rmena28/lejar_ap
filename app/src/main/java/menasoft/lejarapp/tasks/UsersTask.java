package menasoft.lejarapp.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import menasoft.lejarapp.dto.Users;
import menasoft.lejarapp.utils.CollectionDeserializer;
import menasoft.lejarapp.utils.JsonParser;

/**
 * Created by Rmena on 10/31/2015.
 */
public class UsersTask extends AsyncTask<String,Void,List<Users>>{

    private Delegable delegable;

    public UsersTask(Delegable delegable) {
        this.delegable = delegable;
    }

    @Override
    protected List<Users> doInBackground(String... params) {
        String path = params[0];
        String json = JsonParser.toJson(path);
        Type collectionType = new TypeToken<List<Users>>(){}.getType();
        List<Users> users = new Gson().fromJson(json, collectionType);
        return users;
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
    protected void onPostExecute(List<Users> userses) {
        delegable.processResult(userses);
        delegable.processEnd();
    }
}
