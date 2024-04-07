package com.todo_list.todo_list.service;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.todo_list.todo_list.models.User;
import com.todo_list.todo_list.repository.UserRepository;
import com.todo_list.todo_list.security.UserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = getUser(() -> userRepository.findByEmail(email));
        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(String id) {
        User user = getUser(() -> userRepository.findById(id));
        return UserPrincipal.create(user);
    }
    
      private User getUser(Supplier<Optional<User>> supplier) {
          return supplier.get().orElseThrow(() ->
                  new UsernameNotFoundException("Usuário não cadastrado")
          );
    }

}
