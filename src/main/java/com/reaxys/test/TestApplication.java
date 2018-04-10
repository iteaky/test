package com.reaxys.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) throws IOException, XMLStreamException {
        SpringApplication.run(TestApplication.class, args);
    }
}
