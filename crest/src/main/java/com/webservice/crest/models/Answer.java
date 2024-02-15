package com.webservice.crest.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @Column(name = "answerId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column
    private String data;

    @ManyToOne
    @JoinColumn(name = "questionId")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public String toString() {        
        return ReflectionToStringBuilder.toString(this); 
    }    
}