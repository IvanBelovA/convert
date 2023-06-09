package ru.belov.currency_converter.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "project.base-currency")
@Getter
@Setter
@ToString
public class BaseCurrency {

    private String numCode;
    private String charCode;
    private Integer nominal;
    private String name;
    private String value;
}
