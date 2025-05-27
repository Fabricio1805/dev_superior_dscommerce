package com.devsuperior.dscommerce.application.dto;

import java.time.Instant;

import com.devsuperior.dscommerce.domain.entity.Payment;

public class PaymentDTO {
    private Long id;

    private Instant moment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public PaymentDTO(Long id, Instant moment) {
        this.id = id;
        this.moment = moment;
    }

    public PaymentDTO(Payment payment) {
        this.id = payment.getId();
        this.moment = payment.getMoment();
    }
}
