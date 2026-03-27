package br.ifmg.produto1_2026.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String senha;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant criadoEm;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant atualizadoEm;

    public Usuario() {
    }

    public Usuario(Long id, String nome, String telefone, String email, String senha, Instant criadoEm, Instant atualizadoEm) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Instant criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Instant getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(Instant atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(nome, usuario.nome) && Objects.equals(telefone, usuario.telefone) && Objects.equals(email, usuario.email) && Objects.equals(senha, usuario.senha) && Objects.equals(criadoEm, usuario.criadoEm) && Objects.equals(atualizadoEm, usuario.atualizadoEm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, telefone, email, senha, criadoEm, atualizadoEm);
    }

    @PrePersist
    public void prePersist() {
        this.criadoEm = Instant.now();
    }
    @PreUpdate
    public void preUpdate() {
        this.atualizadoEm = Instant.now();
    }
}

