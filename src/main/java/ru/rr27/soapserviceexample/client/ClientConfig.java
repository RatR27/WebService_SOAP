package ru.rr27.soapserviceexample.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class ClientConfig {

    @Bean
    Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ru.rr27.soapserviceexample.soap");                    //путь к нашим нагенеренным types
        return marshaller;
    }

    @Bean
    public WebServiceTemplate template(){
        WebServiceTemplate template = new WebServiceTemplate();
        template.setMarshaller(marshaller());                                           //получается темплейт научили генерить jaxb -> xml
        template.setUnmarshaller(marshaller());                                         //и обратно из xml -> java object from jaxb
//        template.setDefaultUri("http://localhost:8787/soap/ws");
        return template;
    }
}
