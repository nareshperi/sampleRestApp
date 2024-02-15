package com.webservice.crest.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class QuestionRequest {
    private Question question;
    private long userId;
}
