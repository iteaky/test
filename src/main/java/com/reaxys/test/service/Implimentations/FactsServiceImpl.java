package com.reaxys.test.service.Implimentations;

import com.reaxys.test.pojo.Fact;
import com.reaxys.test.pojo.Filter;
import com.reaxys.test.service.interfaces.FactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FactsServiceImpl implements FactService {

    private final static Logger logger = LoggerFactory.getLogger(FactsServiceImpl.class);

    private XmlServiceImpl xmlService;
    private Filter filters;

    @Autowired
    FactsServiceImpl(XmlServiceImpl xmlService, Filter filter) {
        this.xmlService = xmlService;
        this.filters = filter;
    }

    /** Find all facts from XSD scheme which fits additionals given with all additionals
     * @return List of Facts in String format
     * @throws IOException
     * @throws XMLStreamException
     */
    @Override
    public List<String> getFacts() throws IOException, XMLStreamException {
        logger.info("Method getFacts() was called" );
        List<Fact> list = getListOfFacts();
        final List<String> result = collectFacts(list);
        logger.info("Method getFacts() return: " + result);
        return result;
    }

    /** Find all facts with required additionals from XSD scheme which fits given additionals
     * @param params List of required additionals
     * @return List of Facts in String format
     * @throws IOException
     * @throws XMLStreamException
     */
    @Override
    public List<String> getFactsWithAdditional(List<String> params) throws IOException, XMLStreamException {
        logger.info("Method getFactsWithAdditional() was called with params: " + params );
        List<Fact> list = getListOfFacts();
        List<Fact> factsWithAdditionals = filterFactsByAdditional(list, params);
        final List<String> result = collectFacts(factsWithAdditionals);
        logger.info("Method getFactsWithAdditional() return: " + result);
        return result;

    }

    private List<String> collectFacts(List<Fact> list) {
        return list.stream().map(Fact::toString).collect(Collectors.toList());
    }


    private List<Fact> getListOfFacts() throws IOException, XMLStreamException {
        Map<String, String> map = xmlService.parseXmlToMap();
        List<Fact> facts = createFactsFromMap(map);
        return filterFactsByFilter(facts, filters.getFilter());
    }

    private List<Fact> filterFactsByFilter(List<Fact> facts, Map<String, String> filter) {
        return facts.stream()
                .filter(fact -> filter.keySet().stream()
                        .allMatch(key -> filter.get(key).equals(fact.getOptions().get(key))))
                .collect(Collectors.toList());
    }

    private List<Fact> createFactsFromMap(Map<String, String> map) {
        List<Fact> facts = new ArrayList<>();

        map.forEach((key, value) -> {
            Map<String, String> options = parcelEditionsFromStringToMap(value);
            String name = options.get("name");
            options.remove("name");
            facts.add(new Fact(key, name, options));
        });

        return facts;
    }

    private Map<String, String> parcelEditionsFromStringToMap(String string) {
        string = string.trim();
        String[] strings = string.split("\\|");
        Map<String, String> map = new HashMap<>();

        List<String> list = new ArrayList(Arrays.asList(strings));
        String name = list.get(list.size() - 1);
        map.put("name", name);
        list.remove(list.size() - 1);


        for (String s : list) {
            s = s.substring(3);
            String[] split = s.split("=");
            map.put(split[0], split[1]);
        }
        return map;
    }


    private List<Fact> filterFactsByAdditional(List<Fact> facts, List<String> params) {
        facts.forEach(fact -> fact.getOptions().keySet().retainAll(params));
        return facts;


    }
}
