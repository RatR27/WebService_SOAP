package ru.rr27.soapserviceexample;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    //добавляем кастмоный сервлет для обработку веб-сервиса
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");                   //uri по которому будет срабатывать наш сервлет
    }

    //Описание элементов с которыми будем работать
    @Bean(name = "countries")
    public DefaultWsdl11Definition countriesWsdl11Definition(XsdSchema countriesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();                           //класс описания данного протокола
        wsdl11Definition.setPortTypeName("CountriesPort");                                                  //вот он наш Интерфейс для работы
        wsdl11Definition.setLocationUri("/ws");                                                             //для работы с данным сервисом подвязываем к uri /ws/countries
        wsdl11Definition.setTargetNamespace("http://ru.rr27.soapserviceexample/soap");                      //неймспейс для описания нашего сервиса
        wsdl11Definition.setSchema(countriesSchema);                                                        //указываем на саму схему и заинжектили тут
        return wsdl11Definition;
    }

    //подтягиваем схему
    @Bean
    public XsdSchema countriesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("countries.xsd"));
    }
}