package com.shawntrz.homeworktool;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private ListView listView;

    private TextView mTextMessage;
    // static variables..
    String[] classNames = {"COMP 350: Software Engineering", "COMP 350L: Software Engineering Lab", "Other Class", "Other Class 2"};
    String[] classDescription = {"Computer science class...", "Lab section", "", ""};
    String[] classTime = {"Mon/Wed 11:00am", "Mon/Wed 1:30pm", "", ""};


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = (ListView) findViewById(R.id.home_listview);
        HomeListView homeListView = new HomeListView(this, classNames, classDescription, classTime);
        listView.setAdapter(homeListView);
//        mTextMessage = (TextView) findViewById(R.id.message);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void loadSettings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
    public void loadAddEvents(View view) {
        Intent intent = new Intent(this, AddEvents.class);
        startActivity(intent);
    }
    public void loadAddClasses(View view) {
        Intent intent = new Intent(this, AddCourses.class);
        startActivity(intent);
    }
    public void testHomework(View view) {
        Intent intent = new Intent(this, ClassDetails.class);
        intent.putExtra("courseID", -1);
        startActivity(intent);
    }

}
