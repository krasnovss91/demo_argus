package com.example.demo_argus.service.impl;

import com.example.demo_argus.service.ArgusFitoApiService;
import dto.ArgusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;



@Service
public class ArgusFitoApiServiceImpl implements ArgusFitoApiService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseEntity<String> sendDataForSertificate(ArgusDto argusDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("docNumber",argusDto.getDocNumber().toString());
        map.add("date",argusDto.getDate().toString());
        map.add("blancNumber",argusDto.getBlancNumber().toString());
        map.add("inn",argusDto.getInn().toString());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        /*
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
map.add("email", "first.last@example.com");

HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
         */


        return restTemplate.postForEntity("https://argusgate2.fitorf.ru/srv_rec2/ws/rec2?wsdl", request, String.class);

    }

    @Override
    public Object sendDataForConclusion(ArgusDto argusDto) {
        return argusDto.getDocNumber() + argusDto.getInn();
    }
}
