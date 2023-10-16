package com.example.databaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Update extends AppCompatActivity
{

    EditText et_j_u_fName;
    EditText et_j_u_lName;
    Button btn_j_u_update;
    Button btn_j_u_back;
    Intent mainActivity;
    DatabaseHelper dbHelper;
    User userPassed;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update);

        et_j_u_fName   = findViewById(R.id.et_v_u_fName);
        et_j_u_lName   = findViewById(R.id.et_v_u_lName);
        btn_j_u_update = findViewById(R.id.btn_v_u_update);
        btn_j_u_back   = findViewById(R.id.btn_v_u_back);

        mainActivity = new Intent(Update.this, MainActivity.class);

        Intent cameFrom = getIntent();

        userPassed = (User) cameFrom.getSerializableExtra("User");

        et_j_u_fName.setText(userPassed.getFname());
        et_j_u_lName.setText(userPassed.getLname());

        dbHelper = new DatabaseHelper(this);

        updateButtonEvent();
        backButtonEvent();
    }

    public void updateButtonEvent()
    {
        btn_j_u_update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //change the info about the user that was passed
                userPassed.setFname(et_j_u_fName.getText().toString());
                userPassed.setLname(et_j_u_lName.getText().toString());

                //pass new updated user to update
                dbHelper.updateUser(userPassed);

                //start main activity
                startActivity(mainActivity);
            }
        });
    }

    public void backButtonEvent()
    {
        btn_j_u_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(mainActivity);
            }
        });
    }
}