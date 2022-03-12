package com.utcn.assignmentSD.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @Column (name = "question_id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (name = "title")
    private String title;
    @Column (name = "body")
    private String body;
    @Column (name = "creation_date")
    private Date creationDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany
    @JoinColumn(table = "tag_link", name = "question_id")
    private Set<Tag> tags = new HashSet<>();

    public Question() {
    }

    public Question(String title, String body, Date creationDate, User user) {
        this.title = title;
        this.body = body;
        this.creationDate = creationDate;
        this.user = user;
    }

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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag)
    {
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", creationDate=" + creationDate +
                ", user=" + user.getName() +
                '}' + "\n";
    }
}
