package com.poyrazaktas.bitirme.vat.service.entityservice;

import com.poyrazaktas.bitirme.vat.dao.VatValueAddedTaxDao;
import com.poyrazaktas.bitirme.vat.entity.VatValueAddedTax;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VatValueAddedTaxEntityServiceTest {

    @Mock
    private VatValueAddedTaxDao valueAddedTaxDao;

    @InjectMocks
    private VatValueAddedTaxEntityService valueAddedTaxEntityService;

    @Test
    void whenGetVatValueAddedTaxByProductType_calledWithVatList_thenItShouldReturnVatList() {
        Long id = 2L;

        VatValueAddedTax valueAddedTax = mock(VatValueAddedTax.class);
        when(valueAddedTax.getId()).thenReturn(id);

        when(valueAddedTaxDao.getVatValueAddedTaxByProductType(any())).thenReturn(valueAddedTax);

        VatValueAddedTax result = valueAddedTaxEntityService.getVatValueAddedTaxByProductType(any());

        assertEquals(id, result.getId());

    }

    @Test
    void whenGetVatValueAddedTaxByProductType_calledWithNull_thenItShould() {

        when(valueAddedTaxDao.getVatValueAddedTaxByProductType(null)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> valueAddedTaxEntityService.getVatValueAddedTaxByProductType(null));

    }
}