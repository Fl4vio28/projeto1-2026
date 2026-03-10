package br.ifmg.produto1_2026.service;
import br.ifmg.produto1_2026.dto.CategoriaDTO;
import br.ifmg.produto1_2026.repositories.CategoriaRepository;
import br.ifmg.produto1_2026.service.exepition.RegistroNaoEncontrado;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.ifmg.produto1_2026.entities.Categoria;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import br.ifmg.produto1_2026.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> findAll() {
        //Lista com dados do BD
        List<Categoria> categorias = categoriaRepository.findAll();

        //Lista com os dados convertidos em DTO
        // List<CategoriaDTO> categoriasDTO = new ArrayList<>();

        //for (Categoria categoria : categorias) {
        //  categoriasDTO.add(new CategoriaDTO(categoria));
        //}

        return categorias.stream().map(x -> new CategoriaDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoriaDTO findById(Long id) {
        Optional<Categoria> opt = categoriaRepository.findById(id);
        Categoria categoria = opt.orElseThrow(() -> new RegistroNaoEncontrado("Categoria não encontrada."));
        return new CategoriaDTO(categoria);
    }
}