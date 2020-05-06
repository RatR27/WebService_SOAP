package ru.rr27.soapserviceexample.repositories;

import org.springframework.stereotype.Component;
import ru.rr27.soapserviceexample.soap.Country;
import ru.rr27.soapserviceexample.soap.Currency;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class CountryRepository {
    private static final List<Country> countries = new ArrayList<Country>();

    @PostConstruct
    public void initData() {
        Country spain = new Country();
        spain.setName("Spain");
        spain.setCapital("Madrid");
        spain.setCurrency(Currency.EUR);
        spain.setPopulation(46704314);

        countries.add(spain);

        Country poland = new Country();
        poland.setName("Poland");
        poland.setCapital("Warsaw");
        poland.setCurrency(Currency.PLN);
        poland.setPopulation(38186860);

        countries.add(poland);

        Country uk = new Country();
        uk.setName("United Kingdom");
        uk.setCapital("London");
        uk.setCurrency(Currency.GBP);
        uk.setPopulation(63705000);

        countries.add(uk);
    }

    public Country findCountry(String name) {
        Country result = null;
        for (Country country : countries) {
            if (name.equals(country.getName())) {
                result = country;
            }
        }
        return result;
    }

    public Currency findCurrency(String name) {
        Currency currency = null;
        for (Country country : countries) {
            if (name.equals(country.getName())) {
                currency = country.getCurrency();
            }
        }
        return currency;
    }
}