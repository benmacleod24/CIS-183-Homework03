package com.benmacleod.userregistration_homework03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditEmployee extends AppCompatActivity {
    Database database;
    User user;

    Intent userViewScreen;

    EditText inp_first_name;
    EditText inp_last_name;
    EditText inp_age;
    EditText inp_email;
    EditText inp_password;
    TextView txt_username;
    Button btn_save;
    Button btn_cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee);

        database = new Database(this);
        user = getUserFromExtras();

        userViewScreen = new Intent(EditEmployee.this, ViewEmployee.class);

        inp_first_name = findViewById(R.id.inp_emp_edit_first_name);
        inp_last_name = findViewById(R.id.inp_emp_edit_last_name);
        inp_age = findViewById(R.id.inp_emp_edit_age);
        inp_email = findViewById(R.id.inp_emp_edit_email);
        inp_password = findViewById(R.id.inp_emp_edit_password);
        btn_save = findViewById(R.id.btn_emp_edit_save);
        btn_cancel = findViewById(R.id.btn_emp_edit_cancel);
        txt_username = findViewById(R.id.txt_emp_view_username2);

        setUpInputs();
        onGoBack();
        onSave();
    }

    private void setUpInputs()
    {
        inp_first_name.setText(user.getFirstName());
        inp_last_name.setText(user.getLastName());
        inp_age.setText(user.getAge());
        inp_email.setText(user.getEmail());
        inp_password.setText(user.getPassword());
        txt_username.setText(user.getUsername());
    }

    private void onSave()
    {
        btn_save.setOnClickListener(view -> {
            String firstName = inp_first_name.getText().toString().toLowerCase();
            String lastName = inp_last_name.getText().toString().toLowerCase();
            String age = inp_age.getText().toString().toLowerCase();
            String email = inp_email.getText().toString().toLowerCase();
            String password = inp_password.getText().toString();

            User tempUser = new User(user.getUsername(), firstName, lastName, email, password, age);
            database.updateUser(tempUser);

            userViewScreen.putExtra("USERNAME", user.getUsername());
            startActivity(userViewScreen);
        });
    }

    private  void onGoBack()
    {
        btn_cancel.setOnClickListener(view -> {
            userViewScreen.putExtra("USERNAME", user.getUsername());
            startActivity(userViewScreen);
        });
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
}