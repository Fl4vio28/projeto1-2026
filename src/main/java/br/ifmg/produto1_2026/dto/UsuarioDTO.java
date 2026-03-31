package br.ifmg.produto1_2026.dto;

import br.ifmg.produto1_2026.entities.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private List<PerfilDTO> perfis = new ArrayList<>();
    public UsuarioDTO() {}

    public UsuarioDTO(Long id, String nome, String telefone, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }

    public UsuarioDTO(Usuario entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.telefone = entity.getTelefone();
        this.email = entity.getEmail();
        this.senha = entity.getSenha();

        entity.getPerfis().forEach(role -> this.perfis.add(new PerfilDTO(role)));
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

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}