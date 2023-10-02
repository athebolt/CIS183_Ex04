package com.example.databaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    EditText et_j_fname;
    EditText et_j_lname;
    EditText et_j_uname;
    Button btn_j_addUser;
    ListView lv_j_users;
    ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_j_fname = findViewById(R.id.et_v_fName);
        et_j_lname = findViewById(R.id.et_v_lName);
        et_j_uname = findViewById(R.id.et_v_uName);
        btn_j_addUser = findViewById(R.id.btn_v_addUser);
        lv_j_users = findViewById(R.id.lv_v_users);

        userList = new ArrayList<User>();

        addNewUserButtonEvent();
    }

    public void addNewUserButtonEvent()
    {
        btn_j_addUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("Button pressed", "Add New User");

                String f = et_j_fname.getText().toString();
                String l = et_j_lname.getText().toString();
                String u = et_j_uname.getText().toString();

                User user = new User(f, l, u);

                addNewUser(user);

                displayUsers();

                //add to database
                //save to an array list
                //display in listview
            }
        });
    }

    public void addNewUser(User u)
    {
        userList.add(u);
    }

    public void displayUsers()
    {
        for (int i = 0; i < userList.size(); i++)
        {
            Log.d("User: ", userList.get(i).getFname());
        }
    }
}