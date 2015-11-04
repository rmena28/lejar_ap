package menasoft.lejarapp.utils;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Rmena on 9/12/2015.
 */
public class JsonParser {

    public static String toJson(String httpUrl) {
        InputStream is = null;
        StringBuilder json = new StringBuilder();
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            is = connection.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Scanner sc = new Scanner(is);
            json.append(sc.nextLine());
            while (sc.hasNext()) {
                json.append(sc.nextLine());
            }
        } catch (Exception ex) {
            Log.e(JsonParser.class.getName(), ex.getMessage(), ex);
        }

        return json.toString();
    }
}
