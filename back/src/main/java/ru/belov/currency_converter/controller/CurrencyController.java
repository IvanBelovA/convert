package ru.belov.currency_converter.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.belov.currency_converter.dto.currency.CurrencyDtoInOut;
import ru.belov.currency_converter.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("api/v1/currencies")
@AllArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @CrossOrigin(origins = "http://127.0.0.1:5173")
    @GetMapping
    public List<CurrencyDtoInOut> getCurrencies() {
        return currencyService.findAll();
    }

}
