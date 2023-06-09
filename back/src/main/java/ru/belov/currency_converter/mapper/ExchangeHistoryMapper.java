package ru.belov.currency_converter.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.belov.currency_converter.dto.exchange.ExchangeHistoryDtoOut;
import ru.belov.currency_converter.entity.ExchangeHistory;

@Component
@AllArgsConstructor
public class ExchangeHistoryMapper {

    private final CurrencyMapper currencyMapper;

    public ExchangeHistoryDtoOut toDtoOut(ExchangeHistory exchangeHistory) {
        if(exchangeHistory == null) {
            return null;
        }
        ExchangeHistoryDtoOut dtoOut = new ExchangeHistoryDtoOut();
        dtoOut.setId(exchangeHistory.getId());
        dtoOut.setUserId(exchangeHistory.getUser().getId());
        dtoOut.setRateFrom(currencyMapper.entityToDto(exchangeHistory.getRateFrom()));
        dtoOut.setRateTo(currencyMapper.entityToDto(exchangeHistory.getRateTo()));
        dtoOut.setQuantity(exchangeHistory.getQuantity());
        dtoOut.setDate(exchangeHistory.getDate());
        dtoOut.setSum(exchangeHistory.getSum());

        return dtoOut;
    }
}
