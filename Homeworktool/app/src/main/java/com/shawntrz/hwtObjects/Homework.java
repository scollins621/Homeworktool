package com.shawntrz.hwtObjects;

/**
 * Created by sean on 11/3/17.
 */

public class Homework {

    private String homeworkName;
    private String homeworkDueDate;
    private String homeworkPostDate;
    private int homeworkID;

    public Homework(String name, String dueDate, String postDate) {

        homeworkName = name;
        homeworkDueDate = dueDate;
        homeworkPostDate = postDate;
//        homeworkID = ??
    }

    public String getHomeworkName() {
        return homeworkName;
    }
    public int getHomeworkID() {
        return homeworkID;
    }
    public String getHomeworkDueDate() {
        return homeworkDueDate;
    }
    public String getHomeworkPostDate() {
        return homeworkPostDate;
    }
//    public void setHomeworkName(){}
//    public void setHomeworkID(){}
//    public void setHomeworkDueDate(){}
//    public void setHomeworkPostDate(){}




}
