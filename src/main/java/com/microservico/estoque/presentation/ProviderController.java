package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.Provider;
import com.microservico.estoque.presentation.openapi.ProviderControllerOpenApi;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.ProviderSerivce;
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
@RequestMapping("/fornecedor")
public class ProviderController implements ProviderControllerOpenApi {

    @Autowired
    private ProviderSerivce providerSerivce;

    @Override
    public ResponseEntity<Provider> findByCode(@PathVariable Long code) {
        Optional<Provider> cityReturned = this.providerSerivce.findByCode(code);
        return cityReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Provider> createProvider(@Valid @RequestBody Provider provider) {
        Provider save = this.providerSerivce.save(provider);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(save.getCodigoFornecedor()).toUri();
        return ResponseEntity.created(uri).body(save);
    }

    @Override
    public ResponseEntity<Void> deleteProvider(@PathVariable Long code) {
        this.providerSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("provider.removed", String.valueOf(code))).build();
    }

    @Override
    public ResponseEntity<Provider> editProvider(@Valid @RequestBody Provider provider) {
        Provider providerReturned = this.providerSerivce.edit(provider);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("provider.edited", String.valueOf(providerReturned.getCodigoFornecedor()))).build();
    }
}
