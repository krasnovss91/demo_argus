package com.example.demo_argus.service;

import dto.ArgusDto;

public interface ArgusFitoApiService {
    Object sendDataForSertificate(ArgusDto argusDto);

    Object sendDataForConclusion(ArgusDto argusDto);
}
