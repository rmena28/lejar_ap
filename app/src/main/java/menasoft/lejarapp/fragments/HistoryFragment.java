package menasoft.lejarapp.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import menasoft.lejarapp.R;
import menasoft.lejarapp.adapters.EntriesAdapter;
import menasoft.lejarapp.dto.Entry;
import menasoft.lejarapp.tasks.Delegable;
import menasoft.lejarapp.tasks.HistoryTask;
import menasoft.lejarapp.utils.PreferencesUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment implements Delegable {


    TextView historyTextView;
    private ListView mListView;
    ProgressDialog mDialog;


    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_history, container, false);
        mListView = (ListView) view.findViewById(R.id.list_view_history);
        String userId = (String) PreferencesUtil.getPreference("id", PreferencesUtil.Type.STRING, getContext());
        HistoryTask task = new HistoryTask(this);
        String path = "http://le-jar-service.herokuapp.com/entries/all/byUserId/"+userId;
        Log.i("HistoryFragment",path);
        task.execute(path);
        return view;
    }


    @Override
    public void processResult(Object o) {
        Entry entries = (Entry)o;
        EntriesAdapter entriesAdapter = new EntriesAdapter(getContext(), entries.results);
        mListView.setAdapter(entriesAdapter);
        processEnd();
    }

    @Override
    public void processStart() {
        mDialog = new ProgressDialog(getContext());
        mDialog.setTitle("Please wait");
        mDialog.setMessage("We are looking for your data");
        mDialog.show();
    }

    @Override
    public void processEnd() {
        if(mDialog!=null && mDialog.isShowing()){
            mDialog.dismiss();
        }
    }
}
