package com.todo_list.todo_list.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo_list.todo_list.models.User;

public interface UserRepository extends JpaRepository <User, String>{
    public Optional<User> findByEmail(String email);
}
