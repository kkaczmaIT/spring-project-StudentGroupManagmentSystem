package com.paoim_kacper_kaczmarczyk.paoim_gr3_zad5_kacper_kaczmarczyk.students;

//import entityDatabase.GroupsEntity;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.pattern.PathPatternParser;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface StudentsRepository  extends JpaRepository<StudentsEntity, Integer>{

    @Query("SELECT COUNT(idGroup) FROM StudentsEntity WHERE idGroup = ?1")
    public int getQuantityStudentsFromGroup(int groupID);

    @Query("SELECT student FROM StudentsEntity student WHERE student.idGroup = ?1")
    public List<StudentsEntity> getStudentsListFromGroup(int groupID);
}
