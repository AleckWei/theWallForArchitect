package com.example.rxtest.rxcode.day2.bean;

import androidx.annotation.NonNull;

public class Course {

    private String courseName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @NonNull
    @Override
    public String toString() {
        return "Course { courseName: " + courseName + " }";
    }
}
