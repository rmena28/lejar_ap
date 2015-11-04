package menasoft.lejarapp.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

import menasoft.lejarapp.MainActivity;
import menasoft.lejarapp.R;
import menasoft.lejarapp.adapters.UsersAdapter;
import menasoft.lejarapp.dto.Users;
import menasoft.lejarapp.tasks.Delegable;
import menasoft.lejarapp.tasks.UsersTask;

/**
 * Created by Rmena on 10/31/2015.
 */
public class LoginActivity extends Activity implements Delegable {
    ProgressDialog loadingDialog;
    Spinner mSpinner;
    List<Users> users;
    Users mSelectedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.mSpinner = (Spinner)findViewById(R.id.list_view_users);

        UsersTask task = new UsersTask(this);
        task.execute(getString(R.string.rootPath)+"/users/all");
    }

    @Override
    public void processResult(Object o) {
        this.users = (List<Users>)o;
        UsersAdapter adapter = new UsersAdapter(this,users);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedUser = (Users) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void processStart() {
        loadingDialog = ProgressDialog.show(this, "Loading", "Please wait", true);
    }

    @Override
    public void processEnd() {
        if(loadingDialog!=null) {
            if (loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
        }
    }

    public void doEnterApp(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
       SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name",mSelectedUser.first_name + " " + mSelectedUser.last_name);
        editor.putString("id", mSelectedUser._id);
        editor.putBoolean("isUserApprover", mSelectedUser.is_user_approver);
        editor.putBoolean("isUserMaintenance", mSelectedUser.is_user_maintenance);
        editor.apply();
        editor.commit();
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
