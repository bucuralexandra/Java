package com.utcn.assignmentSD.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany
    @JoinColumn(table = "tag_link", name = "tag_id")
    private List<Question> questions = new ArrayList<>();

    @Column(name="name")
    private String name;
    public Integer getId() {
        return id;
    }

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public void addQuestion(Question question)
    {
        this.questions.add(question);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
