package com.loja.loja.service;

import com.loja.loja.model.ProdutosModel;
import com.loja.loja.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutosService {
    @Autowired
    private ProdutosRepository repository;

    public ProdutosModel criarProduto(ProdutosModel produto) {
        return repository.save(produto);
    }

    public List<ProdutosModel> listarProdutos() {
        return repository.findAll();
    }

    public ProdutosModel buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public ProdutosModel atualizarProduto(Long id, ProdutosModel produto) {
        ProdutosModel produtoExistente = buscarPorId(id);
        produtoExistente.setNome(produto.getNome());
        produtoExistente.setPreco(produto.getPreco());
        produtoExistente.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque());
        return repository.save(produtoExistente);
    }

    public void removerProduto(Long id) {
        ProdutosModel produto = buscarPorId(id);
        repository.delete(produto);
    }
} 