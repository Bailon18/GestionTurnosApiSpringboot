package com.GestionTurnosApiBack.security.Entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username); 
    boolean existsByCorreoElectronico(String correoElectronico);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM user WHERE username = :username", nativeQuery = true)
    int existsByUsername(String username);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM user WHERE cedula = :cedula", nativeQuery = true)
    int existsByCedula(@Param("cedula") String cedula);

    @Query(value = "SELECT DISTINCT u.role FROM user u", nativeQuery = true)
    List<String> findDistinctRoleBy();
}
