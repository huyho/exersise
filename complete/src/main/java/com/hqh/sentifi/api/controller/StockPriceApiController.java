package com.hqh.sentifi.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/v2")
public class StockPriceApiController {

    public static final Logger logger = LoggerFactory.getLogger(StockPriceApiController.class);

    private static final String DATE_PARTERN = "yyyy-MM-dd";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/{tickerSymbol}/{actionName}", method = RequestMethod.GET)
    public Greeting sayHello(@RequestParam(value = "startDate") @DateTimeFormat(pattern = DATE_PARTERN) Date startDate,
                             @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = DATE_PARTERN) Date endDate,
                             @PathVariable("actionName") String actionName,
                             @PathVariable("tickerSymbol") String tickerSymbol) {
        System.out.println(startDate);
        System.out.println(endDate);
        System.out.println(actionName);
        System.out.println(tickerSymbol);

        final String uri = "http://localhost:8080/springrestexample/employees.xml";

        final RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);

        return new Greeting(counter.incrementAndGet(), "sdsd");
    }
}
