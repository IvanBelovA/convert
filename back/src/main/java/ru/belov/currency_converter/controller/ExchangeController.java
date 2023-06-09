package ru.belov.currency_converter.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.belov.currency_converter.dto.exchange.ExchangeHistoryDtoOut;
import ru.belov.currency_converter.service.ExchangeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/exchanges")
@AllArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    @CrossOrigin(origins = "http://127.0.0.1:5173")
    @GetMapping()
    public String getExchange(
            @RequestParam String currencyFrom,
            @RequestParam String currencyTo,
            @RequestParam Long quantity) {
        return exchangeService.change(currencyFrom, currencyTo, quantity);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5173")
    @GetMapping("history")
    public List<ExchangeHistoryDtoOut> getHistories() {
        return exchangeService.getHistory();
    }

}
