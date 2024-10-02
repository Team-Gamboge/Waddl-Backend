package com.northcoders.gamboge.waddl_api.model;

import jakarta.persistence.*;
import lombok.*;


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
    private boolean isCompleted;
}
