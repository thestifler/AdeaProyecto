package com.mauramirez.adeaproject.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mauramirez.adeaproject.domain.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, String> {

    List<Usuario> findByStatusAndNombreContainingAndFechaAltaBetween(
            String status,
            String nombre,
            Date fechaInicio,
            Date fechaFin);

    @Query("SELECT u FROM Usuario u WHERE u.status = :status")
    List<Usuario> fyndByStatus(String status);

    @Query("SELECT u FROM Usuario u WHERE u.status = :status AND u.nombre LIKE :nombre")
    List<Usuario> fyndByStatusAndNombre(String status, String nombre);

    @Query("SELECT u FROM Usuario u WHERE u.status = :status AND u.fechaAlta BETWEEN :fechaInicial AND :fechaFinal")
    List<Usuario> fyndByStatusAndFechaAltaBetween(String status, Date fechaInicial, Date fechaFinal);

    @Query("SELECT u FROM Usuario u WHERE u.login = :login AND u.password = :password AND  u.fechaVigencia >= :fechaVigencia")
    Usuario login(String login, String password, Date fechaVigencia);

    @Query("SELECT u FROM Usuario u WHERE u.login = :login ")
    Usuario fyndByLogin(String login);

}
