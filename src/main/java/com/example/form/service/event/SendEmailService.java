package com.example.form.service.event;

import com.example.form.endpoint.event.model.SendEmail;
import com.example.form.mail.Email;
import com.example.form.mail.Mailer;
import jakarta.mail.internet.InternetAddress;
import java.util.List;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SendEmailService implements Consumer<SendEmail> {
  private final Mailer mailer;

  @Override
  @SneakyThrows
  public void accept(SendEmail sendEmail) {
    InternetAddress userAddress = new InternetAddress(sendEmail.getTo());
    mailer.accept(
        new Email(
            userAddress,
            List.of(),
            List.of(),
            "Your registration has been confirmed",
            "",
            List.of()));
  }

  @Override
  public Consumer<SendEmail> andThen(Consumer<? super SendEmail> after) {
    return Consumer.super.andThen(after);
  }
}
