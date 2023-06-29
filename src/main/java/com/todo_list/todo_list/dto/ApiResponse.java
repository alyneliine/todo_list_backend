package com.todo_list.todo_list.dto;

import lombok.Data;

@Data
public class ApiResponse {

     private String message;

     public ApiResponse(String message) {
        this.message = message;
  }
    
}
