package com.loja.loja.service;

import com.loja.loja.model.ProdutosModel;
import com.loja.loja.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutosService {
    @Autowired
    private ProdutosRepository repository;

    public ResponseEntity<ProdutosModel> criarProduto(ProdutosModel produto) {
        return ResponseEntity.ok(repository.save(produto));
    }

    public ResponseEntity<List<ProdutosModel>> listarProdutos() {
        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity<ProdutosModel> buscarPorId(Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<ProdutosModel> atualizarProduto(Long id, ProdutosModel produto) {
        return repository.findById(id).map(p -> {
            p.setNome(produto.getNome());
            p.setPreco(produto.getPreco());
            p.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque());
            return ResponseEntity.ok(repository.save(p));
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> removerProduto(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}


