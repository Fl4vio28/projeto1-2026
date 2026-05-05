package br.ifmg.produto1_2026.service;

import br.ifmg.produto1_2026.dto.PerfilDTO;
import br.ifmg.produto1_2026.dto.UsuarioDTO;
import br.ifmg.produto1_2026.dto.UsuarioInsertDTO;
import br.ifmg.produto1_2026.entities.Perfil;
import br.ifmg.produto1_2026.entities.Usuario;
import br.ifmg.produto1_2026.repositories.PerfilRepository;
import br.ifmg.produto1_2026.repositories.UsuarioRepository;
import br.ifmg.produto1_2026.service.exepition.ErroNoBancoDeDados;
import br.ifmg.produto1_2026.service.exepition.RegistroNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Transactional(readOnly = true)
    public Page<UsuarioDTO> findAll(Pageable pageable) {
        Page<Usuario> usuarios = repository.findAll(pageable);
        return usuarios.map(UsuarioDTO::new);
    }

    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id) {
        Optional<Usuario> opt = repository.findById(id);
        Usuario usuario = opt.orElseThrow(() -> new RegistroNaoEncontrado("Usuario não encontrado"));
        return new UsuarioDTO(usuario);
    }

    @Transactional
    public UsuarioDTO insert(UsuarioInsertDTO dto) {
        Usuario entity = new Usuario();
        entity.setNome(dto.getNome());
        entity.setTelefone(dto.getTelefone());
        entity.setEmail(dto.getEmail());
        entity.setSenha(encoder.encode(dto.getSenha()));
        entity.getPerfis().clear();
        for (PerfilDTO perfilDTO : dto.getPerfis()) {
            Perfil perfil = perfilRepository.getReferenceById(perfilDTO.getId());
            entity.getPerfis().add(perfil);
        }
        Usuario novo = repository.save(entity);
        return new UsuarioDTO(novo);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RegistroNaoEncontrado("Usuario não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ErroNoBancoDeDados(e.getMessage());
        }
    }

    @Transactional
    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        if (!repository.existsById(id)) {
            throw new RegistroNaoEncontrado("Usuario não encontrado");
        }
        Usuario entity = repository.getReferenceById(id);
        entity.setNome(dto.getNome());
        entity.setTelefone(dto.getTelefone());
        entity.setEmail(dto.getEmail());
        entity.getPerfis().clear();
        for (PerfilDTO perfilDTO : dto.getPerfis()) {
            Perfil perfil = perfilRepository.getReferenceById(perfilDTO.getId());
            entity.getPerfis().add(perfil);
        }
        entity = repository.save(entity);
        return new UsuarioDTO(entity);
    }
}