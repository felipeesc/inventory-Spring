package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.Transport;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.TransportSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.Cacheable;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/transporte")
public class TransportController {

    @Autowired
    private TransportSerivce transportSerivce;

    @GetMapping("/{code}")
    @Cacheable("transport")
    public ResponseEntity<Transport> findByCode(@PathVariable Long code) {
        Optional<Transport> transporReturned = this.transportSerivce.findByCode(code);
        return transporReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Transport> createTransport(@Valid @RequestBody Transport transport) {
        Transport transprtSave = this.transportSerivce.save(transport);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(transprtSave.getCodigoTransportadora()).toUri();
        return ResponseEntity.created(uri).body(transprtSave);
    }

    @PostMapping("/{code}")
    public ResponseEntity<Void> deleteTransport(@PathVariable Long code) {
        this.transportSerivce.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("transport.removed", String.valueOf(code))).build();
    }

    @PutMapping("/{code}")
    public ResponseEntity<Transport> editTransport(@Valid @RequestBody Transport transport) {
        Transport transportReturned = this.transportSerivce.edit(transport);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("transport.edited", String.valueOf(transportReturned.getCodigoTransportadora()))).build();
    }
}
