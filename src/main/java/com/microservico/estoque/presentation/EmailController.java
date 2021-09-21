package com.microservico.estoque.presentation;

import com.microservico.estoque.domain.EmailModel;
import com.microservico.estoque.domain.dtos.EmailDto;
import com.microservico.estoque.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/sending-email")
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDto emailDto) {
        EmailModel emailModel = emailService.sendEmail(emailDto);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }

}
