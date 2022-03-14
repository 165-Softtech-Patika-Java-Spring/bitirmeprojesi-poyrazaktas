package com.poyrazaktas.bitirme.prd.service;

import com.poyrazaktas.bitirme.prd.service.entityservice.PrdProductEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrdProductService {
    private final PrdProductEntityService productEntityService;
}
