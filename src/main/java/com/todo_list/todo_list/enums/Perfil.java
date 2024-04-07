package com.todo_list.todo_list.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.AccessLevel;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Perfil {

    ADMINISTRADOR("ROLE_ADMINISTRADOR"),
    COMUM("ROLE_COMUM");

    private final String role;

}
