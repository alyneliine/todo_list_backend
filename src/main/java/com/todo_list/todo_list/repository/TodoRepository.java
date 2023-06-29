package com.todo_list.todo_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo_list.todo_list.models.Todo;

public interface TodoRepository extends JpaRepository <Todo, Long> {

}
    

