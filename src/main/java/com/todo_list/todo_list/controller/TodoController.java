package com.todo_list.todo_list.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo_list.todo_list.dto.ApiResponse;
import com.todo_list.todo_list.dto.AtualizarRequest;
import com.todo_list.todo_list.dto.CadastroRequest;
import com.todo_list.todo_list.models.Todo;
import com.todo_list.todo_list.service.TodoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@Tag(name = "Todo")
public class TodoController {
    @Autowired
    private TodoService todoService;
 
    @Operation(summary = "Listar todos os TODOs")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/todo")
    public ResponseEntity<List<Todo>> getAllListas(){
        List<Todo> listas = todoService.getAllListas();
        return new ResponseEntity<>(listas, HttpStatus.OK);
    }

    @Operation(summary = "Deletar TODO por Id")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/todo/{id}")
    public ResponseEntity<ApiResponse> deleteTodoById(@PathVariable ("id") String id) throws Exception{
        try{
            todoService.deleteTodoById(id);
            return ResponseEntity.ok().body(new ApiResponse("ToDo deletado com sucesso!"));

        }catch(Exception e){
            return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage()));
        }
    }

    @Operation(summary = "Cadastrar um TODO")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/todo")
    public ResponseEntity<ApiResponse> cadastrarTodo(@RequestBody CadastroRequest request) throws Exception {
        try {
            todoService.cadastrarTodo(request.getName(), request.getDescription(), request.getStartDate(), request.getEndDate());
            return ResponseEntity.ok().body(new ApiResponse("ToDo criado com sucesso!"));
        } catch (Exception e) {
             return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage()));
        }
    
    }

    @Operation(summary = "Atualizar um TODO")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/todo")
    public ResponseEntity<ApiResponse> updateTodoById(@RequestBody AtualizarRequest request){
        try {
            todoService.updateTodoById(request);
            return ResponseEntity.ok().body(new ApiResponse("ToDo atualizada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage()));
        }
    }

}
