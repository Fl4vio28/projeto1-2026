package br.ifmg.produto1_2026.repositories;

import br.ifmg.produto1_2026.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

public Usuario findByEmail(String email);
}
