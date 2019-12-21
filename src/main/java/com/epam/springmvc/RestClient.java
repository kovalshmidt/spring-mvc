package com.epam.springmvc;

import com.epam.springmvc.converter.PdfHttpMessageConverter;
import com.epam.springmvc.utility.PdfUtility;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Objects;

public class RestClient {

    public static void main(String[] args) {
        getUserJSON();
        getUsersJSON();
        getUserPDF();
        getUsersPDF();
    }

    private static void getUserJSON() {

        RestTemplate restTemplate = new RestTemplate();

        final String URI = "http://localhost:8085/rest/user/31";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(URI, HttpMethod.GET, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                //Write the retrieved json
                Files.write(Paths.get("user.json"), Objects.requireNonNull(response.getBody()).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void getUsersJSON() {

        RestTemplate restTemplate = new RestTemplate();

        final String URI = "http://localhost:8085/rest/users";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(URI, HttpMethod.GET, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                //Write the retrieved json
                Files.write(Paths.get("users.json"), Objects.requireNonNull(response.getBody()).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void getUserPDF() {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new PdfHttpMessageConverter(new PdfUtility()));

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_PDF));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        final String URI = "http://localhost:8085/rest/user/31";
        ResponseEntity<byte[]> response = restTemplate.exchange(URI, HttpMethod.GET, entity, byte[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                //Write the retrieved pdf
                Files.write(Paths.get("user.pdf"), Objects.requireNonNull(response.getBody()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void getUsersPDF() {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new PdfHttpMessageConverter(new PdfUtility()));

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_PDF));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        final String URI = "http://localhost:8085/rest/users";
        ResponseEntity<byte[]> response = restTemplate.exchange(URI, HttpMethod.GET, entity, byte[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                //Write the retrieved pdf
                Files.write(Paths.get("users.pdf"), Objects.requireNonNull(response.getBody()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
