package br.ifmg.produto1_2026.dto;

import br.ifmg.produto1_2026.entities.Usuario;

public class UsuarioInsertDTO extends UsuarioDTO {

    private String senha;

    public UsuarioInsertDTO() {}

    public UsuarioInsertDTO(Long id, String nome, String telefone, String email, String senha) {
        super(id, nome, telefone, email);
        this.senha = senha;
    }

    public UsuarioInsertDTO(Usuario entity) {
        super(entity);
        this.senha = entity.getSenha();
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "UsuarioInsertDTO{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}