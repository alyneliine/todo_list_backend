package com.todo_list.todo_list.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo_list.todo_list.dto.AtualizarRequest;
import com.todo_list.todo_list.models.Todo;
import com.todo_list.todo_list.repository.TodoRepository;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllListas() {
        return todoRepository.findAll();
    }

    public void deleteTodoById(Long id) throws Exception{
        try {
            Optional<Todo> todo = todoRepository.findById(id);
            if(!todo.isPresent()){
                throw new Exception("ToDo não encontrado");
            }
            todoRepository.deleteById(id);
        } catch (Exception e) {
            throw e;
        }
    }
    public void cadastrarTodo(String name, String description, Date startDate, Date endDate) {
        Todo todo = new Todo();
        todo.setName(name);
        todo.setDescription(description);
        todo.setStartDate(startDate);
        todo.setEndDate(endDate);

        todoRepository.save(todo);
    }

    public void updateTodoById(AtualizarRequest request) throws Exception{
        Optional<Todo> todoFind = todoRepository.findById(request.getId());
        if(!todoFind.isPresent()){
            throw new Exception("Lista não cadastrada!");
        }
        Todo todo = todoFind.get();
        if(todo.getDescription() != request.getDescription()){
            todo.setDescription(request.getDescription());
        }
        if(todo.getName() != request.getName()){
            todo.setName(request.getName());
        }
        if(todo.getStartDate() != request.getStartDate()){
            todo.setStartDate(request.getStartDate());
        }
        if(todo.getEndDate() != request.getEndDate()){
            todo.setEndDate(request.getEndDate());
        }
        todoRepository.save(todo);
    }
}

 