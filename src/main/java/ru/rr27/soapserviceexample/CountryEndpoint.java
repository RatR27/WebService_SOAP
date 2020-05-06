package ru.rr27.soapserviceexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.rr27.soapserviceexample.repositories.CountryRepository;
import ru.rr27.soapserviceexample.soap.GetCountryRequest;
import ru.rr27.soapserviceexample.soap.GetCountryResponse;
import ru.rr27.soapserviceexample.soap.GetCurrencyRequest;
import ru.rr27.soapserviceexample.soap.GetCurrencyResponse;

@Endpoint
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://ru.rr27.soapserviceexample/soap";

    private CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        System.out.println(request.getName());
        response.setCountry(countryRepository.findCountry(request.getName()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCurrencyRequest")
    @ResponsePayload
    public GetCurrencyResponse getCurrencyByCountry(@RequestPayload GetCurrencyRequest request) {
        GetCurrencyResponse response = new GetCurrencyResponse();
        response.setCurrency(countryRepository.findCurrency(request.getName()));
        return response;
    }
}