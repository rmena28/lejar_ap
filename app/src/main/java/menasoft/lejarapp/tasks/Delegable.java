package menasoft.lejarapp.tasks;

/**
 * Created by Rmena on 10/29/2015.
 */
public interface Delegable {
    void processResult(Object o);
    void processStart();
    void processEnd();
}
