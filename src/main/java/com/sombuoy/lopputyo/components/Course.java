package com.sombuoy.lopputyo.components;

import java.util.ArrayList;
import java.util.List;

public class Course 
{
    private String courseName;                                              //Muuttujien määritelmiä. 
    private String courseTeacher;
    private static int count = 0;                                           //Laskuri uusien kurssi-id-numeroiden määrittämiseen 
    private int courseId;
    private List<Integer> courseAttendees = new ArrayList<>();              //Lista, joka pitää sisällään kurssiin osallistuvien opiskelijoiden id-numerot

    public Course()                                                         //Ketjutettuja constructoreita
    {
        this("", ""); 
    }

public Course(String cName, String cTeacher)                                //Jos opiskelijaa luodessa ei määritellä id:numeroa, antaa ohjelma automaattisesti
    {                                                                       //seuraavan vapaan id:numeron
        this(count++, cName, cTeacher);
    }

    public Course(int id, String name, String teacher)                      //Kursseille voi myös manuaalisesti antaa id:numeron. Käytetään, kun kurssidata 
    {                                                                       //haetaan tiedostosta.
        System.out.println("Course constructor");
        this.courseId = id;
        this.courseName = name;
        this.courseTeacher = teacher;
    }

    public static void setCount(int newCount)                               //Funktio "count"-arvon manuaaliseen asettamiseen. Käytetään ohjelman alussa, kun
    {                                                                       //ohjelma hakee kurssitiedot tiedostosta, jotta ei tule päällekkäisiä id-numeroita. 
        count = newCount;
    }

    public Boolean addStudentToCourse(int studentId)                        //Funktio, jolla opiskelija voidaan sisällyttää kurssin opiskelijalistaan
    {
        if (courseAttendees.add(studentId)) 
        {
            System.out.println("Student added succesfully!");
            return true;
        }
        System.out.println("Adding a student failed");
        return false;
    }

    public String getCourseName()                                           //gettereitä ja settereitä
    {
        return this.courseName;
    }

    public void setCourseName(String courseName) 
    {
        this.courseName = courseName;
    }

    public String getCourseTeacher() 
    {
        return this.courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) 
    {
        this.courseTeacher = courseTeacher;
    }

    public int getCourseId() 
    {
        return this.courseId;
    }

    public void setCourseId(int courseId)
    {
        this.courseId = courseId;
    }

    public List<Integer> getCourseAttendees() 
    {
        return this.courseAttendees;
    }
}
