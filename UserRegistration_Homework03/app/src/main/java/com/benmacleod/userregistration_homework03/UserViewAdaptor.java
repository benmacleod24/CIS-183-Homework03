package com.benmacleod.userregistration_homework03;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserViewAdaptor extends BaseAdapter
{
    Context ctx;
    ArrayList<User> users;

    public UserViewAdaptor()
    {}

    public UserViewAdaptor(Context ctx, ArrayList<User> users )
    {
        this.ctx = ctx;
        this.users = users;
    }

    // Get total size of list.
    @Override
    public int getCount() {
        return users.size();
    }

    // Get the user of the list item.
    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    // Get the id of the list item.
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            LayoutInflater inflator = (LayoutInflater) ctx.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflator.inflate(R.layout.layout_employee, null);
        }

        TextView name = view.findViewById(R.id.txt_layout_employee_name);
        TextView username = view.findViewById(R.id.txt_layout_employee_username);

        User user = users.get(i);
        name.setText(user.getFirstName() + " " + user.getLastName());
        username.setText(user.getUsername());

        return view;
    }
}
