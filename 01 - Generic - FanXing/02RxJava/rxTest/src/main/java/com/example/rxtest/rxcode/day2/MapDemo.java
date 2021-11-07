package com.example.rxtest.rxcode.day2;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.rxtest.rxcode.day2.bean.Course;
import com.example.rxtest.rxcode.day2.bean.Student;
import com.example.rxtest.rxcode.day2.model.StudentModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


public class MapDemo {
    // 模拟网络数据的获取
    public static void testMapLikeDataFromNetwork() {
        // 初始化数据源0444
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

    @SuppressLint("CheckResult")
    public static void testMapInMap() {
        // 初始数据源
        StudentModel.getsInstance().init();

        Observable.fromIterable(StudentModel.getsInstance().getStudents())
                .map(new Function<Student, List<Course>>() {
                    @Override
                    public List<Course> apply(Student student) throws Exception {
                        return student.getCourseList();
                    }
                }).subscribe(new Consumer<List<Course>>() {
            @Override
            public void accept(List<Course> courses) throws Exception {
                Log.d("wwj", "==================");
                for (Course course : courses) {
                    Log.d("wwj", "课程：" + course.getCourseName());
                }
            }
        });
    }

    @SuppressLint("CheckResult")
    public static void testFlatMap() {
        StudentModel.getsInstance().init();
        Observable.fromIterable(StudentModel.getsInstance().getStudents())
                .flatMap(new Function<Student, ObservableSource<Course>>() {
                    @Override
                    public ObservableSource<Course> apply(Student student) throws Exception {
                        return Observable.fromIterable(student.getCourseList());
                    }
                }).subscribe(new Consumer<Course>() {
            @Override
            public void accept(Course o) throws Exception {
                Log.d("wwj", "课程:" + o.getCourseName());
            }
        });

    }

    public static void clearStudentList() {
        StudentModel.getsInstance().onDestroy();
    }
}
