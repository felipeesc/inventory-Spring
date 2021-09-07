package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.Transport;
import com.microservico.estoque.presentation.openapi.TransportControllerOpenApi;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.TransportSerivce;
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
@RequestMapping("/transporte")
public class TransportController implements TransportControllerOpenApi {

    @Autowired
    private TransportSerivce transportSerivce;

    @Override
    public ResponseEntity<Transport> findByCode(@PathVariable Long code) {
        Optional<Transport> transporReturned = this.transportSerivce.findByCode(code);
        return transporReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Transport> createTransport(@Valid @RequestBody Transport transport) {
        Transport transprtSave = this.transportSerivce.save(transport);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(transprtSave.getCodigoTransportadora()).toUri();
        return ResponseEntity.created(uri).body(transprtSave);
    }

    @Override
    public ResponseEntity<Void> deleteTransport(@PathVariable Long code) {
        this.transportSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("transport.removed", String.valueOf(code))).build();
    }

    @Override
    public ResponseEntity<Transport> editTransport(@Valid @RequestBody Transport transport) {
        Transport transportReturned = this.transportSerivce.edit(transport);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("transport.edited", String.valueOf(transportReturned.getCodigoTransportadora()))).build();
    }
}
