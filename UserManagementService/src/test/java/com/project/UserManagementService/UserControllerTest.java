package com.project.UserManagementService;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.project.UserManagementService.controller.UserController;
import com.project.UserManagementService.model.User;
import com.project.UserManagementService.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(MockitoJUnitRunner.Silent.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper=new ObjectMapper();
    ObjectWriter objectWriter=objectMapper.writer();

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    User user_1 = new User( 1,"Saathvik","Saath***1234", "Saath12@gmail.com","9874564567");

    User user_2 = new User( 2,"Sarthak","Sarth***1994", "Sarth12@gmail.com", "9746310051");

    User user_3 = new User( 3,"Sagar","Sagar***1234", "Saagar12@gmail.com", "988563105");

    User user_4 = new User( 4,"Shivu","Shivu***1234", "Shivu@gmail.com", "988831005");

    User user_5 = new User( 5,"shubham","Shubam***1234", "Shubam12@gmail.com", "333563105");


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        }



    @Test
    public void addUser_success() throws Exception{
        User record=User.builder()
                .userId(1)
                .userName("Saathvik")
                .password("Saath***1234")
                .email("Saath12@gmail.com")
                .phone("9874564567")
                .build();

        when(userService.add(record)).thenReturn(record);

        String content=objectWriter.writeValueAsString(record);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder=MockMvcRequestBuilders.post("/user/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.userName").value("Saathvik"));


    }

    @Test
    public void getUsers_success() throws Exception{
        List<User> records=new ArrayList<>(Arrays.asList(user_1,user_2,user_3,user_4,user_5));

        when(userService.getUsers()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/viewusers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].userName", is("Saathvik")));

    }

    @Test
    public void viewbyId_success() throws Exception {
        when(userService.viewbyId(user_1.getUserId())).thenReturn(java.util.Optional.of(user_1));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/view/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.userName").value("Saathvik"));
    }

    @Test
    public void updateUser_success() throws Exception{
        User updateRecord= User.builder()
                .userId(1)
                .userName("Sathvik")
                .password("Saath***1234")
                .email("Saath12@gmail.com")
                .phone("9874564567")
                .build();


        when(userService.updateUser(user_1.getUserId(),updateRecord)).thenReturn(updateRecord);

        String updatedContent= objectWriter.writeValueAsString(updateRecord);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder=MockMvcRequestBuilders.put("/user/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updatedContent);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.userName").value("Sathvik"));


    }

    @Test
    public void deleteUser_success() throws Exception{
        willDoNothing().given(userService).deleteUser(user_4.getUserId());
        ResultActions resultActions = mockMvc.perform(delete("/user/delete/4" + user_4.getUserId()));
        resultActions.andExpect(status().isOk())
                .andDo(print());

    }








}
