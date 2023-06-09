package ru.belov.currency_converter.service;

import org.springframework.stereotype.Component;
import ru.belov.currency_converter.dto.currency.CurrencyDtoInOut;

import java.util.List;

@Component
public interface CurrencyService {

    List<CurrencyDtoInOut> findAll();
}
