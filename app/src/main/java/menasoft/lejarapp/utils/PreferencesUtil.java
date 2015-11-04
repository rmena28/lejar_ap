package menasoft.lejarapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Rmena on 11/1/2015.
 */
public class PreferencesUtil {

    public enum Type{
        BOOLEAN,INT,STRING,DOUBLE;
    }

    public static Object getPreference(String key,Type type, Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        switch (type){
            case BOOLEAN:
                return preferences.getBoolean(key,false);
            case DOUBLE:

                break;
            case STRING:
                return preferences.getString(key,"");
        }
        return "";
    }

}
