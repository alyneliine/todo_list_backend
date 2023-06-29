package com.todo_list.todo_list.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo_list.todo_list.models.Todo;
import com.todo_list.todo_list.repository.TodoRepository;

@Service
public class TodoService {
     public List<Todo> getAllListas() {
        return null;
    }

    @Autowired
    private TodoRepository todoRepository;
    public void deleteTodoById(Long id){
        try {
             todoRepository.deleteById(id);
     } catch (Exception e) {
            e.toString();
     }
}
    public void cadastrarTodo(String name, String description, Date startDate, Date endDate) {
    }
}

 