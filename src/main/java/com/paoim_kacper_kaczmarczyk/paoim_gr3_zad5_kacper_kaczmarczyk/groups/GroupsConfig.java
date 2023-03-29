package com.paoim_kacper_kaczmarczyk.paoim_gr3_zad5_kacper_kaczmarczyk.groups;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GroupsConfig {
    @Bean
    CommandLineRunner commandLineRunner(GroupsRepository groupsRepository)
    {

        return args -> {
//            GroupsEntity group1 = new GroupsEntity();
//
//            group1.setNameGroup("Kółko matematyczne");
//            group1.setMaxListSize(19);
//            GroupsEntity group2 = new GroupsEntity();
//            group2.setNameGroup("Kółko przyrodnicze");
//            group2.setMaxListSize(12);
//
//            groupsRepository.saveAll(
//                    List.of(group1, group2)
//            );
        };
    }
}