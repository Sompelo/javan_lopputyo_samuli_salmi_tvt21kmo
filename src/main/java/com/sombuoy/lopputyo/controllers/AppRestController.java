package com.sombuoy.lopputyo.controllers;

import java.util.List;

import com.sombuoy.lopputyo.components.Course;
import com.sombuoy.lopputyo.components.Student;
import com.sombuoy.lopputyo.services.CourseService;
import com.sombuoy.lopputyo.services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppRestController 
{
    @Autowired
    StudentService myStudentService;

    @Autowired
    CourseService myCourseService;

    @Autowired
    StudentCourse myStudentCourse;                                  //Olio opiskelijat ja kurssit yhdistävästä luokasta. Tätä tarvitaan lisättäessä opiskelijalle 
                                                                    //kursseja tai kurssille opiskelijoita. 
    public AppRestController()
    {
        // myStudentService.readStudentInfoFromFile();
        System.out.println("AppRestCotroller constructor");
        
    }

    public void readDataFromFile()                                  //Funktio, joka ajetaan main-luokassa ohjelman alussa. Lukee tiedostoista opiskelija- 
    {                                                               //ja kurssidataa.
        StudentService s = new StudentService();
        CourseService c = new CourseService();
        s.readStudentInfoFromFile();
        c.readCourseInfoFromFile();
    }

    @GetMapping("students")
    public List<Student> getStudents()                              //Get-mappaus opiskelijalistauksen hakemiseen
    {
        return myStudentService.getStudents();
    }

@GetMapping("students/{stdid}")                                     //Get-mappaus tietyn opiskelijan hakemiseen muistista/tiedostosta
    public Student getIdOfStudent(@PathVariable("stdid") int id) 
    {
        return myStudentService.getStudentById(id);
    }

    @PostMapping("addstudent")                                      //POST-metodin mappaus addStudent-funktioon uuden opiskelijan lisäämiseksi.
    public String addStudent(@RequestBody Student student) 
    {
        if (myStudentService.addStudent(student) == true) 
        {
            return "Adding student successfull";
        }
        return "Adding student failed";
    }

    @GetMapping("courses")                                          //Get-mappaus kurssilistauksen hakemiseen.
    public List<Course> getCourse() 
    {
        return myCourseService.getCourses();
    }

    @GetMapping("courses/{crsid}")                                  //Get-mappaus tietyn kurssin hakemiseen muistista/tiedostosta.
    public Course getCourseById(@PathVariable("crsid") int id) 
    {
        return myCourseService.getCoursesById(id);
    }

    @PostMapping("addcourse")                                       //POST-metodin mappaus addCourse-funktioon uuden kurssin lisäämiseksi.
    public String addCourse(@RequestBody Course course) 
    {
        if (myCourseService.addCourse(course) == true) 
        {
            return "Added a course successfully";
        }
        return "Adding a course failed :(((";
    }

    @PostMapping("addstudenttocourse")
    public String addStudentToCourse(@RequestBody StudentCourse sc) //Post-mappaus opiskelijan lisäämiseksi kurssille
    {
        System.out.println(sc.getStudentId());
        System.out.println(sc.getCourseId());
        myCourseService.addStudentToACourse(sc.getCourseId(), sc.getStudentId());
        return "";
    }

    @GetMapping("getcourseattendees/{crssid}")                      //Get-mappaus, jolla haetaan kurssin osallistujien id-numerot
    public List<Integer> getCourseAttendees(@PathVariable("crssid") int courseId) 
    {
        return myCourseService.gettingCourseAttendees(courseId);
    }

    @PostMapping("addcoursestoastudent")                            //Post-mappaus kurssienlisäysopiskelijalle-funktioon
    public String addCoursesToAStudent(@RequestBody StudentCourse sc) 
    {
        System.out.println(sc.getStudentId());
        System.out.println(sc.getCourseId());
        myStudentService.addingCoursesToAStudent(sc.getStudentId(), sc.getCourseId());
        return "";
    }

    @GetMapping("getstudentscourses/{stdsid}")                      //Mappaus opiskelijan kurssien hakemiseen opiskelijan id-numeron perusteella. 
    public List<Integer> getStudentsCourses(@PathVariable("stdsid") int studentId) 
    {
        return myStudentService.gettingStudentsCourses(studentId);
    }
}
