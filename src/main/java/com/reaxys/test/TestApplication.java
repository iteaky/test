package com.reaxys.test;

import com.reaxys.test.service.Implimentations.XmlServiceImpl;

import java.io.IOException;
import javax.xml.stream.XMLStreamException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) throws IOException, XMLStreamException {
        SpringApplication.run(TestApplication.class, args);
    }
}
