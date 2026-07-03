package com.example.form.endpoint.rest.controller;

import com.example.form.endpoint.event.EventProducer;
import com.example.form.endpoint.event.model.SendEmail;
import com.example.form.entity.dto.RegisterRequest;
import com.example.form.service.register.RegistrationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {
  private final EventProducer<SendEmail> eventProducer;
  private final RegistrationService registrationService;

  @PostMapping("/register")
  @SneakyThrows
  public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
    var event = SendEmail.builder().to(registrationService.register(registerRequest)).build();
    eventProducer.accept(List.of(event));
    return ResponseEntity.ok().build();
  }
}
