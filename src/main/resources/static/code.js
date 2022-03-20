let list = document.getElementById("ulLeft");                       //Määritelmät listoille, joille erinäisten hakujen tulokset tulostuvat.
let list2 = document.getElementById("ulRight");

document.getElementById("addStudent")                               //Funktio opiskelijan lisäämiseksi "tietokantaan" (Tässä ohjelmassa ei käytetä tietokantaa, 
.addEventListener("click", e =>                                     //vaan tiedot tallentuvat laitteen muistiin, sekä tiedostoksi "/lopputyö/data/"-kansioon).
{                                                                   //EventListener tarkkailee "addStudent"-id:isen napin toimintaa ja aktivoituu tätä klikattaessa.
    e.preventDefault()

    let student =                                                   //Opiskelijan tiedot muodostetaan olioksi html-formin datan pohjalta
    {
        studentName : document.getElementById("sname").value,
        studentEmail : document.getElementById("semail").value,
        studentGroup : document.getElementById("sgroup").value
    }

    fetch("http://localhost:8080/addstudent",                       //Tässä osassa tehdään POST-HTTP kutsu osoitteeseen, jonka body:ssä lähetetään olion tiedot
    {                                                               //JSON-muodossa.
        headers: 
        {
            'Accept' : 'application/json',
            'Content-type' : 'application/json'
        },
        method: "POST",
        body: JSON.stringify(student)
    })
    .then //(console.log("Student added succesfully"));
    document.getElementById("myStudentForm").reset();               //Tyhjentää kyseisen formin tekstikenttien sisällöt
})

document.getElementById("getStudents")                      
.addEventListener("click", e =>
{
    e.preventDefault()

    getStudents();
})

function getStudents()                                              //Toiminnallisuus, jolla listataan kaikki opiskelijat tietokannasta
{
    list.innerHTML = "";

    fetch("http://localhost:8080/students")
    .then(response => response.json())
    .then(data =>
    {
        data.forEach(i =>                                           //Jokaista muistista löydettyä oppilasta kohden muodostetaan oma <li>-elementti 
        {                                                           //johon parsitaan JSON-datasta opiskelijan tiedot String-muotoon
            let li = document.createElement("li")

            li.innerText = 
                "Student id: " + i.studentId + " | " +
                "Student name: " + i.studentName + " | " + 
                "Student e-mail: " + i.studentEmail + " | " + 
                "Student group: " + i.studentGroup;
            list.appendChild(li)
        })
    })
}

document.getElementById("addCourse")                                //Toiminnallisuus kurssin lisäämiseen "tietokantaan"
.addEventListener("click", e => 
{
    e.preventDefault();

    let course = 
    {
        courseName : document.getElementById("cname").value,
        courseTeacher : document.getElementById("cteach").value
    }

    fetch("http://localhost:8080/addcourse",
    {
        headers: 
        {
            'Accept' : 'application/json',
            'Content-type' : 'application/json'
        },
        method: "POST",
        body: JSON.stringify(course)
    })
    .then//(console.log("Course added succesfully"));
    document.getElementById("myCourseForm").reset();
})

document.getElementById("getCourses")                           //Toiminnallisuus kaikkien kurssien listaamiseen tietokannasta
.addEventListener("click", e => 
{
    e.preventDefault();

    getCourses();
})

function getCourses()
{
    list.innerHTML = "";

    fetch("http://localhost:8080/courses")
    .then(response => response.json())
    .then(data =>
    {
        data.forEach(i => 
        {
            let li = document.createElement("li")

            li.innerText =  "Course id: " + i.courseId + 
                            " | Course name: " + i.courseName + 
                            " | Course teacher: " + i.courseTeacher;
            list.appendChild(li)
        })
    })
}

document.getElementById("getStudentById")                       //Toiminnallisuus, jolla voidaan hakea tiettyä oppilasta id-numeron perusteella
.addEventListener("click", e => 
{
    e.preventDefault();
    getStudentsById();
})

function getStudentsById()
{
    list.innerHTML = "";

    let id = document.getElementById("stdid");

    if(id.value !== '') {
        console.log("id.value: " + id.value);
        fetch("http://localhost:8080/students/" + id.value)
        .then(response => response.json())
        .then(data =>
        {
            let li = document.createElement("li")

            li.innerText = "Student name: " + data.studentName + " | Student email: " + data.studentEmail + " | Student group: " + data.studentGroup;
            list.appendChild(li)
        })
    }
    document.getElementById("myGetStudentByIdForm").reset();
    return;
}

document.getElementById("getCourseById")                        //Toiminnallisuus, jolla voidaan hakea tiettyä kurssia id-numeron perusteella
.addEventListener("click", e => 
{
    e.preventDefault();
    getCourseById();
})

function getCourseById()
{
    list.innerHTML = "";

    let id = document.getElementById("crsid");

    if(id.value !== '') {
        fetch("http://localhost:8080/courses/" + id.value)
        .then(response => response.json())
        .then(data =>
        {
            console.log(data)
            let li = document.createElement("li")

                li.innerText = "Course name: " + data.courseName + " | Course teacher: " + data.courseTeacher;
                list.appendChild(li)
        })
    }
    document.getElementById("myGetCourseByIdForm").reset();
    return;
}

document.getElementById("addStudentToACourse")                  //Funtio, jolla lisätään opiskelija kurssin osallistujaksi "courseAttendees"-listaan
.addEventListener("click",e => {
    e.preventDefault();
    addStudentToCourse();
})

function addStudentToCourse() {

    let course = 
    {
        courseId : document.getElementById("addscid").value,
        studentId : document.getElementById("addssid").value
    }

    fetch("http://localhost:8080/addstudenttocourse",
    {
        headers: 
        {
            'Accept' : 'application/json',
            'Content-type' : 'application/json'
        },
        method: "POST",
        body: JSON.stringify(course)
    })
    .then (console.log("Student added succesfully on the course"));
    document.getElementById("addingStudentToCourseForm").reset();
}

document.getElementById("addCoursetoStudent")                   //Funktio, jolla lisätään kurssi opiskelijan kurssilistaan "studentsCourses"
.addEventListener("click",e => {
    e.preventDefault();
    addCourseToStudent();
})

function addCourseToStudent() {

    let student = 
    {
        studentId : document.getElementById("addcsid").value,
        courseId : document.getElementById("addccid").value,
    }

    //console.log(student);

    fetch("http://localhost:8080/addcoursestoastudent",
    {
        headers: 
        {
            'Accept' : 'application/json',
            'Content-type' : 'application/json'
        },
        method: "POST",
        body: JSON.stringify(student)
    })
    .then (console.log("Course added succesfully to student"));
    document.getElementById("addingCourseToStudentForm").reset();
}

document.getElementById("getCourseAttendees")                   //Tällä voidaan hakea kurssin osallistujalista
.addEventListener("click", e => 
{
    e.preventDefault();
    getCourseAttendeesById();
})

function getCourseAttendeesById()
{
    list2.innerHTML = "";

    let id = document.getElementById("crssid");

    if(id.value !== '') {
        fetch("http://localhost:8080/getcourseattendees/" + id.value)       //Ensiksi haetaan kurssin osallistujalista.
        .then(response => response.json())
        .then(data =>
        {
//            console.log("data" + data)
            
            data.forEach(i => {                                             //Kurssin osallistujat on tallennettu id-numerolistaan. 
//                console.log("i: " + i)
                fetch("http://localhost:8080/students/" + i)                //Id-listan perusteella haetaan kunkin osallistujan tiedot opiskelija-tiedostosta.
                .then(response => response.json())
                .then(data2 => 
                    {
                        console.log("data2: " + data2)
                        let li = document.createElement("li")

                        li.innerText = "Student name: " + data2.studentName + " | Student email: " + data2.studentEmail + " | Student group: " + data2.studentGroup;
                        list2.appendChild(li)
                    })
                
            });     
        })
    }
    document.getElementById("getCourseAttendeesForm").reset();
    return;
}

document.getElementById("getStudentsCourses")                               //Tällä voidaan hakea opiskelijan kurssit
.addEventListener("click", e => 
{
    e.preventDefault();
    getStudentsCoursesById();
})

function getStudentsCoursesById()
{
    list2.innerHTML = "";

    let id = document.getElementById("stdsid");

    if(id.value !== '') {
        fetch("http://localhost:8080/getstudentscourses/" + id.value)       //Jokaisen opiskelijan kurssit on tallennettu id-numerolistaan.
        .then(response => response.json())
        .then(data =>
        {
//            console.log("data" + data)
            
            data.forEach(i => {
//                console.log("i: " + i)
                fetch("http://localhost:8080/courses/" + i)                 //Kurssien tiedot haetaan sitten id-numerolistan perusteella. 
                .then(response => response.json())
                .then(data2 => 
                    {
                        console.log("data2: " + data2)
                        let li = document.createElement("li")

                        li.innerText = "Course name: " + data2.courseName + " | Course teacher: " + data2.courseTeacher;
                        list2.appendChild(li)
                    })
                
            });     
        })
    }
    document.getElementById("getStudentsCoursesForm").reset();
    return;
}