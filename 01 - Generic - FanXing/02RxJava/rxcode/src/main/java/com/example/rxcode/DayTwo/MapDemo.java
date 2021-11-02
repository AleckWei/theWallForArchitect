package com.example.rxcode.DayTwo;

import android.util.Log;

import com.example.rxcode.DayTwo.bean.Course;
import com.example.rxcode.DayTwo.bean.Student;
import com.example.rxcode.DayTwo.model.StudentModel;

import java.util.List;

public class MapDemo {
    // 模拟网络数据的获取
    public static void testMapLikeDataFromNetwork() {
        // 初始化数据源
        StudentModel.getsInstance().init();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 模仿网络请求获取学生列表数据
                List<Student> students = StudentModel.getsInstance().getStudents();

                for (Student student : students) {
                    List<Course> courseList = student.getCourseList();
                    Log.d("wwj", "========================");
                    for (Course course : courseList) {
                        Log.d("wwj：", student.getName() + " -- " + course.getCourseName());
                    }
                }
            }
        }).start();
    }

    public static void clearStudentList() {
        StudentModel.getsInstance().onDestroy();
    }
}
