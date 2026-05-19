package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.dto.UsuarioDTO;
import br.ifmg.produto1_2026.dto.UsuarioInsertDTO;
import br.ifmg.produto1_2026.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> usuarios(Pageable pageable) {
        Page<UsuarioDTO> usuarios = usuarioService.findAll(pageable);
        return ResponseEntity.ok().body(usuarios);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> usuario(@PathVariable Long id) {
        UsuarioDTO dto = usuarioService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    @PostMapping
    public ResponseEntity<UsuarioDTO> insert(@RequestBody @Valid UsuarioInsertDTO dto) {
        UsuarioDTO retorno = usuarioService.insert(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(retorno.getId()).toUri();
        return ResponseEntity.created(location).body(retorno);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody @Valid UsuarioInsertDTO dto) {
        UsuarioDTO retorno = usuarioService.update(id, dto);
        return ResponseEntity.ok().body(retorno);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}