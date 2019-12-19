package com.example.webappspringboot.models;

import lombok.*;

import javax.persistence.*;

@Getter @Setter @NoArgsConstructor
@Entity @Table(name = "Users")

public class User{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;
    @Column(name = "user name", nullable = false, length = 155)
    String userName;
    @Column(name = "e-mail", nullable = false, length = 155)
    String email;
    @Column(name = "password", nullable = false, length = 155)
    String password;

}
