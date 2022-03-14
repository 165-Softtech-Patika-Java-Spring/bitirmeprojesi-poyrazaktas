package com.poyrazaktas.bitirme.typ.service;

import com.poyrazaktas.bitirme.typ.service.entityservice.TypProductTypeEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TypProductTypeService {
    private final TypProductTypeEntityService productTypeEntityService;
}