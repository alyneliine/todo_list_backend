package com.todo_list.todo_list.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
