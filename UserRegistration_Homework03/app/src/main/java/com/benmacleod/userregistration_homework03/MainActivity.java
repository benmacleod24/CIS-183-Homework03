package com.benmacleod.userregistration_homework03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Region;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    Database database;
    ArrayList<User> listOfUsers;
    UserViewAdaptor viewAdaptor;

    // Intents
    Intent addEmployeeIntent;
    Intent employeeViewIntent;

    // Buttons
    Button btn_add_employee;

    // ListView
    ListView lv_list_of_emps;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Database Processes
        database = new Database(this);
        database.initialize();

        // Setup list of users from the database.
        listOfUsers = database.getAllUsers();

        // Set up vars.
        setUpElements();
        setUpIntents();
    }

    // Setup any ui elements and linking.
    private void setUpElements()
    {
        btn_add_employee = findViewById(R.id.btn_home_add_employee);
        lv_list_of_emps = findViewById(R.id.lv_main_list_of_emps);

        // Listview Adaptor
        viewAdaptor = new UserViewAdaptor(this, listOfUsers);
        lv_list_of_emps.setAdapter(viewAdaptor);

        onAddEmployeeClick();
        onEmployeeLongHold();
        onEmployeeClick();
    }

    // Register and setup intents for switching.
    private void setUpIntents()
    {
        addEmployeeIntent = new Intent(MainActivity.this, AddEmployee.class);
        employeeViewIntent = new Intent(MainActivity.this, ViewEmployee.class);
    }

    // Listeners
    private void onAddEmployeeClick()
    {
        btn_add_employee.setOnClickListener(view -> {
            startActivity(addEmployeeIntent);
        });
    }

    private void onEmployeeLongHold()
    {
        lv_list_of_emps.setOnItemLongClickListener((v, view, i, l) ->
        {
            User user = listOfUsers.get(i);
            database.deleteUser(user.getUsername());
            listOfUsers.remove(i);
            viewAdaptor.notifyDataSetChanged();
            return  true;
        });
    }

    private  void onEmployeeClick()
    {
        lv_list_of_emps.setOnItemClickListener((adapterView, view, i, l) ->
        {
            User user = listOfUsers.get(i);
            employeeViewIntent.putExtra("USERNAME", user.getUsername());
            startActivity(employeeViewIntent);
        });
    }
}