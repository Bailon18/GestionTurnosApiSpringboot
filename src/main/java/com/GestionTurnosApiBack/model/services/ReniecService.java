package com.GestionTurnosApiBack.model.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.GestionTurnosApiBack.model.entity.Cliente;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;

@Service
public class ReniecService {

    private static final String API_URL = "https://api.apis.net.pe/v2/reniec/dni?numero=";
    private final String token = "apis-token-7716.UdzS1qS8oaDlV5e72lpn-MCa-Z-NmLRF";
    private final RestTemplate restTemplate;

    public ReniecService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Cliente consultarApiReniec(int dni) throws JsonMappingException, JsonProcessingException {
        String url = API_URL + dni;
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            String.class
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(responseEntity.getBody(), Cliente.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
