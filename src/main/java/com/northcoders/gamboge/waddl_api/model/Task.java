package com.northcoders.gamboge.waddl_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Getter
@Setter
@Table(name="tasks")

@Entity

public class Task {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private long id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private LocalDate createdDate;
    @Column
    private LocalDate completedDate;
    @Column
    private boolean isCompleted;
}
