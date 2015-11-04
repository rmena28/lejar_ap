package menasoft.lejarapp.tasks;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Rmena on 9/13/2015.
 */
public class CommonsTask {
    public static <T> List<T> jsonToList(String s, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(s, clazz);
        return Arrays.asList(arr);
    }

}
