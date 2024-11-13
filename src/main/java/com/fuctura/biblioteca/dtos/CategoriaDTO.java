package com.fuctura.biblioteca.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class CategoriaDTO {
    private Integer id;

    @NotEmpty(message = "O Campo Nome é Obrigatorio")
    @Length(min = 3, max = 30, message = "Nome deve conter entre 3 e 30 caracter")
    private String nome;
    private String descricao;

    public CategoriaDTO(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public CategoriaDTO() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
