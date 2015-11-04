package menasoft.lejarapp.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import menasoft.lejarapp.R;
import menasoft.lejarapp.dto.Dashboard;
import menasoft.lejarapp.tasks.DashboardTask;
import menasoft.lejarapp.tasks.Delegable;
import menasoft.lejarapp.utils.MoneyUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class BalanceFragment extends Fragment implements Delegable {

    ProgressDialog loadingDialog;

    TextView mTotalBalancePaidTextView, mTotalBalanceUnpaidTextView, mTotalPersonalBalancePaidTextView,
            mTotalPersonalBalanceUnpaidTextView, mTodaysPersonalBalancePaidTextView, mTodaysPersonalBalanceUnpaidTextView;
    Dashboard mDashboard;
    View mView;


    public BalanceFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_balance, container, false);
        this.mView = view;
        DashboardTask task = new DashboardTask();
        task.setDelegable(this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String id = preferences.getString("id", "");
        task.execute(getString(R.string.rootPath) + "/entries/dashboard/byUserId/" + id);
        return view;
    }


    private void assignValues() {
        //assigning values
        mTotalBalancePaidTextView = (TextView) mView.findViewById(R.id.textview_total_balance_paid);
        mTotalBalanceUnpaidTextView = (TextView) mView.findViewById(R.id.textview_total_balance_unpaid);

        mTodaysPersonalBalancePaidTextView = (TextView) mView.findViewById(R.id.textview_todays_personal_balance_paid);
        mTodaysPersonalBalanceUnpaidTextView = (TextView) mView.findViewById(R.id.textview_todays_personal_balance_unpaid);

        mTotalPersonalBalancePaidTextView = (TextView) mView.findViewById(R.id.textview_total_personal_balance_paid);
        mTotalPersonalBalanceUnpaidTextView = (TextView) mView.findViewById(R.id.textview_total_personal_balance_unpaid);

        mTotalBalancePaidTextView.setText(MoneyUtils.toCurrency(mDashboard.total_balance_paid));
        mTotalBalanceUnpaidTextView.setText(MoneyUtils.toCurrency(mDashboard.total_balance_unpaid));
        mTodaysPersonalBalancePaidTextView.setText(MoneyUtils.toCurrency(mDashboard.todays_personal_balance_paid));
        mTodaysPersonalBalanceUnpaidTextView.setText(MoneyUtils.toCurrency(mDashboard.todays_personal_balance_unpaid));
        mTotalPersonalBalancePaidTextView.setText(MoneyUtils.toCurrency(mDashboard.total_personal_balance_paid));
        mTotalPersonalBalanceUnpaidTextView.setText(MoneyUtils.toCurrency(mDashboard.total_personal_balance_unpaid));
    }

    @Override
    public void processResult(Object o) {
        mDashboard = (Dashboard) o;
        Log.i("BalanceFragment", mDashboard.toString());
        assignValues();
    }

    @Override
    public void processStart() {
        Context c = mView.getContext();
        loadingDialog = ProgressDialog.show(c, "Loading", "Please wait", true);
    }

    @Override
    public void processEnd() {
        if (loadingDialog != null) {
            if (loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
        }
    }
}
