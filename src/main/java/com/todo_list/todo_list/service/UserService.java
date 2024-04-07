package com.todo_list.todo_list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo_list.todo_list.models.Comum;
import com.todo_list.todo_list.repository.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class UserService {
    private static final String headerPrefix = "Bearer ";

    @Autowired
    private UserRepository userRepository;

    @Autowired
  private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public void cadastrar(String name, String email, String password) throws Exception {
        if(userRepository.findByEmail(email).isPresent()){
          throw new Exception("Usuário já cadastrado!");
        }
        Comum userComum = new Comum();
        userComum.setName(name);
        userComum.setEmail(email);
        userComum.setPassword(passwordEncoder.encode(password));
        
        userRepository.save(userComum);
      }

      public String login(String email, String senha) {
        try{
          Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, senha));
          SecurityContextHolder.getContext().setAuthentication(authentication);
          String token = headerPrefix + jwtService.gerarToken(authentication);
          return token;
        }catch(Exception e){
          System.out.println("####################################" + e.getMessage() + "####################################");
          return e.getMessage();
        }
      }

}
