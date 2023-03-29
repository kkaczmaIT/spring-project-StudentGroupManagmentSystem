package com.paoim_kacper_kaczmarczyk.paoim_gr3_zad5_kacper_kaczmarczyk.groups;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GroupController.class)
class GroupControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getAllGroups() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/course");
        MvcResult result = mvc.perform(request).andReturn();

    }

    @Test
    void addNewGroup() {
    }

    @Test
    void deleteGroup() {
    }

    @Test
    void getFillGroup() {
    }

    @Test
    void getStudentsListFromGroup() {
    }
}