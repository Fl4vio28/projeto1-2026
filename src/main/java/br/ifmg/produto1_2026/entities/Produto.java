package br.ifmg.produto1_2026.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "tb_produto")

public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(columnDefinition = "TEXT")
    private String descricao;
    private Double preco;
    private String imgUrl;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant criadoEm;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant atualizadoEm;

    @ManyToMany
    @JoinTable(name = "tb_produto_categoria", joinColumns = @JoinColumn(name = "id_prodto"), inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private Set<Categoria> categorias = new HashSet<Categoria>();

    public Produto() {}

    public Produto(Long id, String nome, String descricao, Double preco, String imgUrl, Instant criadoEm, Instant atualizadoEm) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imgUrl = imgUrl;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }


    public Instant getAtualizadoEm() {
        return atualizadoEm;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }

    @PrePersist
    public void prePersist() {this.criadoEm = Instant.now();}

    @PreUpdate
    public void preUpdate() {this.atualizadoEm = Instant.now();}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) && Objects.equals(nome, produto.nome) && Objects.equals(descricao, produto.descricao) && Objects.equals(preco, produto.preco) && Objects.equals(imgUrl, produto.imgUrl) && Objects.equals(criadoEm, produto.criadoEm) && Objects.equals(atualizadoEm, produto.atualizadoEm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, preco, imgUrl, criadoEm, atualizadoEm);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", imgUrl='" + imgUrl + '\'' +
                ", criadoEm=" + criadoEm +
                ", atualizadoEm=" + atualizadoEm +
                '}';
    }
}
