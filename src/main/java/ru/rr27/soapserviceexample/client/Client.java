package ru.rr27.soapserviceexample.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import ru.rr27.soapserviceexample.soap.Country;
import ru.rr27.soapserviceexample.soap.GetCountryRequest;
import ru.rr27.soapserviceexample.soap.GetCountryResponse;

@Component
public class Client {

    private WebServiceTemplate template;

    private String URI = "http://localhost:8787/soap/ws";

    @Autowired
    public void setTemplate(WebServiceTemplate template) {
        this.template = template;
    }

    public Country getCountry(String name){
        GetCountryRequest request = new GetCountryRequest();
        request.setName(name);

        //При создании WSTemplate мы сразу указали адрес по дефолту
//        GetCountryResponse response = (GetCountryResponse)template.marshalSendAndReceive(request);

        //У WSTemplate не настроен основной Uri, тогда пишем адресс запроса в ручную
        GetCountryResponse response = (GetCountryResponse)template.marshalSendAndReceive(URI, request);

        return response.getCountry();
    }
}
