package com.shawntrz.homeworktool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

public class ClassDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_details);
    }
    public void classModal(View view){
        PopupMenu popupMenu = new PopupMenu(this, findViewById(R.id.addButton));
        popupMenu.getMenuInflater().inflate(R.menu.class_modal, popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                String string = Integer.toString(id);
                if(R.id.add_events == id) {
                    addEvents();
                }
                else if(R.id.add_homework == id) {
                    addHomework();
                }
                else if(R.id.add_notes == id) {
                    addNotes();
                }
                return true;
            }
        });
    }
    public void addEvents(){
        this.findViewById(android.R.id.content);
        Intent intent = new Intent(this, AddEvents.class);
        startActivity(intent);
    }
    public void addHomework(){
        this.findViewById(android.R.id.content);
        Intent intent = new Intent(this, AddHomework.class);
        startActivity(intent);
    }
    public void addNotes(){
        this.findViewById(android.R.id.content);
        Intent intent = new Intent(this, AddNotes.class);
        startActivity(intent);
    }
}
