package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.Output;
import com.microservico.estoque.presentation.openapi.OutputControllerOpenApi;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.OutputSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/saida")
public class OutputController implements OutputControllerOpenApi {

    @Autowired
    private OutputSerivce outputSerivce;

   @Override
    public ResponseEntity<Output> findByCode(@PathVariable Long code) {
        Optional<Output> exitReturned = this.outputSerivce.findByCode(code);
        return exitReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Output> createOutput(@Valid @RequestBody Output output) {
        Output exitCreate = this.outputSerivce.save(output);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(exitCreate.getCodigoSaida()).toUri();
        return ResponseEntity.created(uri).body(exitCreate);
    }

    @Override
    public ResponseEntity<Void> deleteOutput(@PathVariable Long code) {
        this.outputSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("output.removed", String.valueOf(code))).build();
    }

    @Override
    public ResponseEntity<Output> editOutput(@Valid @RequestBody Output output) {
        Output exitReturned = this.outputSerivce.edit(output);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("output.edited.", String.valueOf(exitReturned.getCodigoSaida()))).build();
    }
}
