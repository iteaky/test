package com.reaxys.test.service.Implimentations;

import com.reaxys.test.pojo.Filter;
import com.reaxys.test.service.interfaces.XmlService;
import com.sun.org.apache.bcel.internal.generic.FADD;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FactsServiceImplTest {

    @Mock
    private XmlServiceImpl xmlService;

    @Mock
    Filter filter;

    @InjectMocks
    private FactsServiceImpl sut;

    @Before
    public void setUp() throws Exception {
        Map<String, String> filters = new HashMap<>();
        filters.put("display", "true");
        Map<String, String> test = new HashMap<>();
        test.put("IDE.CN", "xf:search=phrase|xf:display=true|Chemical Name");
        test.put("AD.CN", "xf:search=phrase|xf:display=false|Alala Dadada");
        when(xmlService.parseXmlToMap()).thenReturn(test);
        when(filter.getFilter()).thenReturn(filters);
    }

    @Test
    public void getFacts() throws Exception {
        final List<String> facts = sut.getFacts();
        Assert.assertTrue(facts.get(0).contains("IDE.CN"));
        Assert.assertTrue(facts.get(0).contains("search"));
        Assert.assertTrue(facts.get(0).contains("phrase"));
        Assert.assertTrue(facts.get(0).contains("display"));
        Assert.assertTrue(facts.get(0).contains("true"));
        Assert.assertTrue(facts.get(0).contains("Chemical Name"));
        Assert.assertTrue(facts.size() == 1);
    }

    @Test
    public void getFactsWithOneAdditional() throws Exception {
        final List<String> facts = sut.getFactsWithAdditional(Collections.singletonList("search"));
        Assert.assertTrue(facts.get(0).contains("search"));
        Assert.assertFalse(facts.get(0).contains("display"));
    }

    @Test
    public void getFactsWithOutOfAdditional() throws Exception {
        final List<String> facts = sut.getFactsWithAdditional(Collections.singletonList(""));
        Assert.assertFalse(facts.get(0).contains("search"));
        Assert.assertFalse(facts.get(0).contains("display"));
    }

    @Test
    public void getFactsWithSomeAdditional() throws Exception {
        final List<String> facts = sut.getFactsWithAdditional(Arrays.asList("search", "display"));
        Assert.assertTrue(facts.get(0).contains("search"));
        Assert.assertTrue(facts.get(0).contains("display"));
    }
}