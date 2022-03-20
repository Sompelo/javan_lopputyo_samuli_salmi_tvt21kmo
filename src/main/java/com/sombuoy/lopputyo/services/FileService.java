package com.sombuoy.lopputyo.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sombuoy.lopputyo.components.Course;
import com.sombuoy.lopputyo.components.Student;

import org.springframework.stereotype.Service;

@Service
public class FileService                                            //Luokka tiedostojen kirjoittamiseen ja tiedostoista lukemiseen.
{
    public FileService()
    {
        System.out.println("File service constructor");
        
    }
    
    public void writeStudentInfoToFile(List<Student> students)      //Funktio kirjoittaa "students"-listan sisällön tiedostoon "/data/students.txt" JSON-muodossa.
    {
        try
        {
            FileWriter fw = new FileWriter(new File("./data/students.txt"));

            for (Student s : students)
            {
                fw.append(
                            "{\"studentId\" : "      + s.getStudentId() +
                            ", \"studentName\" : \""   + s.getStudentName() +
                            "\", \"studentEmail\" : \""  + s.getStudentEmail() +
                            "\", \"studentGroup\" : \""  + s.getStudentGroup() + "\"}\n");      //JSONia string-muodossa.

            }
            
            fw.close();
        }
        catch (IOException io)
        {
            System.out.println("An error occured when writing to file");
            io.printStackTrace();
        }

    }

    public void writeCourseInfoToFile(List<Course> courses)         //Funktio kirjoittaa "courses"-listan sisällön tiedostoon "/data/courses.txt" JSON-muodossa.
    {
        try
        {
            FileWriter fw = new FileWriter(new File("./data/courses.txt"));

            for (Course c : courses)
            {
                fw.append(
                            "{\"courseId\" : "         + c.getCourseId() +
                            ", \"courseName\" : \""    + c.getCourseName() +
                            "\", \"courseTeacher\" : \"" + c.getCourseTeacher() + "\"}\n");

            }

            fw.close();
        }
        catch (IOException io)
        {
            System.out.println("An error occured when writing to file");
            io.printStackTrace();
        }
    }

    public List<String> readStudentInfo()                           //Funktio opiskelijadatan lukemiseksi tiedostosta ohjelman alussa.
    {
        List<String> students = new ArrayList<>();
        try 
        {
            File myFile = new File("./data/students.txt");          //Tiedostopolku opiskelijalistalle
            Scanner readStudentScanner = new Scanner(myFile);

            while(readStudentScanner.hasNextLine())
            {
                students.add(readStudentScanner.nextLine());
                //System.out.println("students in while loop: " + students);
            }
            readStudentScanner.close();

            // System.out.println("students:" + students);

            
        } 
        catch (Exception e) 
        {
            System.out.println("Error reading file");
            e.printStackTrace();
        }

        return students;
        
    }

    public List<String> readCourseInfo()                            //Funktio kurssidatan lukemiseen tiedostosta ohjelman alussa.
    {
        List<String> courses = new ArrayList<>();
        try 
        {
            File myFile = new File("./data/courses.txt");
            Scanner readCourseScanner = new Scanner(myFile);

            while(readCourseScanner.hasNextLine())
            {
                courses.add(readCourseScanner.nextLine());
//                System.out.println("courses in while loop: " + courses);
            }
            readCourseScanner.close();

            // System.out.println("students:" + students);

            
        } 
        catch (Exception e) 
        {
            System.out.println("Error reading file");
            e.printStackTrace();
        }

        return courses;
    }

    


}
