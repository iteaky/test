package com.reaxys.test.service.Implimentations;

import com.reaxys.test.service.interfaces.XmlService;
import jdk.nashorn.internal.objects.annotations.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service
public class XmlServiceImpl implements XmlService {

    private final static Logger logger = LoggerFactory.getLogger(FactsServiceImpl.class);
    private static final String EMPTY = "";

    @Value("${xsdLocation}")
    private String xsdLocation;

    /** Parse XML to Map where key is Fact shortcut and value is additionals with full name
     * @return Map of values prepared for transform to Facts
     * @throws IOException
     * @throws XMLStreamException
     */
    @Override
    public Map<String, String> parseXmlToMap() throws IOException, XMLStreamException {
        logger.info("Method parseXmlToMap() was called");
        Map<String, String> map = new HashMap<>();
        String val = EMPTY;
        String key = EMPTY;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser = factory.createXMLStreamReader(new FileInputStream(xsdLocation));

        while (parser.hasNext()) {

            int event = parser.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                for (int i = 0; i < parser.getAttributeCount(); i++) {
                    if (parser.getLocalName().equals("element") && parser.getAttributeLocalName(i).equals("name")) {
                        key = parser.getAttributeValue(i);
                    }
                }
            }

            if (event == XMLStreamConstants.CHARACTERS) {
                if (!parser.isWhiteSpace()) {
                    val = parser.getText();
                    if (map.get(key) != null) {
                        val = map.get(key) + val;
                    }
                    map.put(key, val);
                }
            }
        }
        logger.info("Method parseXmlToMap()return: " + map);

        return map;
    }
}
