package com.loja.loja.controller;

import com.loja.loja.model.ProdutosModel;
import com.loja.loja.service.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {
    @Autowired
    private ProdutosService service;

    @PostMapping
    public ResponseEntity<ProdutosModel> criarProduto(@RequestBody ProdutosModel produto) {
        return service.criarProduto(produto);
    }

    @GetMapping
    public ResponseEntity<List<ProdutosModel>> listarProdutos() {
        return service.listarProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutosModel> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutosModel> atualizarProduto(@PathVariable Long id, @RequestBody ProdutosModel produto) {
        return service.atualizarProduto(id, produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerProduto(@PathVariable Long id) {
        return service.removerProduto(id);
    }
}
