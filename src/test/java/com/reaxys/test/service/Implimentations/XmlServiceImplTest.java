package com.reaxys.test.service.Implimentations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(properties = "classpath:application.properties")
@RunWith(SpringRunner.class)
public class XmlServiceImplTest {

    @Autowired
    private XmlServiceImpl sut;

    private Map<String,String> check;

    @Before
    public void setUp() {
       check = new HashMap<>();
       check.put("1","1");
       check.put("2","2");
    }

    @Test
    public void parseXmlToMap() throws Exception {
        final Map<String, String> map = sut.parseXmlToMap();
        System.out.println();
        Assert.assertTrue(map.equals(check));
    }
}