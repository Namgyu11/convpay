package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.type.ConvenienceType;
import com.zerobase.convpay.type.PayMethodType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountByMethodTest {
    DiscountByMethod discountByMethod = new DiscountByMethod();

    @Test
    void discountSuccess() {
        //given
        PayRequest payRequestM
                = new PayRequest(PayMethodType.MONEY, ConvenienceType.G25, 1000);
        PayRequest payRequestC
                = new PayRequest(PayMethodType.CARD, ConvenienceType.G25, 1000);
        //when
        Integer discountedAmountM = discountByMethod.getDiscountedAmount(payRequestM);
        Integer discountedAmountC = discountByMethod.getDiscountedAmount(payRequestC);
        //then
        assertEquals(700, discountedAmountM);
        assertEquals(1000, discountedAmountC);

    }

}