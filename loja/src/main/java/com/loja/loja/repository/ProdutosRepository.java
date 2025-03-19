package com.loja.loja.repository;

import com.loja.loja.model.ProdutosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<ProdutosModel, Long> {}
