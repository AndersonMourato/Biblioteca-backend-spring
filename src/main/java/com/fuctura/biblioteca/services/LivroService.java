package com.fuctura.biblioteca.services;

import com.fuctura.biblioteca.dtos.CategoriaDTO;
import com.fuctura.biblioteca.dtos.LivroDTO;
import com.fuctura.biblioteca.exceptions.ObjectNotFoundExeption;
import com.fuctura.biblioteca.models.Categoria;
import com.fuctura.biblioteca.models.Livro;
import com.fuctura.biblioteca.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaService categoriaService;

    public Livro findById(Integer id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            return livro.get();
        }
        throw new ObjectNotFoundExeption("Livro não encontrado!");
    }

    public List<Livro> findCategoriaById(Integer id_cat) {
        categoriaService.findById(id_cat);
        return livroRepository.findAllLivrosByCategoria(id_cat);
    }

    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    public List<Livro> findByNome(String nome) {
        categoriaService.buscarNome(nome);
        return livroRepository.findByCategoriaNomeContainingIgnoreCase(nome);
    }

    public Livro save(Integer id, LivroDTO livroDTO) {
        livroDTO.setId(null);
        CategoriaDTO cat = categoriaService.findById(id);
        livroDTO.setCategoria(new Categoria(cat));
        return livroRepository.save(new Livro(livroDTO));
    }

    public Livro update(Integer id, Livro livro) {
        findById(id);
        livro.setId(id);
        return livroRepository.save(livro);
    }
}
