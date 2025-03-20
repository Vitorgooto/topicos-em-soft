package com.loja.loja.service;

import com.loja.loja.model.ProdutosModel;
import com.loja.loja.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutosService {

    @Autowired
    private ProdutosRepository produtosRepository;

    public List<ProdutosModel> findAll() {
        return produtosRepository.findAll();
    }

    public ResponseEntity<ProdutosModel> buscarPorId(Long id) {
        Optional<ProdutosModel> produto = produtosRepository.findById(id);
        return produto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    public ProdutosModel criarProduto(ProdutosModel produtosModel) {
        return produtosRepository.save(produtosModel);
    }

    public ResponseEntity<ProdutosModel> atualizarProduto(Long id, ProdutosModel produtosModel) {
        Optional<ProdutosModel> produtoExistente = produtosRepository.findById(id);
        if (produtoExistente.isPresent()) {
            ProdutosModel produtoAtualizado = produtoExistente.get();
            produtoAtualizado.setNome(produtosModel.getNome());
            produtoAtualizado.setPreco(produtosModel.getPreco());
            produtoAtualizado.setQuantidadeEmEstoque(produtosModel.getQuantidadeEmEstoque());
            produtosRepository.save(produtoAtualizado);
            return ResponseEntity.ok(produtoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> deletarProduto(Long id) {
        Optional<ProdutosModel> produto = produtosRepository.findById(id);
        if (produto.isPresent()) {
            produtosRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
