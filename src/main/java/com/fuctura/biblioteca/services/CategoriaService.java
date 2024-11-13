package com.fuctura.biblioteca.services;

import com.fuctura.biblioteca.dtos.CategoriaDTO;
import com.fuctura.biblioteca.exceptions.DataIntegrityViolationException;
import com.fuctura.biblioteca.exceptions.IllegalArgumentException;
import com.fuctura.biblioteca.exceptions.ObjectNotFoundExeption;
import com.fuctura.biblioteca.models.Categoria;
import com.fuctura.biblioteca.models.Livro;
import com.fuctura.biblioteca.repositories.CategoriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CategoriaDTO findById(Integer id) {
        Optional<Categoria> cat = categoriaRepository.findById(id);
        if(cat.isPresent()){
            return modelMapper.map(cat.orElse(null), CategoriaDTO.class);
        }else {
            throw new ObjectNotFoundExeption("Categoria não Encontrada");
        }
    }

    public List<CategoriaDTO> findAll() {
        List<Categoria>lcat=categoriaRepository.findAll();
        return lcat.stream().map(c -> modelMapper.map(c , CategoriaDTO.class)).collect(Collectors.toList());
    }

    public CategoriaDTO save(CategoriaDTO categoriaDTO) {
        findByNome(categoriaDTO);
        Categoria cat = categoriaRepository.save(modelMapper.map(categoriaDTO, Categoria.class));
        return modelMapper.map(cat, CategoriaDTO.class);
    }

    public CategoriaDTO update(CategoriaDTO categoriaDTO) {
        findById(categoriaDTO.getId());
        findByNome(categoriaDTO);
        Categoria cat = categoriaRepository.save(modelMapper.map(categoriaDTO, Categoria.class));
        return modelMapper.map(cat, CategoriaDTO.class);
    }

    public void delete(Integer id){
        findById(id);
        Optional<Categoria> cat = categoriaRepository.findById(id);
        if(!cat.get().getLivros().isEmpty()){
            throw new DataIntegrityViolationException("Não é possivel remover a categoria por existir livros");
        }
        categoriaRepository.deleteById(id);
    }

    private void findByNome(CategoriaDTO categoriaDTO){
        Optional<Categoria> cat = categoriaRepository.findByNome(categoriaDTO.getNome());
        if(cat.isPresent() && cat.get().getNome().equalsIgnoreCase(categoriaDTO.getNome())){
            throw new IllegalArgumentException("Categoria já cadastrada");
        }
    }

    public void buscarNome(String nome){
        Optional<Categoria> cat = categoriaRepository.findByNomeContainingIgnoreCase(nome);
        if (cat.isEmpty()){
            throw new ObjectNotFoundExeption("Categoria não existe!");
        }

    }
}
