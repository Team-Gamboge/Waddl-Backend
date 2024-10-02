package com.northcoders.gamboge.waddl_api.utility;

import com.northcoders.gamboge.waddl_api.model.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    @Test
    @DisplayName("Test non-null field updater works with null fields.")
    void updateFieldsWhereNotNull() throws NoSuchFieldException, IllegalAccessException {
        Task sourceTask = new Task();
        sourceTask.setDescription("This is the new description.");
        
        Task destinationTask = new Task();
        destinationTask.setId(1L);
        destinationTask.setDescription("Old description.");
        destinationTask.setTitle("Original title!");
        
        assertDoesNotThrow(() -> Utility.updateFieldsWhereNotNull(sourceTask, destinationTask));
        assertEquals(sourceTask.getDescription(), destinationTask.getDescription());
    }
    
    @Test
    @DisplayName("Check updater does not throw with a null source.")
    void testFieldUpdaterDoesNotThrowWithNullSource() {
        Task sourceTask = null;

        Task destinationTask = new Task();
        destinationTask.setId(1L);
        destinationTask.setDescription("Old description.");
        destinationTask.setTitle("Original title!");

        assertThrows(NullPointerException.class, () -> Utility.updateFieldsWhereNotNull(sourceTask, destinationTask));
    }

    @Test
    @DisplayName("Check updater does not throw with a null destination.")
    void testFieldUpdaterDoesNotThrowWithNullDestination() {
        Task sourceTask = new Task();
        sourceTask.setDescription("This is the new description.");

        Task destinationTask = null;

        assertThrows(NullPointerException.class, () -> Utility.updateFieldsWhereNotNull(sourceTask, destinationTask));
    }
}