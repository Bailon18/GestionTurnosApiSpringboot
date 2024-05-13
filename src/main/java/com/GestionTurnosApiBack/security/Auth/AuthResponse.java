package com.GestionTurnosApiBack.security.Auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.GestionTurnosApiBack.model.entity.Modulo;
import com.GestionTurnosApiBack.model.entity.Servicio;


public class AuthResponse {

	private String token;
	private String bearer = "Bearer";
	private String nombreUsuario;
	private Collection<? extends GrantedAuthority> authorities;
	private Long usuarioId;
	private Modulo modulo;
	
	

	public AuthResponse() {
		super();
	}

	public AuthResponse(String token, String bearer, String nombreUsuario,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.token = token;
		this.bearer = bearer;
		this.nombreUsuario = nombreUsuario;
		this.authorities = authorities;
	}

	public AuthResponse(String token, String bearer, String nombreUsuario,
			Collection<? extends GrantedAuthority> authorities, Long usuarioId) {
		super();
		this.token = token;
		this.bearer = bearer;
		this.nombreUsuario = nombreUsuario;
		this.authorities = authorities;
		this.usuarioId = usuarioId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getBearer() {
		return bearer;
	}

	public void setBearer(String bearer) {
		this.bearer = bearer;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}


}