package ru.belov.currency_converter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class ExchangeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Users user;

    @ManyToOne
    private Currency rateFrom;

    @ManyToOne
    private Currency rateTo;

    private Long quantity;

    private LocalDate date;

    private Long sum;
}
