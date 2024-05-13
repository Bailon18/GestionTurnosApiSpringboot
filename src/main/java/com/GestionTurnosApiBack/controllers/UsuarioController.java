package com.GestionTurnosApiBack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.GestionTurnosApiBack.security.Entity.User;
import com.GestionTurnosApiBack.security.Entity.UserService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = { "*" })
public class UsuarioController {

    @Autowired
    private UserService usuarioService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsuarios() {
        List<User> usuarios = usuarioService.usuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUsuarioById(@PathVariable Long id) {
        User usuario = usuarioService.buscarUsuarioId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

 
    @GetMapping("/existe-correo")
    public ResponseEntity<Boolean> existeCorreoElectronico(@RequestParam String correoElectronico) {
        boolean existe = usuarioService.existeCorreoElectronico(correoElectronico);
        return ResponseEntity.ok(existe);
    }

    @GetMapping("/existe-username")
    public ResponseEntity<Boolean> existeUsername(@RequestParam String username) {
        boolean existe = usuarioService.existeUsername(username);
        return ResponseEntity.ok(existe);
    }

    @GetMapping("/existe-cedula")
    public ResponseEntity<Boolean> existeCedula(@RequestParam String cedula) {
        boolean existe = usuarioService.existeCedula(cedula);
        return ResponseEntity.ok(existe);
    }

    @PostMapping("/cambiar-estado/{id}/{estado}")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long id, @PathVariable boolean estado) {
        try {
            usuarioService.cambioEstado(id, estado);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    @GetMapping("/roles")
    public List<String> getDistinctRoles() {
        return usuarioService.findDistinctRoleBy();
    }
}
