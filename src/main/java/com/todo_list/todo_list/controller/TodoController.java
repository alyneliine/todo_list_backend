package com.todo_list.todo_list.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo_list.todo_list.dto.ApiResponse;
import com.todo_list.todo_list.dto.CadastroRequest;
import com.todo_list.todo_list.models.Todo;
import com.todo_list.todo_list.service.TodoService;

@RestController
public class TodoController {
    @Autowired
    private TodoService todoService;

    
    @GetMapping 
    public ResponseEntity<List<Todo>> getAllListas(){
        List<Todo> listas = todoService.getAllListas();
        return new ResponseEntity<>(listas, HttpStatus.OK);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<ApiResponse> deleteTodoById(@PathVariable ("id") Long id) throws Exception{
        try{
            todoService.deleteTodoById(id);
            return ResponseEntity.ok().body(new ApiResponse("ToDo deletado com sucesso!"));

        }catch(Exception e){
            return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage()));
        }
    }


    @PostMapping("/todo")
    public ResponseEntity<ApiResponse> cadastrarTodo(@RequestBody CadastroRequest request) throws Exception {
        try {
            todoService.cadastrarTodo(request.getName(), request.getDescription(), request.getStartDate(), request.getEndDate());
            return ResponseEntity.created(null).body(new ApiResponse("ToDo criado com sucesso!"));
        } catch (Exception e) {
             return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage()));
        }
    
    }

}
