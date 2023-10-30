package com.benmacleod.userregistration_homework03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ViewEmployee extends AppCompatActivity {
    Database database;
    User user;

    // Intents
    Intent homeScreen;
    Intent updateIntent;

    // Elements
    TextView username;
    TextView email;
    TextView age;
    TextView name;
    TextView password;
    Button goBack;
    Button editUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);

        // Init a new database connection here.
        database = new Database(this);

        // Get the user from extras.
        user = getUserFromExtras();

        // Setup UI elements.
        username = findViewById(R.id.txt_emp_view_username);
        email = findViewById(R.id.txt_emp_view_email);
        name = findViewById(R.id.txt_emp_view_name);
        password = findViewById(R.id.txt_emp_view_password);
        age = findViewById(R.id.txt_emp_view_age);
        goBack = findViewById(R.id.btn_emp_view_back);
        editUser = findViewById(R.id.btn_emp_view_edit);

        // Listeners
        onGoBack();
        onEditUser();

        // Intents
        homeScreen = new Intent(ViewEmployee.this, MainActivity.class);
        updateIntent = new Intent(ViewEmployee.this, EditEmployee.class);

        // Display the user info to the ui.
        displayUser();


    }

    private void displayUser()
    {
        username.setText(user.getUsername());
        email.setText(user.getEmail());
        age.setText(user.getAge());
        name.setText(user.getLastName() + ", " + user.getFirstName());
        password.setText(user.getPassword());
    }

    private User getUserFromExtras()
    {
        // Get Intent & Bundle.
        Intent origin = getIntent();
        Bundle _bundle = origin.getExtras();

        // Get the username from the bundle.
        String _username = _bundle.getString("USERNAME");

        return database.getUser(_username);
    }

    private  void onGoBack()
    {
        goBack.setOnClickListener(view -> {
            startActivity(homeScreen);
        });
    }

    private void onEditUser()
    {
        editUser.setOnClickListener(view -> {
            updateIntent.putExtra("USERNAME", user.getUsername());
            startActivity(updateIntent);
        });
    }
}