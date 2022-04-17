package com.example.demo_argus.service;

import dto.ArgusDto;

public interface ArgusFitoApiService {
    Object sendDataForCertificate(ArgusDto argusDto);

    Object sendDataForConclusion(ArgusDto argusDto);
}
