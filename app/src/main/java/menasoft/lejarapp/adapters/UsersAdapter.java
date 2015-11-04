package menasoft.lejarapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import menasoft.lejarapp.R;
import menasoft.lejarapp.activities.LoginActivity;
import menasoft.lejarapp.dto.Users;

/**
 * Created by Rmena on 10/31/2015.
 */
public class UsersAdapter extends ArrayAdapter<Users> {
    List<Users> mUsers;
    Context mContext;
    LayoutInflater mInflater;

    public UsersAdapter(Context context, List<Users> objects) {
        super(context, R.layout.user_list_layout,objects);
        this.mUsers = objects;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.user_list_layout, parent, false);
        TextView label = (TextView) view.findViewById(R.id.spinner_text);
        Users currentUser = mUsers.get(position);
        label.setText(currentUser.first_name + " " + currentUser.last_name);
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.user_list_layout, parent, false);
        TextView label = (TextView) view.findViewById(R.id.spinner_text);
        Users currentUser = mUsers.get(position);
        label.setText(currentUser.first_name + " " + currentUser.last_name);
        return label;
    }
}
