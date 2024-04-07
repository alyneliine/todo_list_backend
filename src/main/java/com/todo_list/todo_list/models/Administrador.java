package com.todo_list.todo_list.models;

import com.todo_list.todo_list.enums.Perfil;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import static com.todo_list.todo_list.enums.Perfil.ADMINISTRADOR;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Administrador extends User {

    @Override
    public Perfil getPerfil() {
        return ADMINISTRADOR;
    }

}
