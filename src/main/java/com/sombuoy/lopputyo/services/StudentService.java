package com.sombuoy.lopputyo.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.sombuoy.lopputyo.components.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService                                             //Luokka opiskelijatietojen hallintaan.
{
    @Autowired
    FileService myFileService;

    @Autowired
    Json myJsonService;                                                 //Json-olio JSON-muotoisen tiedon lukemiseen tiedostosta ohjelman käynnistyessä.

    public static List<Student> students = new ArrayList<>();           //Lista, johon ohjelma hakee käynnistyessään opiskelijoiden tiedot ja johon voi 
                                                                        //sitten lisätä uusia opiskelijoita.

    public StudentService() 
    {
        System.out.println("Student service constructor");
    }

    public Boolean addStudent(Student student)                          //Funktio opiskelijan lisäämistä varten.
    {
        if(students.add(student))
        {
            System.out.println("Lisääminen onnistui");
            myFileService.writeStudentInfoToFile(students);             //Uuden opiskelijan tiedot kirjoitetaan samalla "/data/students.txt"-tiedostoon.
            return true;
        }

        return false;
    }

    public List<Student> getStudents()                                  //Funktio opiskelijalistan hakemiseen.
    {
        System.out.println(students);
        return new ArrayList<>(students);
    }

    public Student getStudentById(int studentId)                        //Funktio opiskelijan hakemiseen id-numeron perusteella.
    {
        for (Student s : students) 
        {
            if (s.getStudentId() == studentId) 
            {
                return s;
            }
        }
        return null;
    }

    public Boolean addingCoursesToAStudent(int studentId, int courseId) //Funktio, jolla opiskelijan kurssilistaan lisätään kurssi. 
    {
        if (getStudentById(studentId).addCoursesToStudent(courseId)) 
        {
            return true;
        }
        return false;
    }

    public List<Integer> gettingStudentsCourses(int studentId)          //Funktio opiskelijan kurssilistan hakemiseen.
    {
        for (Student s : students) 
        {
            if (s.getStudentId() == studentId) 
            {
                return s.getStudentsCourses();
            }
        }
        List<Integer> nolist = new ArrayList<>();                       //Palautetaan tyhjä lista, jos tietoja ei pystytä hakemaan. 
        return nolist;
    }

    public Boolean readStudentInfoFromFile()                            //Funktio, jolla opiskelijan tiedot luetaan tiedostosta ("/data/student.exe").
    {                                                                   //Tämä toiminnallisuus aiheutti paljon päänvaivaa.
        try
        {
            FileService f = new FileService();
            List<Integer> idList = new ArrayList<>();
            // System.out.println("size: " + f.readStudentInfo().size());
            
            for(int i = 0; i < f.readStudentInfo().size(); i++)
            {
                JsonNode node = Json.parse(f.readStudentInfo().get(i));
                // System.out.println(node);
                // System.out.println("node.get int value: " + node.get("studentId").intValue()); 
                // System.out.println("node.get studentName as text: " + node.get("studentName"));
                if(students.add(new Student(node.get("studentId").intValue(), node.get("studentName").asText(), node.get("studentEmail").asText(), node.get("studentGroup").asText())))
                {
                    idList.add(node.get("studentId").intValue());
                    System.out.println("Student added succesfully");
                }
            }
            int suurin = 0;
            for (Integer integer : idList)                              //For-luuppi, joka käy läpi id-luvut "students.txt" tiedostosta ja palauttaa suurimman.
            {
                if(integer > suurin)
                {
                    suurin = integer;
                }
            }
            Student s = new Student();
            s.setCount(suurin + 1);                                     //Asetetaan opiskelijaluokan staattinen "count"-muuttujan arvo yhtä suuremmaksi, kuin
        }                                                               //"students.txt"-tiedostosta löydetty suurin id-arvo.
        catch(IOException e) 
        {

        }
        return false;
    }
}
