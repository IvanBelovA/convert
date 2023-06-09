package ru.belov.currency_converter.dto.exchange;

import lombok.Getter;
import lombok.Setter;
import ru.belov.currency_converter.dto.currency.CurrencyDtoInOut;

import java.time.LocalDate;

@Getter
@Setter
public class ExchangeHistoryDtoOut {

    private Long id;

    private String userId;

    private CurrencyDtoInOut rateFrom;

    private CurrencyDtoInOut rateTo;

    private Long quantity;

    private LocalDate date;

    private Long sum;
}
