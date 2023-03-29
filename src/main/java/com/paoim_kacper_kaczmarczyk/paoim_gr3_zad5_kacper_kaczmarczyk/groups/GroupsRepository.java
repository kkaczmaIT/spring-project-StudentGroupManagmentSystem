package com.paoim_kacper_kaczmarczyk.paoim_gr3_zad5_kacper_kaczmarczyk.groups;

import com.paoim_kacper_kaczmarczyk.paoim_gr3_zad5_kacper_kaczmarczyk.students.StudentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface GroupsRepository extends JpaRepository<GroupsEntity, Integer> {
    //Group Methods
//    @Query("SELECT COUNT(idGroup) FROM StudentsEntity WHERE idGroup = ?1")
//    public int getQuantityStudentsFromGroup(int groupID);
//
//    @Query("SELECT student FROM StudentsEntity student WHERE student.idGroup = ?1")
//    public List<StudentsEntity> getStudentsListFromGroup(int groupID);
}
