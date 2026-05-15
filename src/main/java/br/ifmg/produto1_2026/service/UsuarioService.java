package br.ifmg.produto1_2026.service;

import br.ifmg.produto1_2026.dto.PerfilDTO;
import br.ifmg.produto1_2026.dto.UsuarioDTO;
import br.ifmg.produto1_2026.dto.UsuarioInsertDTO;
import br.ifmg.produto1_2026.entities.Perfil;
import br.ifmg.produto1_2026.entities.Usuario;
import br.ifmg.produto1_2026.projections.UserDetailsProjection;
import br.ifmg.produto1_2026.repositories.PerfilRepository;
import br.ifmg.produto1_2026.repositories.UsuarioRepository;
import br.ifmg.produto1_2026.service.exception.ErroNoBancoDeDados;
import br.ifmg.produto1_2026.service.exception.RegistroNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder encoder;

    public Page<UsuarioDTO> findAll(Pageable pageable){

        //Lista com os dados do BD
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);


        return usuarios.map(UsuarioDTO::new);
    }

    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id){

        //buscamos no BD a usuario. O resultado é um objeto do tipo Optinal
        Optional<Usuario> opt = usuarioRepository.findById(id);

        //buscamos a usuario dentro do objeto Optional
        Usuario usuario = opt.orElseThrow(() -> new RegistroNaoEncontrado("Usuario não encontrada"));

        //Convertemos a entidade em DTO
        return new UsuarioDTO(usuario);
    }


    @Transactional
    public UsuarioDTO insert (UsuarioInsertDTO dto){

        Usuario entity = new Usuario();
        copyDtoToEntity(dto, entity);
        entity.setSenha(encoder.encode(dto.getSenha()));


        Usuario novo = usuarioRepository.save(entity);
        return new UsuarioDTO(novo);
    }


    @Transactional
    public void delete(Long id){

        if(!usuarioRepository.existsById(id)){
            throw new RegistroNaoEncontrado("Usuario não encontrado, ao ser excluida");
        }

        try {
            usuarioRepository.deleteById(id);
        }

        catch (DataIntegrityViolationException e){
            throw new ErroNoBancoDeDados(e.getMessage());
        }
    }


    public UsuarioDTO update(Long id, UsuarioDTO dto) {

        if(!usuarioRepository.existsById(id)){
            throw new RegistroNaoEncontrado("Usuario não encontrado, para ser alterada");
        }

        Usuario entity = usuarioRepository.getReferenceById(id);

        copyDtoToEntity(dto, entity);

        entity = usuarioRepository.save(entity);
        return new UsuarioDTO(entity);

    }

    private void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
        entity.setNome(dto.getNome());//sobrescreve o nome antigo pelo nome
        entity.setTelefone(dto.getTelefone());
        entity.setEmail(dto.getEmail());

        entity.getPerfis().clear();
        for(PerfilDTO perfDto : dto.getPerfis()){
            Perfil perf = perfilRepository.getReferenceById(perfDto.getId());
            entity.getPerfis().add(perf);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserDetailsProjection> dados =
        usuarioRepository.loadUserByUserName(username);

        if(dados.isEmpty()){
            throw new UsernameNotFoundException(username);
        }

        Usuario usuario = new Usuario();

        usuario.setSenha(dados.getFirst().getPassword());
        usuario.setEmail(dados.getFirst().getUsername());

        for(UserDetailsProjection dado:dados){
            usuario.addRole(
                    new Perfil(dado.getRoleId(),
                            dado.getAuthority()
                    )
            );
        }

        return usuario;
    }
}
