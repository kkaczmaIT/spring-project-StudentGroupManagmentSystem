package com.paoim_kacper_kaczmarczyk.paoim_gr3_zad5_kacper_kaczmarczyk.groups;

//import entityDatabase.GroupsEntity;
import com.paoim_kacper_kaczmarczyk.paoim_gr3_zad5_kacper_kaczmarczyk.Student;
import com.paoim_kacper_kaczmarczyk.paoim_gr3_zad5_kacper_kaczmarczyk.students.StudentsEntity;
import com.paoim_kacper_kaczmarczyk.paoim_gr3_zad5_kacper_kaczmarczyk.students.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GroupController {

    private final GroupsService groupsService;
    private final StudentsService studentsService;

    @Autowired
    public GroupController(GroupsService groupsService, StudentsService studentsService) {
        this.groupsService = groupsService;
        this.studentsService = studentsService;
    }

    @GetMapping("/course")
    public ResponseEntity<List<GroupsEntity>> getAllGroups()
    {
        try{
            if(groupsService.getGroups().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(groupsService.getGroups(), HttpStatus.OK);
        }catch(Exception e)
        {
            System.out.println("error: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path="course")
    public ResponseEntity AddNewGroup(@Validated @RequestBody GroupsEntity group)
    {
        try{
            System.out.println(group.getNameGroup());
            if( group.getNameGroup() == null)
                return new ResponseEntity("Incorrect values, group name must be string", HttpStatus.BAD_REQUEST);
            else
            {
                if(group.getMaxListSize() > 0)
                {
                    groupsService.addGroup(group);
                    return new ResponseEntity<>("Group created", HttpStatus.CREATED);
                }
                return new ResponseEntity<>("Length of student list must be greater than zero", HttpStatus.BAD_REQUEST);

            }
        }catch(Exception e)
        {
            System.out.println("error: " + e.getMessage());
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path="course/{groupID}")
    public @ResponseBody ResponseEntity deleteGroup(@Validated @PathVariable("groupID") Integer groupID)
    {
        try{
             groupsService.deleteGroup(groupID);
             return new ResponseEntity("Group Deleted", HttpStatus.OK);
        } catch(Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

//    @PutMapping(path="course/{groupID}")
//    public void editGroup(
//            @PathVariable("groupID") Integer groupID,
//            @RequestParam(value = "nameGroup", required = false) String nameGroup,
//            @RequestParam(value = "maxListSize", required = false) Integer maxListSize
//    )
//    {
//        try
//        {
//            groupsService.editGroup(groupID, nameGroup, maxListSize);
//        } catch(Exception e)
//        {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }

    @GetMapping(path="course/{groupID}/fill")
    public ResponseEntity getFillGroup(@PathVariable("groupID") int groupID)
    {
        try {
            GroupsEntity group = groupsService.getGroup(groupID);
            int quantityOfStudentsInGroup = studentsService.getPercentageOfUsedGroupCapacity(groupID);
            return new ResponseEntity<>((Double.valueOf(quantityOfStudentsInGroup) / Double.valueOf(group.getMaxListSize()) * 100) + "%", HttpStatus.OK);
        } catch(Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="course/{groupID}/students")
    public ResponseEntity getStudentsListFromGroup(@PathVariable("groupID") int groupID)
    {
        try {
            groupsService.getGroup(groupID);
            List<StudentsEntity> studentsList = studentsService.getStudentsListFromGroup(groupID);
            if(studentsList.isEmpty())
                return new ResponseEntity<>(studentsList, HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(studentsList, HttpStatus.OK);
        } catch(Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Group about id " + groupID + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
