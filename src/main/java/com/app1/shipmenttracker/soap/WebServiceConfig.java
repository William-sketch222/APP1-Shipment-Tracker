package com.app1.shipmenttracker.soap;

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

/**
 * Spring-WS configuration for the B2G customs SOAP service.
 *
 * What this sets up:
 *   1. MessageDispatcherServlet  - handles SOAP requests on /ws/*
 *   2. DefaultWsdl11Definition   - auto-generates WSDL from XSD
 *   3. XsdSchema bean            - loads customs-service.xsd from classpath
 *
 * Access points:
 *   WSDL      : GET  http://localhost:8080/ws/customs.wsdl
 *   SOAP POST : POST http://localhost:8080/ws
 *              Content-Type: text/xml
 *              SOAPAction: ""
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    /**
     * Register the Spring-WS MessageDispatcherServlet.
     * Separate from the main DispatcherServlet; handles only SOAP traffic.
     * transformWsdlLocations=true rewrites WSDL <soap:address> to match request URL.
     */
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
            ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    /**
     * Auto-generate WSDL from the XSD schema.
     * Bean name "customs" => served at /ws/customs.wsdl
     */
    @Bean(name = "customs")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema customsSchema) {
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("CustomsPort");
        wsdl.setLocationUri("/ws");
        wsdl.setTargetNamespace("http://app1.com/customs");
        wsdl.setSchema(customsSchema);
        return wsdl;
    }

    /**
     * Load XSD schema from src/main/resources/xsd/customs-service.xsd
     */
    @Bean
    public XsdSchema customsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/customs-service.xsd"));
    }
}
