package com.northcoders.gamboge.waddl_api.service;

import static org.junit.jupiter.api.Assertions.*;

import com.northcoders.gamboge.waddl_api.model.Task;
import com.northcoders.gamboge.waddl_api.repository.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;
@DataJpaTest
public class TaskManagerServiceImplTest {
    @Mock
    private TaskRepository mockTaskRepository;
    @InjectMocks
    private TaskManagerServiceImpl taskManagerServiceImpl;
    @Test
    @DisplayName("GET request for all tasks")
    public void testGetAllTasksReturnsListOfTasks() {
        //Arrange
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L,"Clean room","put washing away", false));

          tasks.add(new Task(2L,"Go shopping","buy bread, milk, eggs", false));

        // Important comment!
        when(mockTaskRepository.findAll()).thenReturn(tasks);
        //Act
        List<Task> actualResult = taskManagerServiceImpl.getAllTasks();
        //Assert
        assertThat(actualResult).hasSize(2);
        assertThat(actualResult).isEqualTo(tasks);
    }

    @Test
    @DisplayName("GET task by id")
    void testGetTaskById() throws Exception {
        Task expectedResult = new Task(3L, "Go eminem", "Go Encore", false);
        when(mockTaskRepository.findById(expectedResult.getId())).thenReturn(Optional.of(expectedResult));
        when(mockTaskRepository.existsById(expectedResult.getId())).thenReturn(true);
        Optional<Task> actualResult = taskManagerServiceImpl.getTaskById(expectedResult.getId());
        assertEquals(actualResult.get(), expectedResult);
    }

    @Test
    @DisplayName("Test for delete task")
    public void testForDeleteTask() {
        Long testId = 3L;
        Task testEminem = new Task(3L, "Go eminem", "Go Encore",  false);
        when(mockTaskRepository.findById(testId)).thenReturn(Optional.of(testEminem));
        when(mockTaskRepository.existsById(testId)).thenReturn(true);
        doNothing().when(mockTaskRepository).deleteById(testId);
        taskManagerServiceImpl.deleteTaskById(testId);
        verify(mockTaskRepository, times(1)).deleteById(testId);
    }

}
