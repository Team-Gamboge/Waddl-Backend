package com.northcoders.gamboge.waddl_api.controller;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.northcoders.gamboge.waddl_api.model.Task;
import com.northcoders.gamboge.waddl_api.repository.TaskRepository;
import com.northcoders.gamboge.waddl_api.service.TaskManagerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskManagerServiceImpl taskManagerService;

    @MockBean
    private TaskRepository repo;

    @InjectMocks
    private TaskController taskController;
    private ObjectMapper objectMapper;

    // Mock tasks
    private Task mockTask1;
    private Task mockTask2;

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
        mockTask1.setCompleted(false);

        mockTask2 = new Task();
        mockTask2.setId(2L);
        mockTask2.setTitle("Go shopping");
        mockTask2.setDescription("Buy bread, milk, eggs");
        mockTask2.setCompleted(false);

    }

    @Test
    @DisplayName("Can retrieve all tasks")
    public void canRetrieveAllTasks() throws Exception {
        //arrange
        List<Task> mockTaskList = Arrays.asList(mockTask1, mockTask2);
        when(taskManagerService.getAllTasks()).thenReturn(mockTaskList);
        List<Task> returnedTasks = taskManagerService.getAllTasks();

        //act and assert
        mockMvc.perform(get("/api/v1/tasks")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockTaskList)))
                .andReturn();
        var status = status();
        var content = content();
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
        String expectedResponse = "Task 1 deleted successfully.";
        when(repo.existsById(1L)).thenReturn(true);        
        when(taskManagerService.deleteTaskById(1L)).thenReturn(expectedResponse);
        mockMvc.perform(delete("/api/v1/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    @DisplayName("Test if a task can be updated.")
    public void testUpdateTaskTitleAndDescription() throws Exception {
        Task updatedTaskInfo = new Task();
        updatedTaskInfo.setTitle("Tidy kitchen");
        updatedTaskInfo.setDescription("Clean the oven, hoover");
        updatedTaskInfo.setCompleted(false);

        when(taskManagerService.updateTaskById(anyLong(), any(Task.class))).thenReturn(updatedTaskInfo);
        mockMvc.perform(put("/api/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedTaskInfo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Tidy kitchen"))
                .andExpect(jsonPath("$.description").value("Clean the oven, hoover"));
    }
}

