package com.poyrazaktas.bitirme.gen.util;

import java.math.BigDecimal;

public class CalculationUtil {

    public static BigDecimal calculatePriceWithTax(int vatRate, BigDecimal priceRaw) {
        return priceRaw.add(priceRaw.multiply(BigDecimal.valueOf(vatRate).divide(BigDecimal.valueOf(100))));
    }
}
