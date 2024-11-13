package com.fuctura.biblioteca.controllers;

import com.fuctura.biblioteca.dtos.LivroDTO;
import com.fuctura.biblioteca.models.Livro;
import com.fuctura.biblioteca.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livro")
@CrossOrigin("*")
public class LivroControlle {

    @Autowired
    private LivroService livroService;

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> findById(@PathVariable Integer id) {
        Livro livro = livroService.findById(id);
        return ResponseEntity.ok().body(new LivroDTO(livro));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<LivroDTO>> findAll() {
        List<Livro> list = livroService.findAll();
        return ResponseEntity.ok().body(list.stream().map(obj -> new LivroDTO(obj)).collect(Collectors.toList()));
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<LivroDTO>> findCategoriaById(@RequestParam(value = "id_cat", defaultValue = "0") Integer id) {
        List<Livro> list = livroService.findCategoriaById(id);
        return ResponseEntity.ok().body(list.stream().map(obj -> new LivroDTO(obj)).collect(Collectors.toList()));
    }

    @GetMapping("/categoria/{nome}")
    public ResponseEntity<List<LivroDTO>> findCategoriaByNome(@PathVariable String nome) {
        List<Livro> list = livroService.findByNome(nome);
        return ResponseEntity.ok().body(list.stream().map(obj -> new LivroDTO(obj)).collect(Collectors.toList()));
    }

    @PostMapping()
    public ResponseEntity<LivroDTO> save(@RequestParam(value = "id_cat", defaultValue = "0") Integer id, @RequestBody LivroDTO livroDTO) {
        return ResponseEntity.ok().body(new LivroDTO(livroService.save(id, livroDTO)));
    }

    @PutMapping()
    public ResponseEntity<LivroDTO> update(@RequestParam(value = "id_livro", defaultValue = "0") Integer id, @RequestBody LivroDTO livroDTO) {
        return ResponseEntity.ok().body(new LivroDTO(livroService.update(id, new Livro(livroDTO))));
    }


}
