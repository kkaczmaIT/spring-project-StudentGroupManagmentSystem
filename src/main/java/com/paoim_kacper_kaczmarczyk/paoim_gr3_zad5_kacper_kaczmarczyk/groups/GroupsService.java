package com.paoim_kacper_kaczmarczyk.paoim_gr3_zad5_kacper_kaczmarczyk.groups;

import com.paoim_kacper_kaczmarczyk.paoim_gr3_zad5_kacper_kaczmarczyk.groups.GroupsEntity;
//import entityDatabase.GroupsEntity;
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
public class GroupsService {

    private final GroupsRepository groupsRepository;
    @Autowired
    public GroupsService(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }


    public List<GroupsEntity> getGroups()
    {
//        GroupsEntity group = new GroupsEntity();
//        group.setNameGroup("test api");
//        group.setMaxListSize(20);
//        group.setId(4);
//        List<GroupsEntity> res = new ArrayList<GroupsEntity>();
//        res.add(group);
//        return res;
        return groupsRepository.findAll();
    }

    public GroupsEntity getGroup(int groupID)
    {
//        Optional<GroupsEntity> group =  groupsRepository.findById(groupID);
//        System.out.println(group.get());
        if(groupsRepository.existsById(groupID))
        {
            return groupsRepository.findById(groupID).get();
        }
        else
        {
            throw new IllegalStateException("Group about id " + groupID + " does not exists");
        }
    }

    public void addGroup(GroupsEntity group)
    {
        groupsRepository.save(group);
    }

    public void deleteGroup(int groupID)
    {
        //Optional<GroupsEntity> group = groupsRepository.findById(groupID);
        if(groupsRepository.existsById(groupID))
        {
            groupsRepository.deleteById(groupID);
        }
        else
        {
            throw new IllegalStateException("Group about id " + groupID + " does not exist");
        }

    }

    @Transactional
    public void editGroup(int groupID, String nameGroup, int maxListSize)
    {
        GroupsEntity group = groupsRepository.findById(groupID)
                .orElseThrow(() -> new IllegalStateException("Group about id " + groupID + " does not exist"));
        if(nameGroup != null && nameGroup.length() > 0 && !Objects.equals(group.getNameGroup(), nameGroup))
        {
            group.setNameGroup(nameGroup);
        }

        if(maxListSize > 0 && !Objects.equals(group.getMaxListSize(), maxListSize))
        {
            group.setMaxListSize(maxListSize);
        }
    }

//    public int getPercentageOfUsedGroupCapacity(int groupID)
//    {
//        if(groupsRepository.existsById(groupID))
//            return groupsRepository.getQuantityStudentsFromGroup(groupID);
//        else
//            throw new IllegalStateException("Group about id " + groupID + " does not exist");
//    }
}
