package com.webservice.crest.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class AnswerRequest {
    private Answer answer;
    private long questionId;
    private long userId;
}
