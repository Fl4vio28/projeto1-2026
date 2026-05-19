package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.dto.CategoriaDTO;
import br.ifmg.produto1_2026.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'VENDEDOR', 'CLIENTE')")
    @GetMapping
    public ResponseEntity<Page<CategoriaDTO>> categoria(Pageable pageable) {
        Page<CategoriaDTO> categorias = categoriaService.findAll(pageable);
        return ResponseEntity.ok().body(categorias);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'VENDEDOR', 'CLIENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> categoria(@PathVariable Long id) {
        CategoriaDTO dto = categoriaService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'VENDEDOR')")
    @PostMapping
    public ResponseEntity<CategoriaDTO> insert(@RequestBody CategoriaDTO dto) {
        CategoriaDTO retorno = categoriaService.insert(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(retorno.getId()).toUri();
        return ResponseEntity.created(location).body(retorno);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'VENDEDOR')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @RequestBody CategoriaDTO dto) {
        CategoriaDTO retorno = categoriaService.update(id, dto);
        return ResponseEntity.ok().body(retorno);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}