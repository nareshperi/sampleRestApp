package com.webservice.crest.models;

import java.util.List;

import lombok.Data;

@Data
public class BatchUserRequest {
    private List<User> users;
}
