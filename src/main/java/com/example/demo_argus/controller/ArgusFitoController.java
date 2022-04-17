package com.example.demo_argus.controller;

import com.example.demo_argus.service.impl.ArgusFitoApiServiceImpl;
import dto.ArgusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArgusFitoController {
    @Autowired
    ArgusFitoApiServiceImpl argusFitoApiService;

    @PostMapping("api/certificate")
    @ResponseBody
    public Object getCertificate(@RequestBody ArgusDto argusDto){//здесь получаем фитосанитарный сертификат

        ResponseEntity<String> entity =  argusFitoApiService.sendDataForCertificate(argusDto);
       // System.out.println(entity.getBody());
        return entity.getBody();
    }

    @PostMapping("api/conclusion")
    @ResponseBody
    public Object getConclusion(@RequestBody ArgusDto argusDto){//здесь получаем заключение

        ResponseEntity<String> entity =  argusFitoApiService.sendDataForConclusion(argusDto);
       // System.out.println(entity.getBody());
        return entity.getBody();
    }

}
