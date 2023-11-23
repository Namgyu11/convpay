package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayCancelRequest;
import com.zerobase.convpay.dto.PayCancelResponse;
import com.zerobase.convpay.type.ConvenienceType;
import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.type.PayCancelResult;
import com.zerobase.convpay.type.PayMethodType;
import com.zerobase.convpay.type.PayResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConveniencePayServiceTest {
    ConveniencePayService conveniencePayService = new ConveniencePayService();
    /*
    * given 어떤 데이터가 있을 때
    * when 어떤 동작을 하게 되면
    * then 어떤 결과가 나와야 된다.
    * */
    @Test
    void pay_success () {
        //given
        PayRequest payRequest = new PayRequest(PayMethodType.MONEY,ConvenienceType.G25, 50);
        //when
        PayResponse payResponse = conveniencePayService.pay(payRequest);
        //then
        assertEquals(PayResult.SUCCESS, payResponse.getPayResult());
        assertEquals(35, payResponse.getPaidAmount());
    }
    @Test
    void pay_fail () {
        //given
        PayRequest payRequest = new PayRequest(PayMethodType.MONEY,ConvenienceType.G25, 1500_001);
        //when
        PayResponse payResponse = conveniencePayService.pay(payRequest);
        //then
        assertEquals(PayResult.FAIL, payResponse.getPayResult());
        assertEquals(0, payResponse.getPaidAmount());
    }

    @Test
    void pay_cancel_success () {
        //given
        PayCancelRequest payCancelRequest = new PayCancelRequest(ConvenienceType.G25, 1000, PayMethodType.MONEY);
        //when
        PayCancelResponse payCancelResponse = conveniencePayService.payCancel(payCancelRequest);
        //then
        assertEquals(PayCancelResult.PAY_CANCEL_SUCCESS, payCancelResponse.getPayCancelResult());
        assertEquals(1000, payCancelRequest.getPayCancelAmount());
    }
    @Test
    void pay_cancel_fail () {
        //given
        PayCancelRequest payCancelRequest = new PayCancelRequest(ConvenienceType.G25, 99, PayMethodType.MONEY);
        //when
        PayCancelResponse payCancelResponse = conveniencePayService.payCancel(payCancelRequest);
        //then
        assertEquals(PayCancelResult.PAY_CANCEL_FAIL, payCancelResponse.getPayCancelResult());
        assertEquals(99, payCancelRequest.getPayCancelAmount());
    }



}