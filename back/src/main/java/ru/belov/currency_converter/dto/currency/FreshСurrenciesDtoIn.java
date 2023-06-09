package ru.belov.currency_converter.dto.currency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import java.util.List;

@JacksonXmlRootElement(localName = "ValCurs")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class FreshСurrenciesDtoIn {

    @JacksonXmlProperty(localName = "Date", isAttribute = true)
    private String date;

    @JacksonXmlProperty(localName = "Valute")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Currency> currencies;

    @Getter
    @Setter
    @ToString
    public static class Currency {

        @JacksonXmlProperty(localName = "ID", isAttribute = true)
        private String id;

        @JacksonXmlProperty(localName = "NumCode")
        private String numberCode;

        @JacksonXmlProperty(localName = "CharCode")
        private String charCode;

        @JacksonXmlProperty(localName = "Nominal")
        private Integer nominal;

        @JacksonXmlProperty(localName = "Name")
        private String name;

        @JacksonXmlProperty(localName = "Value")
        private String value;
    }
}
