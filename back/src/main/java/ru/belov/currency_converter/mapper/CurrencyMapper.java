package ru.belov.currency_converter.mapper;

import org.springframework.stereotype.Component;
import ru.belov.currency_converter.dto.currency.FreshСurrenciesDtoIn;
import ru.belov.currency_converter.dto.currency.CurrencyDtoInOut;
import ru.belov.currency_converter.entity.Currency;

@Component
public class CurrencyMapper {

//    private Currency currency = new Currency();

    public Currency toEntity(FreshСurrenciesDtoIn.Currency dtoIn) {
        if (dtoIn == null) {
            return null;
        }
        Currency currency = new Currency();
        currency.setId(dtoIn.getId());
        currency.setName(dtoIn.getName());
        currency.setCharCode(dtoIn.getCharCode());
        currency.setNominal(dtoIn.getNominal());
        currency.setNumberCode(dtoIn.getNumberCode());

        return currency;
    }

    public Currency dtoToEntity(CurrencyDtoInOut dtoInOut) {
        if (dtoInOut == null) {
            return null;
        }
        Currency currency = new Currency();
        currency.setId(dtoInOut.getId());
        currency.setName(dtoInOut.getName());
        currency.setCharCode(dtoInOut.getCharCode());
        currency.setNominal(dtoInOut.getNominal());
        currency.setNumberCode(dtoInOut.getNumberCode());

        return currency;
    }

    public CurrencyDtoInOut entityToDto(Currency currency) {
        if (currency == null) {
            return null;
        }
        CurrencyDtoInOut dtoInOut = new CurrencyDtoInOut();
        dtoInOut.setId(currency.getId());
        dtoInOut.setName(currency.getName());
        dtoInOut.setCharCode(currency.getCharCode());
        dtoInOut.setNominal(currency.getNominal());
        dtoInOut.setNumberCode(currency.getNumberCode());

        return dtoInOut;
    }
}
