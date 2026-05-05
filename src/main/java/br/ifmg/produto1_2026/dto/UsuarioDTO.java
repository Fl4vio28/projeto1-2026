package br.ifmg.produto1_2026.dto;

import br.ifmg.produto1_2026.entities.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO {

    private Long id;
    @NotBlank(message = "Campo nome obrigatorio")
    private String nome;
    private String telefone;
    @NotBlank(message = "Campo email obrigatorio")
    @Email(message = "Email inválido.")
    private String email;
    private List<PerfilDTO> perfis = new ArrayList<>();
    public UsuarioDTO() {}

    public UsuarioDTO(Long id, String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public UsuarioDTO(Usuario entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.telefone = entity.getTelefone();
        this.email = entity.getEmail();

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

    public List<PerfilDTO> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<PerfilDTO> perfis) {
        this.perfis = perfis;
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