package com.example.examplesqlite;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText nameEditText, detailEditText;
    Button save_button;
    ListView users_list_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameTextEdit);
        detailEditText = findViewById(R.id.detailTextEdit);
        save_button = findViewById(R.id.saveButton);
        //users_list_view =(ListView) findViewById(R.id.listView);

        MySQLiteHelper sqLiteHelper = new MySQLiteHelper(MainActivity.this);
        SQLiteHelper sqLiteHelper1 = new SQLiteHelper(MainActivity.this);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = nameEditText.getText().toString();
                String userDetail = detailEditText.getText().toString();
                //sqLiteHelper.insertUser(userName,userDetail);

                //sqLiteHelper1.insertUser(userName,userDetail);
                //displayData();
                displayDataFragment();
            }

            private void displayDataFragment() {
                Log.d("TAG", "displayDataFragment: ");
                FragmentManager supportFragmentManager =getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                DisplayFragment displayFragment = new DisplayFragment();
                fragmentTransaction.add(displayFragment,DisplayFragment.TAG);
                fragmentTransaction.commit();
            }

//            void displayData() {
//                Log.d("TAG", "displayData: ");
//                ArrayList<HashMap<String, String>> userList = sqLiteHelper1.getUsers();
//                ListAdapter adapter = new SimpleAdapter(MainActivity.this,userList,R.layout.list_display,new String[]{"name","details"},new int[]{R.id.name_display, R.id.detail_display});
//                users_list_view.setAdapter(adapter);
//
//            }
        });
    }
}
