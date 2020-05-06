package ru.rr27.soapserviceexample.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rr27.soapserviceexample.soap.Country;

@RestController
@RequestMapping("/client")
public class ClientController {

    private Client client;


    @Autowired
    public void setClient(Client client) {
        this.client = client;
    }

    @GetMapping("/country/{countryName}")
    public Country getCountry(@PathVariable String countryName){

       return client.getCountry(countryName);

    }
}
