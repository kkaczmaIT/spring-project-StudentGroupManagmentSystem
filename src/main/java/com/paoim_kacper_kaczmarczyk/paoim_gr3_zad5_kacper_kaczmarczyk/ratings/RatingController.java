package com.paoim_kacper_kaczmarczyk.paoim_gr3_zad5_kacper_kaczmarczyk.ratings;

import com.paoim_kacper_kaczmarczyk.paoim_gr3_zad5_kacper_kaczmarczyk.students.StudentsService;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.sql.Date;


@RestController
@RequestMapping(path="/api")
public class RatingController {
    private final RatingsService ratingsService;
    private final StudentsService studentsService;

    @Autowired
    public RatingController(RatingsService ratingsService, StudentsService studentsService) {
        this.ratingsService = ratingsService;
        this.studentsService = studentsService;
    }

    @PostMapping(path="rating")
    public ResponseEntity addStudentRating(@Validated @RequestBody RatingsEntity rating)
    {
        Long timeMililis = System.currentTimeMillis();
        Timestamp dateTime = new Timestamp(timeMililis);
        Date date = new Date(dateTime.getTime());

        try{
            if(rating.getRating() > 2 && rating.getRating() < 6)
            {
                if(rating.getComment() != null)
                {
                    if(studentsService.getStudent(rating.getIdStudent()) != null)
                    {
                        rating.setCreatedAt(date);
                        ratingsService.addStudentRating(rating);
                        return new ResponseEntity("Rating created", HttpStatus.CREATED);
                    }
                    else
                        return new ResponseEntity("Student id is invalid. Student not found", HttpStatus.NOT_FOUND);
                }
                else
                    return new ResponseEntity("Comment must be given", HttpStatus.BAD_REQUEST);
            }
            else
                return new ResponseEntity("Rating must be greater than two and less than 6", HttpStatus.BAD_REQUEST);

        } catch(Exception e)
        {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
