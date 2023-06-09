package ru.belov.currency_converter.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.belov.currency_converter.dto.exchange.ExchangeHistoryDtoOut;
import ru.belov.currency_converter.entity.ExchangeHistory;
import ru.belov.currency_converter.entity.ExchangeRate;
import ru.belov.currency_converter.entity.Users;
import ru.belov.currency_converter.mapper.ExchangeHistoryMapper;
import ru.belov.currency_converter.repository.CurrencyRepository;
import ru.belov.currency_converter.repository.ExchangeHistoryRepository;
import ru.belov.currency_converter.repository.ExchangeRateRepository;
import ru.belov.currency_converter.repository.UserRepository;
import ru.belov.currency_converter.service.ExchangeService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangeRateRepository exchangeRateRepository;

    private final CurrencyRepository currencyRepository;

    private final UserRepository userRepository;

    private final ExchangeHistoryRepository exchangeHistoryRepository;

    private final ExchangeHistoryMapper historyMapper;

    @Override
    public String change(String from, String to, Long quantity) {
        LocalDate now = getRateDate();
        log.info("Change from: {}, to: {}, quantity: {}, date: {}", from, to, quantity, now);
        ExchangeRate exchangeRate = exchangeRateRepository.findExchangeRateByDateAndCurrencyFromAndCurrencyTo(
                now,
                currencyRepository.getReferenceById(from),
                currencyRepository.getReferenceById(to)
        );

        Long sum = calculateSum(
                exchangeRate.getPricePerUnit(),
                quantity,
                exchangeRate.getCurrencyTo().getNominal(),
                exchangeRate.getCurrencyFrom().getNominal());

        saveHistory(exchangeRate, quantity, sum);

        return sum.toString();
    }

    @Override
    public List<ExchangeHistoryDtoOut> getHistory() {
        Users user = userRepository.current().get();

        return exchangeHistoryRepository.findByUser(user)
                .stream()
                .map(historyMapper :: toDtoOut)
                .collect(Collectors.toList());
    }

    private void saveHistory(ExchangeRate exchangeRate, Long quantity, Long sum) {
        ExchangeHistory exchangeHistory = new ExchangeHistory();
        exchangeHistory.setUser(userRepository.current().get());
        exchangeHistory.setQuantity(quantity);
        exchangeHistory.setSum(sum);
        exchangeHistory.setRateFrom(exchangeRate.getCurrencyFrom());
        exchangeHistory.setDate(LocalDate.now());
        exchangeHistory.setRateTo(exchangeRate.getCurrencyTo());

        exchangeHistoryRepository.save(exchangeHistory);
    }

    private LocalDate getRateDate() {
        LocalDate now = LocalDate.now();
        DayOfWeek dayOfWeek = now.getDayOfWeek();

        if (dayOfWeek.name().equals("SUNDAY")) {
            now = now.minusDays(1);
        } else if (dayOfWeek.name().equals("MONDAY")) {
            now = now.minusDays(2);
        }

        return now;
    }

    private Long calculateSum(Long pricePerUnit, Long quantity, Integer nominalTo, Integer nominalFrom) {
        Double generalNominal = (double) (nominalTo / nominalFrom);
        return (long) (pricePerUnit * quantity * generalNominal);
    }

}
