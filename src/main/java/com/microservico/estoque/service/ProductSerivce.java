package com.microservico.estoque.service;

import com.microservico.estoque.domain.Product;
import com.microservico.estoque.repository.ProductRepository;
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
public class ProductSerivce implements AbstractService<Product> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Optional<Product> findByCode(Long code) {
        return this.productRepository.findById(code);
    }

    @Override
    public Collection<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public void delete(Long code) {
        productRepository.findById(code).ifPresent(produto -> {
            productRepository.delete(produto);
        });
    }

    public Product edit(Product product) {
        Optional<Product> produtoSalvo = findByCode(product.getCodigoProduto());
        if (produtoSalvo.get() == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(product, produtoSalvo, "codigoProduto");
        return this.productRepository.save(produtoSalvo.get());
    }

}
