package com.codeup.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "This field cannot be empty")
    @Size(min = 5, message = "Titles must be at least 5 characters long")
    private String title;

    @Column(nullable = false, columnDefinition = "Text")
    @NotBlank(message = "This field cannot be empty")
    private String body;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private User owner;


    public Post(String title, String body, User owner) {
        this.title = title;
        this.body = body;
        this.owner = owner;
    }

    public Post() {}

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public User getOwner() {return owner;}
    public void setOwner(User owner) {this.owner = owner;}
}
