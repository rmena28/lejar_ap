package menasoft.lejarapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import menasoft.lejarapp.MainActivity;

/**
 * Created by Rmena on 10/31/2015.
 */
public class LauncherActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("name", null);
        String id = preferences.getString("id",null);
        String role = preferences.getString("role", null);
        if(name==null){
            startActivity(new Intent(this, LoginActivity.class));
        }else{
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
    }
}
