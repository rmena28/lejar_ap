package menasoft.lejarapp.fragments;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import menasoft.lejarapp.R;
import menasoft.lejarapp.dto.Random;
import menasoft.lejarapp.tasks.Delegable;
import menasoft.lejarapp.tasks.RandomTask;
import menasoft.lejarapp.utils.MoneyUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class RandomFragment extends Fragment implements Delegable {


    ProgressDialog mProgressDialog;
    TextView randomTextView;


    public RandomFragment() {
        // Required empty public constructor
    }

    public void onGenerateRandomButtonClick() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String userId = preferences.getString("id", "");
        String url = "http://le-jar-service.herokuapp.com/entries/add";
        RandomTask randomTask = new RandomTask(this);
        randomTask.execute(url, userId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_random, container, false);
        randomTextView = (TextView) view.findViewById(R.id.textview_random);
        randomTextView.setText("Random");
        return view;
    }


    @Override
    public void processResult(Object o) {
        Random random = (Random) o;
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        Snackbar snackBar = null;
        if (random.random_amount > 0) {

            snackBar = Snackbar.make(getActivity().findViewById(R.id.random_content_layout),"Your generated random amount was: " + MoneyUtils.toCurrency(random.random_amount), Snackbar.LENGTH_SHORT);

        }
        if(random.statusCode==404){
            snackBar = Snackbar.make(getActivity().findViewById(R.id.random_content_layout),random.error, Snackbar.LENGTH_SHORT);

        }
        if(snackBar!=null){
            snackBar.show();
        }
    }

    @Override
    public void processStart() {
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setTitle("Loading");
        mProgressDialog.setMessage("Generating random, please wait");
        mProgressDialog.show();
    }

    @Override
    public void processEnd() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
