package ru.belov.currency_converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Slf4j
public class CurrencyConverterApplication {

    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(CurrencyConverterApplication.class, args);

        RestTemplate restTemplate = new RestTemplate();

//        String resp = restTemplate.getForObject("http://www.cbr.ru/scripts/XML_daily.asp",  String.class);
//
//        XmlMapper xmlMapper = new XmlMapper();
//
//        FreshСurrenciesDtoIn in = xmlMapper.readValue(resp, FreshСurrenciesDtoIn.class);
//
//
//        log.info(resp);
//
//        log.info(in.getDate());
//
//        log.info("Full {}: ", in);
//        log.info("Base cur: {}", baseCurrency.getCharCode());
    }

}
