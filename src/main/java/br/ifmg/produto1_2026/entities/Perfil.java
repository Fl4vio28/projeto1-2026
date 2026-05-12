package br.ifmg.produto1_2026.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.util.Objects;


@Entity
@Table(name= "tb_perfil")
public class Perfil implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String autoridade;

    public Perfil() {
    }

    public Perfil( Long id ,String nome) {
        this.autoridade = nome;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return autoridade;
    }

    public void setNome(String nome) {
        this.autoridade = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Perfil perfil = (Perfil) o;
        return Objects.equals(id, perfil.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "id=" + id +
                ", nome='" + autoridade + '\'' +
                '}';
    }

    @Override
    public String getAuthority() {
        return autoridade;
    }
}
