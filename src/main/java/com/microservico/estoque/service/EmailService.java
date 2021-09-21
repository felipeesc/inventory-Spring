package com.microservico.estoque.service;

import com.microservico.estoque.domain.EmailModel;
import com.microservico.estoque.domain.dtos.EmailDto;
import com.microservico.estoque.enums.StatusEmail;
import com.microservico.estoque.repository.EmailRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class EmailService implements AbstractService<EmailModel> {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    public EmailModel sendEmail(EmailDto emailDto) {
        EmailModel emailModel = new EmailModel();
        emailModel.setSendDateEmail(LocalDateTime.now());
        BeanUtils.copyProperties(emailDto, emailModel);
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e) {
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(emailModel);
        }
    }

    @Override
    public Optional<EmailModel> findByCode(Long code) {
        return this.emailRepository.findById(code);
    }

    @Override
    public Collection<EmailModel> findAll() {
        return this.emailRepository.findAll();
    }

    @Override
    public EmailModel save(EmailModel dataToSave) {
        return this.emailRepository.save(dataToSave);
    }

    @Override
    public void delete(Long code) {

    }
}
