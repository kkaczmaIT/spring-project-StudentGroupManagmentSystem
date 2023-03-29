package com.paoim_kacper_kaczmarczyk.paoim_gr3_zad5_kacper_kaczmarczyk.ratings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingsService {
    private final RatingsRepository ratingsRepository;

    @Autowired
    public RatingsService(RatingsRepository ratingsRepository) {
        this.ratingsRepository = ratingsRepository;
    }

    public Double getStudentRatingGrade(int studentID)
    {
            List<RatingsEntity> studentRatings = ratingsRepository.getStudentsRating(studentID);
            double average = 0;
            if(!studentRatings.isEmpty())
            {
                for(RatingsEntity rating: studentRatings)
                {
                    average += rating.getRating();
                }
                return average / studentRatings.size();
            }
            return average;
    }

    public void addStudentRating(RatingsEntity rating)
    {
        ratingsRepository.save(rating);
    }
}
