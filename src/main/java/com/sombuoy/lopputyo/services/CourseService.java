package com.sombuoy.lopputyo.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.sombuoy.lopputyo.components.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService                                              //Luokka kurssitietojen hallintaan.
{
    @Autowired
    FileService myFileService;

    public static List<Course> courses = new ArrayList<>();             //Lista, johon haetaan "/data/courses.txt"-tiedostosta lista kursseista ja johon
                                                                        //uudet luodut kurssit lisätään.
    public CourseService() 
    {
        System.out.println("Course service constructor");
    }

    public Boolean addCourse(Course course)                             //Funktio kurssin lisäämiseen
    {     
        if (courses.add(course)) 
        {
            System.out.println("Lisääminen onnistui");
            myFileService.writeCourseInfoToFile(courses);               //Kurssin tiedot kirjoitetaan samalla tiedostoon "courses.txt".
            return true;
        }

        return false;
    }

    public List<Course> getCourses()                                    //Funktio kurssilistan hakemiseksi.
    {
        return new ArrayList<>(courses);
    }

    public Course getCoursesById(int courseId)                          //Funktio kurssin hakemiseen id-numeron perusteella.
    {
        for (Course c : courses) 
        {
            if (c.getCourseId() == courseId) 
            {
                return c;
            }
        }
        return null;
    }

    public Boolean addStudentToACourse(int courseId, int studentId)     //Funktio, jolla opiskelija voidaan lisätä kurssin osallistujalistaan.
    {
        if (getCoursesById(courseId).addStudentToCourse(studentId)) 
        {
            return true;
        }
        return false;
    }

    public List<Integer> gettingCourseAttendees(int courseId)           //Funktio, jolla haetaan kurssin osallistujalista.
    {
        for (Course c : courses) 
        {
            if (c.getCourseId() == courseId) 
            {
                return c.getCourseAttendees();
            }
        }
        List<Integer> nolist = new ArrayList<>();                       //Palautetaan tyhjä lista, jos tietoja ei löydy. 
        return nolist;
    }

    public Boolean readCourseInfoFromFile()                             //Funktio, jolla luetaan kurssilista tiedostosta ("/data/courses.txt") levylle
    {                                                                   //ohjelman käynnistyessä. 
        try
        {
            FileService f = new FileService();
            List<Integer> idList = new ArrayList<>();
            // System.out.println("size: " + f.readStudentInfo().size());
            
            for(int i = 0; i < f.readCourseInfo().size(); i++)          //Luuppi, jolla käydään tiedostosta tiedot läpi ja napataan ne mukaan kurssilistaan.
            {
                JsonNode node = Json.parse(f.readCourseInfo().get(i));
                // System.out.println(node);
                // System.out.println("node.get int value: " + node.get("courseId").intValue()); 
                // System.out.println("node.get studentName as text: " + node.get("courseName"));
                if(courses.add(new Course(node.get("courseId").intValue(), node.get("courseName").asText(), node.get("courseTeacher").asText())))
                {
                    idList.add(node.get("courseId").intValue());
                    System.out.println("Course added succesfully");
                }
            }

            int suurin = 0;
            for (Integer integer : idList)                              //Luuppi, jolla etsitään tiedostosta suurin kurssi-id-numero, joka tallennetaan, 
            {                                                           //inkrementoidaan ja asetetaan staattisen "count"-muuttujan uudeksi arvoksi, jottei 
                if(integer > suurin)                                    //syntyisi päällekkäisiä id-lukuja. 
                {
                    suurin = integer;
                }
            }
            Course c = new Course();
            c.setCount(suurin + 1);
        }
        catch(IOException e) 
        {

        }
        return false;
    }
}
