package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.User;
import com.microservico.estoque.presentation.util.HeaderUtil;
import com.microservico.estoque.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/manager/users")
public class UserResource {

    private UserService userService;

    @GetMapping
    public Collection<User> findAll() {
        return this.userService.findAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<User> findByCode(@PathVariable Long code) {
        Optional<User> userReturned = this.userService.findByCode(code);
        return userReturned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid User user) {
        User userSaved = this.userService.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(userSaved.getCode()).toUri();
        return ResponseEntity.created(uri).body(userSaved);
    }

    @PostMapping("/{code}")
    public ResponseEntity<Void> delete(@PathVariable Long code) {
        this.userService.delete(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("user.removed", String.valueOf(code))).build();
    }
}
