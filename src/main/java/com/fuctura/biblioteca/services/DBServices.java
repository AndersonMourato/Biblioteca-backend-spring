package com.fuctura.biblioteca.services;

import com.fuctura.biblioteca.enuns.Tamanho;
import com.fuctura.biblioteca.models.Categoria;
import com.fuctura.biblioteca.models.Livro;
import com.fuctura.biblioteca.repositories.CategoriaRepository;
import com.fuctura.biblioteca.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBServices {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LivroRepository livroRepository;

    public void instaciaDB(){
        Categoria cat1 = new Categoria(null, "Informática", "Livro de informática");
        Categoria cat2 = new Categoria(null, "Ficção Científica", "Ficção Científica");
        Categoria cat3 = new Categoria(null, "Biografias", "Livros de Biografias");
        Categoria cat4 = new Categoria(null, "Infantil", "Livros Infantis");

        Livro l1 = new Livro(null, "Clean code", "Robertin Martin", "A expressão Lorem ipsum em design gráfico e editoração é um texto padrão em latim utilizado na produção gráfica para preencher os espaços de texto em publicações para testar e ajustar aspectos visuais antes de utilizar conteúdo real.", Tamanho.MEDIO, cat1);
        Livro l2 = new Livro(null, "Engenharia de Software", "Louis V. Gerstner", "A expressão Lorem ipsum em design gráfico e editoração é um texto padrão em latim utilizado na produção gráfica para preencher os espaços de texto em publicações para testar e ajustar aspectos visuais antes de utilizar conteúdo real.", Tamanho.GRANDE, cat1);
        Livro l3 = new Livro(null, "The time machine", "H. G. Wells", "A expressão Lorem ipsum em design gráfico e editoração é um texto padrão em latim utilizado na produção gráfica para preencher os espaços de texto em publicações para testar e ajustar aspectos visuais antes de utilizar conteúdo real.", Tamanho.MEDIO, cat2);
        Livro l4 = new Livro(null, "The war of the worlds", "H. G. Wells", "A expressão Lorem ipsum em design gráfico e editoração é um texto padrão em latim utilizado na produção gráfica para preencher os espaços de texto em publicações para testar e ajustar aspectos visuais antes de utilizar conteúdo real.", Tamanho.PEQUENO, cat2);
        Livro l5 = new Livro(null, "A expressão", "Isaac Asimov", "A expressão Lorem ipsum em design gráfico e editoração é um texto padrão em latim utilizado na produção gráfica para preencher os espaços de texto em publicações para testar e ajustar aspectos visuais antes de utilizar conteúdo real.", Tamanho.GRANDE, cat2);
        Livro l6 = new Livro(null, "Lorem ipsum", "Isaac Asimov", "A expressão Lorem ipsum em design gráfico e editoração é um texto padrão em latim utilizado na produção gráfica para preencher os espaços de texto em publicações para testar e ajustar aspectos visuais antes de utilizar conteúdo real.", Tamanho.GRANDE, cat4);

        cat1.getLivros().addAll(Arrays.asList(l1, l2));
        cat2.getLivros().addAll(Arrays.asList(l3, l4, l5));
        cat4.getLivros().addAll(Arrays.asList(l6));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
        livroRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5, l6));
    }
}
