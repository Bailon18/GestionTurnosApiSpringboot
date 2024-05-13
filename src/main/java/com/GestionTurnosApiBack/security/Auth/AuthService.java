package com.GestionTurnosApiBack.security.Auth;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.GestionTurnosApiBack.security.Entity.User;
import com.GestionTurnosApiBack.security.Entity.UserRepository;
import com.GestionTurnosApiBack.security.Jwt.JwtService;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getContrasena()));
        
        UserDetails userDetails = userRepository.findByUsername(request.getUsername()).orElseThrow();
        User usuarioencontrado = userRepository.findByUsername(request.getUsername()).orElseThrow();

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwtService.getToken(userDetails));
        authResponse.setNombreUsuario(userDetails.getUsername());
        authResponse.setUsuarioId(usuarioencontrado.getId());
        authResponse.setAuthorities(authorities);
        authResponse.setBearer("Bearer");
        authResponse.setModulo(usuarioencontrado.getModulo());

        return authResponse;
    }

    public AuthResponse register(RegisterRequest request) {
        User user;
        if (request.getId() != null && userRepository.existsById(request.getId())) {
            // Si el ID existe, actualizamos el usuario existente
            user = userRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            user.setNombre(request.getNombre());
            user.setContrasena(passwordEncoder.encode(request.getContrasena()));
            user.setApellido(request.getApellido());
            user.setCorreoElectronico(request.getCorreoElectronico());
            user.setTelefono(request.getTelefono());
            user.setCedula(request.getCedula());
            user.setEstado(request.isEstado());
            user.setUsername(request.getUsername());
            user.setRole(request.getRole());
            user.setModulo(request.getModulo());
        } else {
            // Si no tiene ID, creamos un nuevo usuario
            user = new User();
            user.setNombre(request.getNombre());
            user.setContrasena(passwordEncoder.encode(request.getContrasena()));
            user.setApellido(request.getApellido());
            user.setCorreoElectronico(request.getCorreoElectronico());
            user.setTelefono(request.getTelefono());
            user.setCedula(request.getCedula());
            user.setEstado(request.isEstado());
            user.setUsername(request.getUsername());
            user.setRole(request.getRole());
            user.setModulo(request.getModulo());
        }

        userRepository.save(user);

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        Collection<? extends GrantedAuthority> authorities = Collections.singletonList(authority);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwtService.getToken(user));
        authResponse.setNombreUsuario(user.getUsername());
        authResponse.setAuthorities(authorities);
        authResponse.setBearer("Bearer");
        authResponse.setModulo(user.getModulo());

        return authResponse;
    }
 
}
