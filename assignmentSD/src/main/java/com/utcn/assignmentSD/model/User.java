package com.utcn.assignmentSD.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="users")
public class User {

    @Id
    @Column(name ="user_id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;


    @OneToMany (mappedBy = "user")
    private Set<Question> questions = new HashSet<>();

    public User( int id, String name, String username, String password)
    {
        this.id = id;
        this.name=name;
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonBackReference
    public Set<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question q)
    {
        this.questions.add(q);
    }
}
