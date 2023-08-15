package com.mauramirez.adeaproject.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mauramirez.adeaproject.dao.IUsuarioDao;
import com.mauramirez.adeaproject.domain.Usuario;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioDao iusuariodao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) iusuariodao.findAll();
    }

    @Override
    public void save(Usuario usuario) {

        Usuario usuarioBD = iusuariodao.fyndByLogin(usuario.getLogin());

        if (usuarioBD == null) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            log.warn(usuario.getPassword());
            iusuariodao.save(usuario);

        } else {
            throw new UsernameNotFoundException("Usuario Existente");
        }

    }

    @Override
    public Usuario fyndbyLogin(Usuario usuario) {

        return iusuariodao.findById(usuario.getLogin()).orElse(null);
    }

    @Override
    public void delete(Usuario usuario) {
        usuario.setStatus("B");
        iusuariodao.save(usuario);
    }

    @Override
    public List<Usuario> getUsersByFilters(Usuario usuario) {

        return iusuariodao.findByStatusAndNombreContainingAndFechaAltaBetween(
                usuario.getStatus(), usuario.getNombre(), usuario.getFechaAlta(), usuario.getFechaBaja());
    }

    @Override
    public List<Usuario> fyndByStatus(String status) {
        return iusuariodao.fyndByStatus(status);
    }

    @Override
    public List<Usuario> fyndByStatusAndFechaAltaBetween(String status, Date fechaInicial, Date fechaFinal) {
        return iusuariodao.fyndByStatusAndFechaAltaBetween(status, fechaInicial, fechaFinal);
    }

    @Override
    public List<Usuario> fyndByStatusAndNombre(String status, String Nombre) {
        return iusuariodao.fyndByStatusAndNombre(status, Nombre);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("loadUserByUsername: CARGANDO USUARIO");
        Usuario usuario = iusuariodao.fyndByLogin(username);
        Usuario usuarioLogin = new Usuario();
        Date fechaActual = new Date();
        if (usuario != null) {

            usuarioLogin = iusuariodao.login(usuario.getLogin(), usuario.getPassword(),
                    fechaActual);
        }
        if (usuarioLogin == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }
        var rol = new ArrayList();
        rol.add("ADMIN");
        return new User(usuarioLogin.getLogin(), usuarioLogin.getPassword(), new ArrayList<>());
    }

    @Override
    public void update(Usuario usuario) {

        Usuario usuarioBD = iusuariodao.fyndByLogin(usuario.getLogin());

        if (usuario.getApellidoMaterno() == null) {
            if (usuarioBD.getApellidoMaterno() == null || usuarioBD.getApellidoMaterno().isEmpty()) {
                usuarioBD.setApellidoMaterno("");
            }

        } else {
            usuarioBD.setApellidoMaterno(usuario.getApellidoMaterno());
        }
        if (usuario.getApellidoPaterno() == null) {
            if (usuarioBD.getApellidoPaterno() == null || usuarioBD.getApellidoPaterno().isEmpty()) {
                usuarioBD.setApellidoPaterno("");
            }

        } else {
            usuarioBD.setApellidoPaterno(usuario.getApellidoMaterno());
        }

        usuarioBD.setFechaVigencia(usuario.getFechaVigencia());
        usuarioBD.setArea(usuario.getArea());
        usuarioBD.setNombre(usuario.getNombre());
        usuarioBD.setEmail(usuario.getEmail());
        usuarioBD.setCliente(usuario.getCliente());

        iusuariodao.save(usuarioBD);

    }
}
