package br.ifmg.produto1_2026.service;

import br.ifmg.produto1_2026.dto.PerfilDTO;
import br.ifmg.produto1_2026.dto.UsuarioDTO;
import br.ifmg.produto1_2026.entities.Usuario;
import br.ifmg.produto1_2026.repositories.UsuarioRepository;
import br.ifmg.produto1_2026.service.exepition.ErroNoBancoDeDados;
import br.ifmg.produto1_2026.service.exepition.RegistroNaoEncontrado;
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
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Page<UsuarioDTO> findAll(Pageable pageable){

        //Lista com os dados do BD
        Page<Usuario> usuarios = repository.findAll(pageable);


        return usuarios.map(UsuarioDTO::new);
    }

    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id){

        //buscamos no BD a usuario. O resultado é um objeto do tipo Optinal
        Optional<Usuario> opt = repository.findById(id);

        //buscamos a usuario dentro do objeto Optional
        Usuario usuario = opt.orElseThrow(() -> new RegistroNaoEncontrado("Usuario não encontrada"));

        //Convertemos a entidade em DTO
        return new UsuarioDTO(usuario);
    }


    @Transactional
    public UsuarioDTO insert (UsuarioDTO usuarioDTO){

        Usuario entity = new Usuario();
        entity.setNome(usuarioDTO.getNome());
        entity.setTelefone(usuarioDTO.getTelefone());
        entity.setEmail(usuarioDTO.getEmail());
        entity.setSenha(usuarioDTO.getSenha());

        Usuario novo = repository.save(entity);
        return new UsuarioDTO(novo);
    }


    @Transactional
    public void delete(Long id){

        if(!repository.existsById(id)){
            throw new RegistroNaoEncontrado("Usuario não encontrado, ao ser excluida");
        }

        try {
            repository.deleteById(id);
        }

        catch (DataIntegrityViolationException e){
            throw new ErroNoBancoDeDados(e.getMessage());
        }
    }


    public UsuarioDTO update(Long id, UsuarioDTO dto) {

        if(!repository.existsById(id)){
            throw new RegistroNaoEncontrado("Usuario não encontrado, para ser alterada");
        }

        Usuario entity = repository.getReferenceById(id);

        entity.setNome(dto.getNome());//sobrescreve o nome antigo pelo nome
        entity.setTelefone(dto.getTelefone());
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());
        entity = repository.save(entity);
        return new UsuarioDTO(entity);

    }
}