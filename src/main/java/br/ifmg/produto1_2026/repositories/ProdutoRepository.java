package br.ifmg.produto1_2026.repositories;

import br.ifmg.produto1_2026.entitites.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
