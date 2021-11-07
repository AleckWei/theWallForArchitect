package com.example.rxtest.rxcode.day2.bean;

import java.util.List;

/**
 * 学生实体类
 */
public class Student {

    String name;
    List<Course> courseList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
