package menasoft.lejarapp.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import menasoft.lejarapp.R;
import menasoft.lejarapp.dto.Entry;
import menasoft.lejarapp.utils.DateUtil;
import menasoft.lejarapp.utils.MoneyUtils;

/**
 * Created by Rmena on 11/1/2015.
 */
public class EntriesAdapter extends ArrayAdapter<Entry.Entries> {
    List<Entry.Entries> mList;
    public EntriesAdapter(Context context, List<Entry.Entries> list){
        super(context, R.layout.entries_list_layout, list);
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Entry.Entries getItem(int position) {
        return mList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view  = inflater.inflate(R.layout.entries_list_layout, parent, false);
        Entry.Entries entry = mList.get(position);

        TextView date = (TextView)view.findViewById(R.id.textview_history_date);
        TextView amount = (TextView)view.findViewById(R.id.textview__history_amount);
        TextView approveBy = (TextView)view.findViewById(R.id.textview__history_approved_by);
        TextView status = (TextView)view.findViewById(R.id.textview__history_status);
        date.setText(DateUtil.getFormattedDate(entry.entry_date));
        amount.setText(MoneyUtils.toCurrency(entry.amount));
        String approveByString = "Approved By: ";
        if(entry.approved_by==null || entry.approved_by.trim().isEmpty()){
            approveByString+="SYSTEM";
        }else{
            approveByString+=entry.approved_by;
        }
        approveBy.setText(approveByString);
        status.setText(entry.paid?"PAID":"UNPAID");
        if(entry.paid){
            date.setTextColor(ContextCompat.getColor(getContext(), R.color.positive_balance));
            amount.setTextColor(ContextCompat.getColor(getContext(), R.color.positive_balance));
            approveBy.setTextColor(ContextCompat.getColor(getContext(), R.color.positive_balance));
            status.setTextColor(ContextCompat.getColor(getContext(), R.color.positive_balance));
        }else{
            date.setTextColor(ContextCompat.getColor(getContext(), R.color.negative_balance));
            amount.setTextColor(ContextCompat.getColor(getContext(), R.color.negative_balance));
            approveBy.setTextColor(ContextCompat.getColor(getContext(), R.color.negative_balance));
            status.setTextColor(ContextCompat.getColor(getContext(), R.color.negative_balance));
        }

        return view;
    }
}
