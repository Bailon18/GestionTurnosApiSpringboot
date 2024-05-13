package com.GestionTurnosApiBack.security.Entity;

import java.util.List;



public interface UserService {

    List<User> usuarios();
    User buscarUsuarioId(Long id);
    User guardarUsuario(User usuario);
    void cambioEstado(Long id, boolean estado);
    boolean existeCorreoElectronico(String correoElectronico);

    boolean existeUsername(String username);
    boolean existeCedula(String cedula);
    List<String> findDistinctRoleBy();
}
