package br.ifmg.produto1_2026.service;

import br.ifmg.produto1_2026.dto.ProdutoDTO;
import br.ifmg.produto1_2026.entities.Produto;
import br.ifmg.produto1_2026.repositories.ProdutoRepository;
import br.ifmg.produto1_2026.service.exception.ErroNoBancoDeDados;
import br.ifmg.produto1_2026.service.exception.RegistroNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Page<ProdutoDTO> findAll(Pageable pageable){

        //Lista com os dados do BD
        Page<Produto> produtos = produtoRepository.findAll(pageable);

        return produtos.map(ProdutoDTO::new);
    }

    @Transactional(readOnly = true)
    public ProdutoDTO findById(Long id){

        //buscamos no BD a produto. O resultado é um objeto do tipo Optinal
        Optional<Produto> opt = produtoRepository.findById(id);

        //buscamos a produto dentro do objeto Optional
        Produto produto = opt.orElseThrow(() -> new RegistroNaoEncontrado("Produto não encontrada"));

        //Convertemos a entidade em DTO
        return new ProdutoDTO(produto);
    }


    @Transactional
    public ProdutoDTO insert (ProdutoDTO produtoDTO){

        Produto entity = new Produto();
        entity.setNome(produtoDTO.getNome());

        Produto nova = produtoRepository.save(entity);
        return new ProdutoDTO(entity);
    }


    @Transactional
    public void delete(Long id){

        if(!produtoRepository.existsById(id)){
            throw new RegistroNaoEncontrado("Produto não encontrado, ao ser excluida");
        }

        try {
            produtoRepository.deleteById(id);
        }

        catch (DataIntegrityViolationException e){
            throw new ErroNoBancoDeDados(e.getMessage());
        }
    }


    public ProdutoDTO update(Long id, ProdutoDTO dto) {

        if(!produtoRepository.existsById(id)){
            throw new RegistroNaoEncontrado("Produto não encontrado, para ser alterada");
        }

        Produto entity = produtoRepository.getReferenceById(id);

        entity.setNome(dto.getNome());//sobrescrevi o nome antigo pelo nome
        entity = produtoRepository.save(entity);
        return new ProdutoDTO(entity);

    }
}