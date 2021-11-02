package com.example.rxcode.DayTwo.model;

import com.example.rxcode.DayTwo.bean.Course;
import com.example.rxcode.DayTwo.bean.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentModel {

    private List<Student> students;
    private static StudentModel sInstance;

    public static StudentModel getsInstance() {
        if (sInstance == null) {
            sInstance = new StudentModel();
        }
        return sInstance;
    }

    private StudentModel() {
        students = new ArrayList<>();
    }

    public void init() {
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            ArrayList<Course> courseArrayList = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                Course course = new Course();
                course.setCourseName("Course" + j);
                courseArrayList.add(course);
            }
            student.setName("Student" + i);
            student.setCourseList(courseArrayList);
            students.add(student);
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public void onDestroy() {
        sInstance = null;
        students.clear();
        students = null;
    }
}
