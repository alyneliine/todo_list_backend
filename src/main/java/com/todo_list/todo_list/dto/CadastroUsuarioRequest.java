package com.todo_list.todo_list.dto;

import lombok.Data;

@Data
public class CadastroUsuarioRequest {
    private String name;
    private String email;
    private String password;
}
