package com.paoim_kacper_kaczmarczyk.paoim_gr3_zad5_kacper_kaczmarczyk.ratings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingsRepository extends JpaRepository<RatingsEntity, Integer> {

    @Query("SELECT rating FROM RatingsEntity rating WHERE rating.idStudent = ?1")
    public List<RatingsEntity> getStudentsRating(int studentID);

}
