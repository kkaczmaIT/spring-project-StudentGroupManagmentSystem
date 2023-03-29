package com.paoim_kacper_kaczmarczyk.paoim_gr3_zad5_kacper_kaczmarczyk.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentsService {
    private final StudentsRepository studentsRepository;

    @Autowired
    public StudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }


    public List<StudentsEntity> getStudents()
    {
        return studentsRepository.findAll();
    }

    public void addStudent(StudentsEntity student)
    {
        studentsRepository.save(student);
    }

    public void deleteStudent(int studentID)
    {
        if(studentsRepository.existsById(studentID))
        {
            studentsRepository.deleteById(studentID);
        }
        else
            throw new IllegalStateException("Student about id " + studentID + " does not exist");
    }

    public int getPercentageOfUsedGroupCapacity(int groupID)
    {
        return studentsRepository.getQuantityStudentsFromGroup(groupID);
    }

    public List<StudentsEntity> getStudentsListFromGroup(int groupID)
    {

        return studentsRepository.getStudentsListFromGroup(groupID);
    }

    public StudentsEntity getStudent(int studentID)
    {
        if(studentsRepository.existsById(studentID))
            return studentsRepository.findById(studentID).get();
        return null;
    }
}
