package com.example.Todo.domain.dto;

import com.example.Todo.domain.dto.common.DomaDtoImpl;
import lombok.Data;
import org.seasar.doma.*;

@Entity
@Data
@Table(name = "users")
public class User extends DomaDtoImpl {
    //自動採番設定
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column
    String email;

    @Column
    String password;

}
