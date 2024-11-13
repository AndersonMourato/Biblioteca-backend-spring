package com.fuctura.biblioteca.controllers;

import com.fuctura.biblioteca.dtos.CategoriaDTO;
import com.fuctura.biblioteca.services.CategoriaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(categoriaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll(){
        List<CategoriaDTO> listCategorias = categoriaService.findAll();
        return ResponseEntity.ok().body(listCategorias);
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> save(@Valid @RequestBody CategoriaDTO categoriaDTO){
        return ResponseEntity.ok(categoriaService.save(categoriaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable Integer id, @Valid @RequestBody CategoriaDTO categoriaDTO){
        categoriaDTO.setId(id);
        return ResponseEntity.ok(categoriaService.update(categoriaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
