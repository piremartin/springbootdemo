package com.chj.springbootdemo.plain.student;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Arrays;
import java.util.List;

/**
 * @author chehaojie
 */
public class StudentTest {
    public static void main(String[] args) throws Exception{
        Student stu[] = {new Student("张三",22,80f)
                ,new Student("李四",23,83f)
                ,new Student("王五",21,81f)};

        List<Student> list = Arrays.asList(stu);
//        list.sort((Student s1, Student s2)->s1.getAge()-s2.getAge());
//        list.sort((Student s1, Student s2)->{
//            if (s1.getScore()<s2.getScore()){
//                return -1;
//            }
//        });
//        System.out.println(list);
    }

}
