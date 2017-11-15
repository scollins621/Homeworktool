package com.shawntrz.hwtObjects;

import java.util.ArrayList;

/**
 * Created by sean on 11/3/17.
 */

public class Course extends com.shawntrz.homeworktool.Course{

    private String course_name;
    private String professor_name;
    private String course_number;
    private String course_section;
    private ArrayList<Homework> HomeworkList;
//    private ArrayList<Note> NoteList;

    public Course(String name, String prof, String number, String section) {
        course_name = name;
        professor_name = prof;
        course_number = number;
        course_section = section;
    }

//    public void setCourse_Details(){}
    public String getCourse_Name(){
        return course_name;
    }
    public String getProfessor_Name(){
        return professor_name;
    }
    public String getCourse_Number(){
        return course_number;
    }
    public String getCourse_Section(){
        return  course_section;
    }
    public void add_homework(Homework homework){
        HomeworkList.add(homework);
    }





//    public void add_notes(Note note){
//        NoteList.add(note);
//    }
//    public Homework getHomework(){}
//    public Note getNotes(){}
}
