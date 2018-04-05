package com.reaxys.test.pojo;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class Filter {

    @Resource(name = "additionsToFilter")
    private Map<String, String> filter;

    public Map<String, String> getFilter() {
        return filter;
    }
}
