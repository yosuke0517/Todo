package com.example.Todo.domain.dto;

import com.example.Todo.domain.dto.common.DomaDtoImpl;
import lombok.Data;
import org.seasar.doma.*;

@Entity
@Table(name = "tasks")
@Data
public class Task extends DomaDtoImpl {

    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    @Column
    Long user_id;

    @Column
    int task_category;

    @Column
    String subject;

    @Column
    String details;
}
