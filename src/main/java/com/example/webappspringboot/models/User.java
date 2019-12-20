package com.example.webappspringboot.models;

import lombok.*;

import javax.persistence.*;

@Getter @Setter @NoArgsConstructor
@Entity @Table(name = "USERS")
public class User{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "name", nullable = false, length = 100)
    String userName;

    @Column(name = "email", nullable = false, length = 100)
    String email;

    @Column(name = "password", nullable = false, length = 100)
    String password;

}
