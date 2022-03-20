package com.sombuoy.lopputyo.services;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

@Service
public class Json                                                           //Apuluokka, jonka nappasin youtube-videolta. Käsittelee JSON-muotoista tietoa.
{
    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper()
    {   
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        // configuration code here
        return defaultObjectMapper;
    }

    public static JsonNode parse(String src) throws IOException
    {
        return objectMapper.readTree(src);
    }
}
