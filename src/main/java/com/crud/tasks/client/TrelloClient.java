package com.crud.tasks.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TrelloClient {
    @Autowired
    private RestTemplate restTemplate;
}
