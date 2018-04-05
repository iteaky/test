package com.reaxys.test.service.interfaces;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.Map;

public interface XmlService {
    Map<String, String> parseXmlToMap() throws IOException, XMLStreamException;
}
