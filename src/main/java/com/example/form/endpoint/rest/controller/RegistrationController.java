package com.example.form.endpoint.rest.controller;

import com.example.form.endpoint.event.EventProducer;
import com.example.form.endpoint.event.model.SendEmail;
import com.example.form.entity.dto.RegisterRequest;
import com.example.form.service.register.RegistrationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RegistrationController {
  private final EventProducer<SendEmail> eventProducer;
  private final RegistrationService registrationService;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
    try {
      var event = SendEmail.builder().to(registrationService.register(registerRequest)).build();
      eventProducer.accept(List.of(event));
      return ResponseEntity.ok().build();
    } catch (RuntimeException e) {
      log.error(e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }
}
