package com.reaxys.test.service.interfaces;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public interface FactService {

    List<String> getFacts() throws IOException, XMLStreamException;

    List<String> getFactsWithAdditional(List<String> filter) throws IOException, XMLStreamException;

}
