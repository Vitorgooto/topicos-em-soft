package com.loja.loja.controller;

import com.loja.loja.model.ProdutosModel;
import com.loja.loja.service.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutosService service;

    @PostMapping
    public ResponseEntity<ProdutosModel> criarProduto(@RequestBody ProdutosModel produtosModel) {
        ProdutosModel response = service.criarProduto(produtosModel);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProdutosModel>> listarProdutos() {
        List<ProdutosModel> lista = service.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutosModel> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutosModel> atualizarProduto(@PathVariable Long id, @RequestBody ProdutosModel produtosModel) {
        return service.atualizarProduto(id, produtosModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable Long id) {
        return service.deletarProduto(id);
    }
}
