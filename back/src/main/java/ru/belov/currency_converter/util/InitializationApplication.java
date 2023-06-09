package ru.belov.currency_converter.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.belov.currency_converter.dto.currency.FreshСurrenciesDtoIn;
import ru.belov.currency_converter.entity.Currency;
import ru.belov.currency_converter.entity.ExchangeRate;
import ru.belov.currency_converter.repository.CurrencyRepository;
import ru.belov.currency_converter.repository.ExchangeRateRepository;
import ru.belov.currency_converter.mapper.CurrencyMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitializationApplication {

    private final RestTemplate restTemplate;

    private final CurrencyRepository currencyRepository;

    private final CurrencyMapper currencyMapper;

    private final ExchangeRateRepository exchangeRateRepository;

    private static final DateTimeFormatter REQUEST_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final DateTimeFormatter RESPONSE_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @EventListener(ApplicationReadyEvent.class)
    public void initial() throws JsonProcessingException {
        log.info("Initial app...");
        FreshСurrenciesDtoIn freshCurrencies = loadFreshCurrencies();

        log.info("Loaded fresh currencies: {}", freshCurrencies);

        log.info("Update currencies");
        saveOrUpdateCurrencies(freshCurrencies.getCurrencies());

        log.info("Update rates");
        calculateAllRates(freshCurrencies);
        log.info("Initial done");
    }


    private FreshСurrenciesDtoIn loadFreshCurrencies() throws JsonProcessingException {
        LocalDate now = LocalDate.now();
        String nowDate = REQUEST_DATE_FORMATTER .format(now);
        String resp = restTemplate.getForObject("http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + nowDate, String.class);

        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.readValue(resp, FreshСurrenciesDtoIn.class);
    }

    private void saveOrUpdateCurrencies(List<FreshСurrenciesDtoIn.Currency> freshCurrencies) {
        Set<Currency> currencies = freshCurrencies
                .stream()
                .map(currencyMapper::toEntity)
                .collect(Collectors.toSet());

        currencyRepository.saveAll(currencies);
    }

    private void calculateAllRates(FreshСurrenciesDtoIn dtoIn) {
        List<ExchangeRate> exchangeRates = new ArrayList<>();

        dtoIn.getCurrencies().forEach(currencyFrom -> {
            dtoIn.getCurrencies().forEach(currencyTo -> {
                // Если целевая валюта != сравниваемой, то рассчитываем
                if (!currencyFrom.getId().equals(currencyTo.getId())) {
                    ExchangeRate exchangeRate = new ExchangeRate();
                    exchangeRate.setCurrencyFrom(currencyMapper.toEntity(currencyFrom));
                    exchangeRate.setCurrencyTo(currencyMapper.toEntity(currencyTo));
                    exchangeRate.setPricePerUnit(
                            calculateCurrencyRateInPenny(currencyTo.getValue(), currencyFrom.getValue()));
                    exchangeRate.setDate(LocalDate.parse(dtoIn.getDate(), RESPONSE_DATE_FORMATTER));

                    exchangeRates.add(exchangeRate);
                }
            });
        });

        exchangeRateRepository.saveAll(exchangeRates);
    }

    private Long calculateCurrencyRateInPenny(String priceTo, String priceFrom) {
        Double to = Double.parseDouble(priceTo.replace(",",".")) / 10000;
        Double from = Double.parseDouble(priceFrom.replace(",",".")) / 10000;
        Double rate = (from / to) * 10000;

        return rate.longValue();
    }
}
