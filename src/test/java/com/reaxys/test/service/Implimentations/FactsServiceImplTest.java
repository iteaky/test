package com.reaxys.test.service.Implimentations;

import com.reaxys.test.pojo.Filter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FactsServiceImplTest {

    @Mock
    Filter filter;
    @Mock
    private XmlServiceImpl xmlService;
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