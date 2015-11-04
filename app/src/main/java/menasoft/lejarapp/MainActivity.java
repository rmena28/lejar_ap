package menasoft.lejarapp;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Type;

import menasoft.lejarapp.activities.LoginActivity;
import menasoft.lejarapp.adapters.SwipeAdapter;
import menasoft.lejarapp.fragments.BalanceFragment;
import menasoft.lejarapp.fragments.HistoryFragment;
import menasoft.lejarapp.fragments.RandomFragment;
import menasoft.lejarapp.utils.PreferencesUtil;

public class MainActivity extends AppCompatActivity{
    private DrawerLayout mDrawerLayout;
    private Fragment mFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adding User name to the Drawer header
        TextView drawerHeaderTextView = (TextView)findViewById(R.id.drawer_header_text_id);
        String name = (String) getPreference("name",Type.STRING);
        drawerHeaderTextView.setText(name);
        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        //If the fragment container is empty then add the default (random)
        if(getSupportFragmentManager().beginTransaction().isEmpty()){
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new RandomFragment()).commit();
        }
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.navigation_item_random:
                        mFragment = new RandomFragment();
                        setTitle("Random");
                        break;
                    case R.id.navigation_item_balance:
                        mFragment = new BalanceFragment();
                        setTitle("Balance");
                        break;
                    case R.id.navigation_item_history:
                        setTitle("History: " + PreferencesUtil.getPreference("name", PreferencesUtil.Type.STRING, getBaseContext()));
                        mFragment = new HistoryFragment();
                }
                menuItem.setChecked(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mFragment).commit();
                mDrawerLayout.closeDrawers();
                return true;
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id){
            case R.id.action_logout:
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("id");
                editor.remove("name");
                editor.remove("isUserApprover");
                editor.remove("isUserMaintenance");
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                return true;
            case R.id.action_exit:
                finish();
                return true;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Object getPreference(String key,Type type){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
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

    public void doGenerateRandom(View view) {
        Fragment fm = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        RandomFragment fragment = (RandomFragment)fm;
        fragment.onGenerateRandomButtonClick();
    }

    enum Type{
        BOOLEAN,INT,STRING,DOUBLE;
    }


}
