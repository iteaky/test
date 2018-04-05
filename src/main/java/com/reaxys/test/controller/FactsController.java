package com.reaxys.test.controller;


import com.reaxys.test.service.Implimentations.FactsServiceImpl;
import com.reaxys.test.service.interfaces.XmlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

@RestController
public class FactsController {

    private final static Logger logger = LoggerFactory.getLogger(FactsServiceImpl.class);
    private FactsServiceImpl factsService;

    @Autowired
    public FactsController(FactsServiceImpl factsService) {
        this.factsService = factsService;
    }

    @RequestMapping(value = "/fact", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<String>> getFacts() throws IOException, XMLStreamException {
        logger.info("GET request to /fact");
        return new ResponseEntity<>(factsService.getFacts(), HttpStatus.OK);
    }


    @RequestMapping(value = "/fact", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<List<String>> getFactsWithOptions(@RequestBody List<String> params) throws IOException, XMLStreamException {
        logger.info("POST request to /fact with params: " + params);
        return new ResponseEntity<>(factsService.getFactsWithAdditional(params), HttpStatus.OK);
    }
}
