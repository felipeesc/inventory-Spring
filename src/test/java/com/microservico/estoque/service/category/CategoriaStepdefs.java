package com.microservico.estoque.service.category;

import com.microservico.estoque.EstoqueApplication;
import com.microservico.estoque.domain.Category;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
@SpringBootTest(classes = EstoqueApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoriaStepdefs {

    private ResponseEntity<Category> response;

    @Autowired
    private TestRestTemplate template;

    @Dado("Que a Categoria está inicializada")
    public void que_a_categoria_está_inicializada() {
    }

    @Quando("o cliente chamar \\/categoria\\/{long}")
    public void o_cliente_chamar_categoria(long code) {
        response = template.getForEntity("/categoria/" + Long.valueOf(code), Category.class);
    }

    @Entao("o cliente recebe a Categoria")
    public void oClienteRecebeACategoria() {
        Assert.assertNotNull(response);
    }
}
