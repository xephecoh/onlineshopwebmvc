package com.khamutov.web;


import com.khamutov.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class RequestBodyParser {
    public RequestBodyParser() {
    }

    final String[] requestValues = new String[5];

    public RequestBodyParser parseRequest(String requestBody) {
        int counter = 0;
        String[] fieldValue = requestBody.split("&");
        for (String el : fieldValue) {
            String[] split = el.split("=");
            String value = split[1];
            requestValues[counter] = value;
            counter++;
        }
        return this;
    }

    public Product getProduct() {
         return Product
                .builder()
                .name(requestValues[0])
                .price(Integer.parseInt(requestValues[1]))
                .id(Integer.parseInt(requestValues[2]))
                .build();
    }
}
