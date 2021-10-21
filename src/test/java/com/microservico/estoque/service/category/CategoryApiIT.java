package com.microservico.estoque.service.category;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/pesquisa-categoria.feature",
        plugin = { "pretty"},
        glue = { "com.microservico.estoque.service.category" }
)
public class CategoryApiIT {
}
