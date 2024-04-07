package com.todo_list.todo_list.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import static com.todo_list.todo_list.enums.Perfil.COMUM;

import com.todo_list.todo_list.enums.Perfil;;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Comum extends User {

    @Override
    public Perfil getPerfil() {
        return COMUM;
    }

}
