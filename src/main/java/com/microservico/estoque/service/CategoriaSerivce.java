package com.microservico.estoque.service;

import com.microservico.estoque.domain.Categoria;
import com.microservico.estoque.domain.Cidade;
import com.microservico.estoque.repository.CategoriaRepository;
import com.microservico.estoque.repository.CidadeRepository;
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
public class CategoriaSerivce implements AbstractService<Categoria> {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Optional<Categoria> findByCode(Long code) {
        return this.categoriaRepository.findById(code);
    }

    @Override
    public Collection<Categoria> findAll() {
        return this.categoriaRepository.findAll();
    }

    @Override
    public Categoria save(Categoria cidade) {
        return this.categoriaRepository.save(cidade);
    }

    @Override
    public void delete(Long code) {
        categoriaRepository.findById(code).ifPresent(categoria -> {
            categoriaRepository.delete(categoria);
        });
    }

    public Categoria edit(Categoria categoria) {
        Optional<Categoria> categoriaSalva = findByCode(categoria.getCodigoCategoria());
        if (categoriaSalva.get() == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(categoria, categoriaSalva, "code");
        return this.categoriaRepository.save(categoriaSalva.get());
    }

}
