package ru.belov.currency_converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.belov.currency_converter.entity.Currency;
import ru.belov.currency_converter.entity.ExchangeRate;

import java.time.LocalDate;
import java.util.Date;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    ExchangeRate findExchangeRateByDateAndCurrencyFromAndCurrencyTo(
            LocalDate date, Currency currencyFrom, Currency currencyTo);
}
