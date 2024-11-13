package com.fuctura.biblioteca.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fuctura.biblioteca.dtos.LivroDTO;
import com.fuctura.biblioteca.enuns.Tamanho;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String autor;
    private String texto;

    private Tamanho tamanho;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Livro() {
    }

    public Livro(Integer id, String nome, String autor, String texto, Tamanho tamanho, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.texto = texto;
        this.tamanho = tamanho;
        this.categoria = categoria;
    }

    public Livro(LivroDTO livroDTO) {
        this.id = livroDTO.getId();
        this.nome = livroDTO.getNome();
        this.autor = livroDTO.getAutor();
        this.texto = livroDTO.getTexto();
        this.tamanho = livroDTO.getTamanho();
        this.categoria = livroDTO.getCategoria();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
