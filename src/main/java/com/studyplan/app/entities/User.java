package com.studyplan.app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//@Getter
//@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "fullName", nullable = false, length = 50)
    private String fullName;

    @Column(name = "email", nullable = false, length = 128, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "userRole", nullable = false)
    private String userRole;

}
