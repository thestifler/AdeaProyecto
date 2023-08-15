package com.mauramirez.adeaproject.service;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mauramirez.adeaproject.domain.Usuario;

public interface IUsuarioService extends UserDetailsService {
    public List<Usuario> findAll();

    public void save(Usuario usuario);

    public void update(Usuario usuario);

    public Usuario fyndbyLogin(Usuario usuario);

    public void delete(Usuario usuario);

    public List<Usuario> getUsersByFilters(Usuario usuario);

    List<Usuario> fyndByStatus(String status);

    List<Usuario> fyndByStatusAndNombre(String status, String Nombre);

    List<Usuario> fyndByStatusAndFechaAltaBetween(String status, Date fechaInicial, Date fechaFinal);
}
