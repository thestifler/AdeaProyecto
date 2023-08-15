package com.mauramirez.adeaproject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mauramirez.adeaproject.domain.Usuario;
import com.mauramirez.adeaproject.service.IUsuarioService;

import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AdeaProjectController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/")
    private String test(Model model) {
        var usuario = usuarioService.findAll();
        var usuarioFiltro = new Usuario();
        model.addAttribute("usuarios", usuario);
        model.addAttribute("usuarioFiltro", usuarioFiltro);

        log.info(usuario.toString());
        return "index";
    }

    @GetMapping("/usuario")
    private String Usuarios(Usuario usuario, Model model) {
        model.addAttribute("usuarioexiste", "");
        return "FormularioUsuario";
    }

    @PostMapping("/usuario/guardar")
    public String guardarUsuario(@Valid Usuario usuario, Errors errores, Model model) {

        try {
            if (errores.hasErrors()) {
                model.addAttribute("usuario", usuario);
                log.info(errores.toString());
                return "FormularioUsuario";
            }

            if (usuarioService.fyndbyLogin(usuario) == null) {
                Date fechaAlta = new Date();

                usuario.setStatus("A");
                usuario.setFechaAlta(fechaAlta);
                usuario.setFechaModificacion(fechaAlta);
                usuario.setIntentos((float) 0);
                usuarioService.save(usuario);
                return "redirect:/";
            } else {
                model.addAttribute("usuario", usuario);
                model.addAttribute("usuarioexiste", "El Usuario Ya Existe");
                return "FormularioUsuario";
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw new UsernameNotFoundException("Usuario Existente");

        }

    }

    @PostMapping("/usuario/editar")
    public String editarUsuario(@Valid Usuario usuario, Errors errores, Model model) {

        try {
            if (errores.hasErrors()) {
                model.addAttribute("usuario", usuario);
                log.info(errores.toString());
                return "FormularioEditarUsuario";
            }

            usuarioService.update(usuario);
            return "redirect:/";

        } catch (Exception e) {
            log.info(e.getMessage());
            throw new UsernameNotFoundException("Usuario Existente");

        }

    }

    @GetMapping("/usuario/edit/{login}")
    public String datosPersona(Usuario usuario, Model model) {
        usuario = usuarioService.fyndbyLogin(usuario);
        model.addAttribute("usuario", usuario);
        return "FormularioEditarUsuario";
    }

    @GetMapping("/usuario/delete/{login}")
    public String deletePersona(Usuario usuario) {

        log.info("EL CLIENTE ES ");
        usuario = usuarioService.fyndbyLogin(usuario);
        log.info(usuario.toString());
        Date fechaBaja = new Date();
        usuario.setFechaBaja(fechaBaja);
        usuarioService.delete(usuario);
        return "redirect:/";
    }

    @PostMapping("/usuario/filtro")
    public String filtro(Usuario usuario, Errors errores, Model model) {

        List<Usuario> user = new ArrayList<>();

        if (usuario.getNombre() == null) {
            usuario.setNombre("");
        }

        if ((usuario.getFechaAlta() != null && usuario.getFechaBaja() != null)) {
            user = usuarioService.getUsersByFilters(usuario);
        } else {
            if (usuario.getNombre().isEmpty()) {
                user = usuarioService.fyndByStatus(usuario.getStatus());
            } else {
                user = usuarioService.fyndByStatusAndNombre(usuario.getStatus(), '%' + usuario.getNombre() + '%');
            }

        }

        model.addAttribute("usuarioFiltro", usuario);
        model.addAttribute("usuarios", user);
        return "index";
    }

    @GetMapping("/login")
    public String iniciarSesion() {
        return "login";
    }
}
