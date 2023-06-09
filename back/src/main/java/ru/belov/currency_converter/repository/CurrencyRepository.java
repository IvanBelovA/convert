package ru.belov.currency_converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belov.currency_converter.entity.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
