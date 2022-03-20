package com.sombuoy.lopputyo.controllers;

import org.springframework.stereotype.Service;

@Service
public class StudentCourse                                      //Apuluokka tiettyjen opiskelijoita ja kursseja yhdistelevien metodien avuksi.
{
    private int studentId;
    private int courseId;

    StudentCourse() 
    {
        this(-1, -1);                                           //-1 id:t indikoimaan virheellistä id-numeroa. (Tälle ei nyt tullut mitään tarkastusta ohjelmaan)
    }

    StudentCourse(int studentId, int courseId)                  
    {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getStudentId()                                   //Settereitä ja gettereitä opiskelija- ja kurssi-id-lukuja varten
    {
        return this.studentId;
    }

    public void setStudentId(int studentId) 
    {
        this.studentId = studentId;
    }

    public int getCourseId() 
    {
        return this.courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
