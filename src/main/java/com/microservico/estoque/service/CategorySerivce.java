package com.microservico.estoque.service;

import com.microservico.estoque.common.RabbitmqConstantes;
import com.microservico.estoque.domain.Category;
import com.microservico.estoque.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class CategorySerivce implements AbstractService<Category> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RabbitMQExchange exchange;

    @Override
    @RabbitListener(queues = RabbitmqConstantes.QUEUE_CATEGORY)
    public Optional<Category> findByCode(Long code) {
        return this.categoryRepository.findById(code);
    }

    @Override
    public Collection<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category save(Category cidade) {
        return this.categoryRepository.save(cidade);
    }

    @Override
    public void delete(Long code) {
        categoryRepository.findById(code).ifPresent(category -> {
            categoryRepository.delete(category);
        });
    }

    public Category edit(Category category) {
        Optional<Category> categoriaSalva = findByCode(category.getCodigoCategoria());
        if (categoriaSalva.get() == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(category, categoriaSalva, "codigoCategoria");
        return this.categoryRepository.save(categoriaSalva.get());
    }

}
