package com.poyrazaktas.bitirme.vat.service;

import com.poyrazaktas.bitirme.vat.service.entityservice.VatValueAddedTaxEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VatValueAddedTaxService {
    private final VatValueAddedTaxEntityService valueAddedTaxEntityService;
}
