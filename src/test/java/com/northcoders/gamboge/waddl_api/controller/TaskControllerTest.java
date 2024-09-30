package com.northcoders.gamboge.waddl_api.controller;

import static org.junit.jupiter.api.Assertions.*;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.northcoders.gamboge.waddl_api.WaddlApiApplication;
import com.northcoders.gamboge.waddl_api.model.Task;
import com.northcoders.gamboge.waddl_api.service.TaskManagerService;
import com.northcoders.gamboge.waddl_api.service.TaskManagerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@AutoConfigureMockMvc
@SpringBootTest(classes = {WaddlApiApplication.class, Configuration.class})
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TaskManagerServiceImpl taskManagerService;

    @InjectMocks
    private TaskController taskController;
    private ObjectMapper objectMapper;

    // Mock tasks
    private Task mockTask1;
    private Task mockTask2;

    @Test
    public void test() {

        assertTrue(true);
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // Initialize mock data
        mockTask1 = new Task();
        mockTask1.setId(1L);
        mockTask1.setTitle("Clean room");
        mockTask1.setDescription("Put washing away, hoover");
        mockTask1.setCreatedDate(LocalDate.of(2024, 9, 27));
        mockTask1.setCompletedDate(LocalDate.of(2024, 9, 28));
        mockTask1.setCompleted(false);

        mockTask2 = new Task();
        mockTask2.setId(2L);
        mockTask2.setTitle("Go shopping");
        mockTask2.setDescription("Buy bread, milk, eggs");
        mockTask2.setCreatedDate(LocalDate.of(2024, 9, 27));
        mockTask2.setCompletedDate(LocalDate.of(2024, 9, 28));
        mockTask2.setCompleted(false);

    }

    @Test
    @DisplayName("Can retrieve all tasks")
    public void canRetrieveAllTasks() throws Exception {
        //arrange
        List<Task> mockTaskList = Arrays.asList(mockTask1, mockTask2);
        given(taskManagerService.getAllTasks()).willReturn(mockTaskList);
        //act and assert
        mockMvc.perform(get("/api/v1/tasks")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content()).andReturn();
//        var status = status();
//        var content = content();
    }

    @Test
    @DisplayName("Can retrieve task by Id")
    public void canRetrieveTaskById() throws Exception {
        // Arrange
        when(taskManagerService.getTaskById(1L))
                .thenReturn(Optional.of(mockTask1));

        // Act & Assert
        mockMvc.perform(get("/api/v1/tasks/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockTask1)));
    }

    @Test
    @DisplayName("Can add task with post request ")
    public void canAddTaskWithPostRequest() throws Exception {

        when(taskManagerService.addTask(any(Task.class))).thenReturn(mockTask1);

        List<Task> mockTaskList = Collections.singletonList(mockTask1);
        when(taskManagerService.getAllTasks()).thenReturn(mockTaskList);

        // mock controller layer / api testing
        mockMvc.perform(post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockTask1)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(mockTask1)));

        mockMvc.perform(get("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockTaskList)));

    }


    @Test
    @DisplayName("Delete task")
    public void testDeleteTask() throws Exception {
        doNothing().when(taskManagerService).deleteTaskById(anyLong());

        mockMvc.perform(delete("/api/v1/tasks/1"))
                .andExpect(status().isNoContent());
    }



}

