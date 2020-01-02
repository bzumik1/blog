package com.example.webappspringboot.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter @Setter @NoArgsConstructor
@Entity @Table(name = "POSTS")
public class Post {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false, length = 200) // length is length of string VARCHAR(200)
    private String title;

    @Lob //large object
    @Column(name = "content", nullable = false, columnDefinition = "TEXT") // columnDefinition for DDL
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on")
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP) // it is here to map correctly the Date from java to TIMESTAMP in SQL
    @Column(name = "updated_on")
    private Date updatedOn;

    @OneToMany//(fetch = FetchType.EAGER) // connect two different tables, ONE item from this table can be connected with MANY from the second one
    //EAGER - fetch the list immediately when the post is called. Don't use when you don't have to
    @JoinColumn(name = "post_id")
    private List<Comment> comments;


}
