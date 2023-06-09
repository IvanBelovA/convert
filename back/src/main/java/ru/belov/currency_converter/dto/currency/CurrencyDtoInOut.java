package ru.belov.currency_converter.dto.currency;

import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class CurrencyDtoInOut {

    private String id;

    private String name;

    private String numberCode;

    private String charCode;

    private Integer nominal;
}
