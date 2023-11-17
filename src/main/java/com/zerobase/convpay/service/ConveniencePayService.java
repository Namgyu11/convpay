package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.dto.PayResult;

public class ConveniencePayService {
    // payRequest 를 받아서 payResponse 던진다.

    /**
     * 결제
     * @param payRequest
     * @return
     */
    public PayResponse pay(PayRequest payRequest){

        return new PayResponse(PayResult.SUCCESS, 100);
    }

    /**
     * 결제 취소
     */
    public void payCancel(){

    }
}
