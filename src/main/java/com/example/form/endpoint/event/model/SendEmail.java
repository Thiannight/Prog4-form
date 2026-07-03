package com.example.form.endpoint.event.model;

import lombok.*;

import java.time.Duration;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendEmail extends PojaEvent {
    private String to;

    @Override
    public Duration maxConsumerDuration() {
        return Duration.ofSeconds(10);
    }

    @Override
    public Duration maxConsumerBackoffBetweenRetries() {
        return Duration.ofSeconds(30);
    }
}
