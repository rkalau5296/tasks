package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail() {
        // Given
        Mail mail = new Mail("test@test.pl", "Test", "test message");

        // When
        simpleEmailService.send(mail);

        // Then
        verify(javaMailSender, times(1)).send(any(MimeMessagePreparator.class));
    }

    @Test
    public void shouldSendEmailWithEmptyCC(){
        // Given
        Mail mail = new Mail("test@test.pl", "Test", "test message");

        // When
        simpleEmailService.send(mail);

        // Then
        verify(javaMailSender, times(1)).send(any(MimeMessagePreparator.class));
    }

    @Test
    public void shouldSendEmailQunatityTask(){
        // Given
        Mail mail = new Mail("test@test.pl", "Test", "test message");

        // When
        simpleEmailService.sendQunatityTask(mail);

        // Then
        verify(javaMailSender, times(1)).send(any(MimeMessagePreparator.class));
    }

    @Test
    public void shouldSendCreateMailMessage(){
        // Given
        Mail mail = new Mail("test@test.pl", "Test", "test message");

        // When
        simpleEmailService.sendCreateMailMessage(mail);

        // Then
        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    public void shouldSendMailMessaggeQunatityTask(){
        // Given
        Mail mail = new Mail("test@test.pl", "Test", "test message");

        // When
        simpleEmailService.sendMailMessaggeQunatityTask(mail);

        // Then
        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
    }
    @Test
    public void shouldSentEmailScheduler(){
        // Given
        Mail mail = new Mail("test@test.pl", "Test", "test message");

        // When
        simpleEmailService.sendMailMessaggeQunatityTask(mail);

        // Then
        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}
