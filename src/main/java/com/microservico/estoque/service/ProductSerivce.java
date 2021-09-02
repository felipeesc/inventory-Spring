package com.microservico.estoque.service;

import com.microservico.estoque.domain.Produto;
import com.microservico.estoque.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ProductSerivce implements AbstractService<Produto> {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Optional<Produto> findByCode(Long code) {
        return this.produtoRepository.findById(code);
    }

    @Override
    public Collection<Produto> findAll() {
        return this.produtoRepository.findAll();
    }

    @Override
    public Produto save(Produto produto) {
        return this.produtoRepository.save(produto);
    }

    @Override
    public void delete(Long code) {
        produtoRepository.findById(code).ifPresent(produto -> {
            produtoRepository.delete(produto);
        });
    }

    public Produto edit(Produto produto) {
        Optional<Produto> produtoSalvo = findByCode(produto.getCodigoProduto());
        if (produtoSalvo.get() == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(produto, produtoSalvo, "codigoProduto");
        return this.produtoRepository.save(produtoSalvo.get());
    }

}
