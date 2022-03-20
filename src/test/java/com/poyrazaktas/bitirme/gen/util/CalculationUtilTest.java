package com.poyrazaktas.bitirme.gen.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculationUtilTest {

    @Test
    void shouldCalculatePriceWithTax_whenRawPriceIsNotNullAndVatRateIsNotNull() {
        BigDecimal priceRaw = BigDecimal.valueOf(100);
        int vatRate = 10;
        BigDecimal expected = BigDecimal.valueOf(110);
        BigDecimal actual = CalculationUtil.calculatePriceWithTax(vatRate, priceRaw);

        assertThat(expected.equals(actual));
    }

    @Test
    void shouldCalculatePriceWithTax_whenVatRateIsZero() {
        BigDecimal priceRaw = BigDecimal.valueOf(100);
        int vatRate = 0;
        BigDecimal expected = BigDecimal.valueOf(100);
        BigDecimal actual = CalculationUtil.calculatePriceWithTax(vatRate, priceRaw);

        assertThat(expected.equals(actual));
    }

    @Test
    void shouldThrowNullPointerException_whenRawPriceIsNullAndVatRateIsNotNull() {
        BigDecimal priceRaw = null;
        int vatRate = 10;

        assertThrows(NullPointerException.class,
                () -> CalculationUtil.calculatePriceWithTax(vatRate, priceRaw));
    }

    @Test
    void shouldThrowNullPointerException_whenRawPriceIsNotNullAndVatRateIsNull() {
        BigDecimal priceRaw = BigDecimal.valueOf(100);
        Integer vatRate = null;

        assertThrows(NullPointerException.class,
                () -> CalculationUtil.calculatePriceWithTax(vatRate, priceRaw));
    }

}