package br.ifmg.produto1_2026.service;
import br.ifmg.produto1_2026.dto.CategoriaDTO;
import br.ifmg.produto1_2026.repositories.CategoriaRepository;
import br.ifmg.produto1_2026.service.exepition.ErroNoBancoDeDados;
import br.ifmg.produto1_2026.service.exepition.RegistroNaoEncontrado;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.ifmg.produto1_2026.entities.Categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Page<CategoriaDTO> findAll(Pageable pageable) {
        //Lista com dados do BD
        Page<Categoria> categorias = categoriaRepository.findAll(pageable);

        return categorias.map(x -> new CategoriaDTO(x));
    }

    @Transactional(readOnly = true)
    public CategoriaDTO findById(Long id) {
        Optional<Categoria> opt = categoriaRepository.findById(id);
        Categoria categoria = opt.orElseThrow(() -> new RegistroNaoEncontrado("Categoria não encontrada."));
        return new CategoriaDTO(categoria);
    }
    @Transactional
    public CategoriaDTO insert(CategoriaDTO dto) {

        Categoria entity = new Categoria();
        entity.setNome(dto.getNome());

       Categoria nova = categoriaRepository.save(entity);
       return new CategoriaDTO(entity);
    }

    @Transactional
    public  void delete(Long id) {

        if (!categoriaRepository.existsById(id)) {
            throw new RegistroNaoEncontrado("Categoria não encontrada  ao ser excluida.");
        }

        try {
            categoriaRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new ErroNoBancoDeDados(e.getMessage());
        }
        categoriaRepository.deleteById(id);
    }

    public CategoriaDTO update(Long id, CategoriaDTO dto) {

        if (!categoriaRepository.existsById(id)) {
            throw new RegistroNaoEncontrado("Categoria não encontrada.");
        }
            Categoria entity = categoriaRepository.getReferenceById(id);
        entity.setNome(dto.getNome()); //sobrescrevi o nome antigo
        entity = categoriaRepository.save(entity);
        return new CategoriaDTO(entity);

    }
}