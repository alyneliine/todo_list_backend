package com.todo_list.todo_list.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CadastroRequest {
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    
}
