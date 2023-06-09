package ru.belov.currency_converter.service;

import org.springframework.stereotype.Component;
import ru.belov.currency_converter.dto.exchange.ExchangeHistoryDtoOut;

import java.util.List;

@Component
public interface ExchangeService {

    String change(String from, String to, Long quantity);

    List<ExchangeHistoryDtoOut> getHistory();
}
