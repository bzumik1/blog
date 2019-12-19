package com.example.webappspringboot.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter @Setter @NoArgsConstructor
@Entity @Table(name = "POSTS")
public class Post {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title", nullable = false, length = 155)
    private String title;

    @Lob
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on")
    private Date upadatedOn;

    @OneToMany
    @JoinColumn(name = "post_id")
    private List<Comment> comments;


}
