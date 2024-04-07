package com.todo_list.todo_list.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo_list.todo_list.dto.ApiResponse;
import com.todo_list.todo_list.dto.CadastroUsuarioRequest;
import com.todo_list.todo_list.dto.LoginRequest;
import com.todo_list.todo_list.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@Tag(name = "Usuário")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Cadastrar um usuário")
    @PostMapping("/user")
    public ResponseEntity<ApiResponse> cadastrar(@RequestBody CadastroUsuarioRequest request) throws Exception   {
        try{
        userService.cadastrar(request.getName(), request.getEmail(), request.getPassword());
        return ResponseEntity.created(null).body(new ApiResponse("Usuário criado com sucesso!"));
        }catch(Exception e){
        return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage()));
        }
    }

    @Operation(summary = "Realizar login")
    @PostMapping("/user/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request) throws Exception {
        try{
        String token = userService.login(request.getEmail(), request.getPassword());
        
        return ResponseEntity.ok().body(new ApiResponse("Autenticação feita com sucesso!" + token));
        }catch(Exception e){
        return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage()));
        }
    }

}
