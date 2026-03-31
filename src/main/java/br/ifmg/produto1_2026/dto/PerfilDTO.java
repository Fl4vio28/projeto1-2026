package br.ifmg.produto1_2026.dto;

import br.ifmg.produto1_2026.entities.Perfil;
import br.ifmg.produto1_2026.entities.Usuario;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_perfil")
public class PerfilDTO { //POJO

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public PerfilDTO() {}

    public PerfilDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    public PerfilDTO(Perfil perfil) {
        this.id = perfil.getId();
        this.nome = perfil.getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

