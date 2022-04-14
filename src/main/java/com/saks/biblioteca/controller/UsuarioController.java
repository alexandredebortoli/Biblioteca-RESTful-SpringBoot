package com.saks.biblioteca.controller;

import com.saks.biblioteca.model.Usuario;
import com.saks.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @GetMapping(value = "{id}")
    public Optional<Usuario> listarTodos(@PathVariable Long id) {
        return usuarioRepository.findById(id);
    }

    @PostMapping
    public Usuario adicionar(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioRepository.findById(id).map(record -> {
            record.setNome(usuario.getNome());
            record.setEmail(usuario.getEmail());
            record.setSenha(usuario.getSenha());
            Usuario usuarioUpdated = usuarioRepository.save(record);
            return ResponseEntity.ok().body(usuarioUpdated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        return usuarioRepository.findById(id).map(record -> {
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
