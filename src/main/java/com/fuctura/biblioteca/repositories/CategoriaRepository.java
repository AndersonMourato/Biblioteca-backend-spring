package com.fuctura.biblioteca.repositories;

import com.fuctura.biblioteca.models.Categoria;
import com.fuctura.biblioteca.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Optional<Categoria> findByNome(String nome);

    Optional<Categoria> findByNomeContainingIgnoreCase(String nome);

}
