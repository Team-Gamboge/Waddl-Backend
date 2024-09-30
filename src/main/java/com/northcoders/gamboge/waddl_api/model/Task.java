package com.northcoders.gamboge.waddl_api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createdDate;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate completedDate;
    @Column
    private boolean isCompleted;
}
