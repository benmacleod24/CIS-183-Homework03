package com.benmacleod.userregistration_homework03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddEmployee extends AppCompatActivity
{
    Database database;

    // Intents
    Intent homeScreenIntent;

    // Inputs
    EditText inp_username;
    EditText inp_firstName;
    EditText inp_lastName;
    EditText inp_age;
    EditText inp_email;
    EditText inp_password;

    // Buttons
    Button btn_add_employee;
    Button btn_go_back;

    // Text
    TextView txt_username_validator;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        // Database Process
        database = new Database(this);

        homeScreenIntent = new Intent(AddEmployee.this, MainActivity.class);

        // Setup Processes
        setUpElements();
    }

    private void setUpElements()
    {
        inp_username = findViewById(R.id.txt_addEmp_username);
        inp_firstName = findViewById(R.id.txt_addEmp_firstName);
        inp_lastName = findViewById(R.id.txt_addEmp_lastName);
        inp_age = findViewById(R.id.txt_addEmp_age);
        inp_email = findViewById(R.id.txt_addEmp_email);
        inp_password = findViewById(R.id.txt_addEmp_password);
        btn_add_employee = findViewById(R.id.btn_addEmp_submit);
        txt_username_validator = findViewById(R.id.txt_addEmp_validator);
        btn_go_back = findViewById(R.id.btn_addEmp_back);

        onAddEmployeeSubmit();
        onGoBackClick();
        onUsernameChange();
    }

    private void onUsernameChange()
    {
        inp_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                boolean isValidUsername = database.isValidUsername(charSequence.toString().toLowerCase());

                if (isValidUsername)
                {
                    txt_username_validator.setTextColor(Color.GREEN);
                    txt_username_validator.setText("Valid");
                }
                else
                {
                    txt_username_validator.setTextColor(Color.RED);
                    txt_username_validator.setText("Not Valid");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private  void onGoBackClick()
    {
        btn_go_back.setOnClickListener(view -> {
            startActivity(homeScreenIntent);
        });
    }

    private void onAddEmployeeSubmit()
    {
        btn_add_employee.setOnClickListener(view -> {
            String u = inp_username.getText().toString().toLowerCase();
            String f = inp_firstName.getText().toString().toLowerCase();
            String l = inp_lastName.getText().toString().toLowerCase();
            String a = inp_age.getText().toString().toLowerCase();
            String e = inp_email.getText().toString().toLowerCase();
            String p = inp_password.getText().toString().toLowerCase();

            User tempUser = new User(u, f, l, e, p, a);
            database.createUser(tempUser);

            inp_username.setText("");
            inp_firstName.setText("");
            inp_lastName.setText("");
            inp_age.setText("");
            inp_email.setText("");
            inp_password.setText("");
            txt_username_validator.setText("");

        });
    }
}