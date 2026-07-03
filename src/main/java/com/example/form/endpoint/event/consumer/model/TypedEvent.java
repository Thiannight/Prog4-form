package com.example.form.endpoint.event.consumer.model;

import com.example.form.PojaGenerated;
import com.example.form.endpoint.event.model.PojaEvent;

@PojaGenerated
public record TypedEvent(String typeName, PojaEvent payload) {}
