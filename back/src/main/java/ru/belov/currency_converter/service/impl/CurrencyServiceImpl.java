package ru.belov.currency_converter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.belov.currency_converter.dto.currency.CurrencyDtoInOut;
import ru.belov.currency_converter.mapper.CurrencyMapper;
import ru.belov.currency_converter.repository.CurrencyRepository;
import ru.belov.currency_converter.service.CurrencyService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    private final CurrencyMapper currencyMapper;

    @Override
    public List<CurrencyDtoInOut> findAll() {
        return currencyRepository.findAll()
                .stream()
                .map(currencyMapper :: entityToDto)
                .collect(Collectors.toList());
    }
}
