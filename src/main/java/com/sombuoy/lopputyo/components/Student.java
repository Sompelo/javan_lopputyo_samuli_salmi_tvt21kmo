package com.sombuoy.lopputyo.components;

import java.util.ArrayList;
import java.util.List;

public class Student
{
    private String studentName;                                         //Muuttujien määrityksiä
    private String studentEmail;
    private String studentGroup;
    private static int count = 0;                                       //Laskuri opiskelijoiden id-luvuille
    private int studentId;
    private List<Integer> studentCourses = new ArrayList<>();           //Lista opiskelijan kursseista (kurssien id-luvut)

    public Student()                                                    //Ketjutettuja constructoreita
    {
        this("John Doe");
    }

    public Student(String name) 
    {
        this(name, "email", "Group Unknown");
    }

    public Student(String name, String email, String group)             //Kun uusi oppilas syötetään järjestelmään, luodaan sille uusi, uniikki id-numero
    {
        this(count++, name, email, group);
    }

    public Student(int id, String name, String email, String group)     //Id-numero voidaan kuitenkin myös asettaa manuaalisesti. Tämä siksi, jottei tulisi
    {                                                                   //päällekkäisiä id-numeroita, kun opiskelijatietoja haetaan tiedostosta ohjelman alussa.
        System.out.println("Student constructor");
        this.studentId = id;
        this.studentName = name;
        this.studentEmail = email;
        this.studentGroup = group;
        
    }

    public static void setCount(int newCount)                           //Funktio, jolla asetetaan "count"-muuttujan arvo (seuraava vapaa kokonaisluku).
    {
        count = newCount;
    }

    public Boolean addCoursesToStudent(int courseId)                    //Funktio, jolla opiskelijan "studentsCourses"-listalle lisätään kurssi
    {
        if (studentCourses.add(courseId)) 
        {
            System.out.println("Adding a course successful");
            return true;
        }
        System.out.println("Adding a course failed :(");
        return false;
    }

    public String getStudentName()                                      //Getterit ja setterit
    {
        return this.studentName;
    }

    public void setStudentName(String studentName) 
    {
        this.studentName = studentName;
    }

    public String getStudentEmail() 
    {
        return this.studentEmail;
    }

    public void getStudentEmail(String studentEmail) 
    {
        this.studentEmail = studentEmail;
    }

    public String getStudentGroup() 
    {
        return this.studentGroup;
    }

    public void setStudentGroup(String studentGroup) 
    {
        this.studentGroup = studentGroup;
    }

    public int getStudentId() 
    {
        return this.studentId;
    }

    public void setStudentId(int studentId)
    {
        this.studentId = studentId;
    }

    public List<Integer> getStudentsCourses() 
    {
        return this.studentCourses;
    }
}
