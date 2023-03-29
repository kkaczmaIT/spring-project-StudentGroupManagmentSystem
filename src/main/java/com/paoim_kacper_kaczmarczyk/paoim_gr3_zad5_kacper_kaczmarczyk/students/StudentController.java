package com.paoim_kacper_kaczmarczyk.paoim_gr3_zad5_kacper_kaczmarczyk.students;

import com.paoim_kacper_kaczmarczyk.paoim_gr3_zad5_kacper_kaczmarczyk.ratings.RatingsService;
import com.paoim_kacper_kaczmarczyk.paoim_gr3_zad5_kacper_kaczmarczyk.utilities.ManagmentCSVFile;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;




@RestController
@RequestMapping("api")
public class StudentController
{

    private final StudentsService studentsService;
    private final RatingsService ratingsService;

    @Autowired
    public StudentController(StudentsService studentsService, RatingsService ratingsService) {
        this.studentsService = studentsService;
        this.ratingsService = ratingsService;
    }

    @GetMapping(path = "student")
    public ResponseEntity getAllStudents() {
        try{
            if(studentsService.getStudents().isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(studentsService.getStudents(), HttpStatus.OK);
        } catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "student/csv")
    public ResponseEntity getAllStudentsToCSVFile(HttpServletResponse response) throws Exception {
        String filename = "students.csv";
        response.setContentType("text/csv");
        response.setCharacterEncoding("UTF-8");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");

        try{
            List<StudentsEntity> csvBody = studentsService.getStudents();

            if(csvBody.isEmpty())
            {
                return new ResponseEntity<>("Empty list of students", HttpStatus.NO_CONTENT);
            }
            ManagmentCSVFile managmentCSVFile = new ManagmentCSVFile(filename, response);
            managmentCSVFile.saveToCSV(csvBody);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path="student")
    public ResponseEntity addNewStudent(@Validated @RequestBody StudentsEntity student)
    {
        try{
            if(student.getFirstName() != null && student.getLastName() != null)
            {
                if(student.getStudentCondition() > 0 && student.getStudentCondition() < 4)
                {
                    if(student.getYearOfBirth() > 1950)
                    {
                        if(student.getScores() > 0)
                        {
                            if(student.getIdGroup() > 0)
                            {
                                studentsService.addStudent(student);
                                return new ResponseEntity("Student created", HttpStatus.CREATED);
                            }
                            else
                                return new ResponseEntity("Group id is invalid", HttpStatus.BAD_REQUEST);
                        }
                        else
                            return new ResponseEntity("Scores must be greater than zero", HttpStatus.BAD_REQUEST);
                    }
                    else
                        return new ResponseEntity("Date of birth must be greater tahn 1950", HttpStatus.BAD_REQUEST);
                }
                else
                    return new ResponseEntity<>("Student Condition invalid", HttpStatus.BAD_REQUEST);
            }
            else
                return new ResponseEntity("First and last name must be given", HttpStatus.BAD_REQUEST);

        } catch(Exception e) {
            return new ResponseEntity("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(path="student/{studentID}")
    public ResponseEntity deleteStudent(@PathVariable("studentID") Integer studentID)
    {
        try{
            studentsService.deleteStudent(studentID);
            return new ResponseEntity<>("Student deleted", HttpStatus.OK);
        } catch(Exception e)
        {
            return new ResponseEntity("Error " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="student/{studentID}/grade")
    public ResponseEntity getStudentRatingGrade(@PathVariable("studentID") int studentID)
    {
        try
        {
            if(studentsService.getStudent(studentID) == null)
                return new ResponseEntity<>("Student about id " + studentID + " does not exists", HttpStatus.NOT_FOUND);
            double average = ratingsService.getStudentRatingGrade(studentID);
            System.out.println(average);

            return new ResponseEntity(ratingsService.getStudentRatingGrade(studentID), HttpStatus.OK);
        } catch(Exception e)
        {
            return new ResponseEntity("Error: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
