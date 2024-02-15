package com.webservice.crest.controllers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.webservice.crest.models.Role;
import com.webservice.crest.models.User;
import com.webservice.crest.services.UserService;

public class APIControllersTest {
/*
    @Test
    public void testGetUser() {
        // Mock UserService
        UserService userServiceMock = mock(UserService.class);
        
        // Mock user
        User user = new User(0, "firstUser", "test", 25, "Jr.Engineer", Role.USER);
        
        // Mock UserService behavior
        when(userServiceMock.getUser(1L)).thenReturn((ResponseEntity<User>) ResponseEntity.ok(user));
        
        // Create instance of APIControllers
        APIControllers apiControllers = new APIControllers(userServiceMock);        
        
        // Test
        ResponseEntity<?> response = apiControllers.getUser(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testGetAllUsers() {
        // Mock UserService
        UserService userServiceMock = mock(UserService.class);
        
        // Mock users
        List<User> users = new ArrayList<>();
        users.add(new User(0, "firstUser", "test1", 25, "Jr.Engineer", Role.USER));
        users.add(new User(0, "secondUser", "test2", 35, "Sr.Engineer", Role.USER));
        
        // Mock UserService behavior
        when(userServiceMock.getAllUsers()).thenReturn(users);
        
        // Create instance of APIControllers
        APIControllers apiControllers = new APIControllers(userServiceMock);        
        
        // Test
        List<User> response = apiControllers.getAllUsers();
        assertEquals(users, response);
    }*/
}
