package com.zerobase.convpay;

import com.zerobase.convpay.config.ApplicationConfig;
import com.zerobase.convpay.dto.PayCancelRequest;
import com.zerobase.convpay.dto.PayCancelResponse;
import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.service.ConveniencePayService;
import com.zerobase.convpay.type.ConvenienceType;
import com.zerobase.convpay.type.PayCancelResult;
import com.zerobase.convpay.type.PayMethodType;

public class UserClient {
    public static void main(String[] args) {
        // !현재 클래스!사용자 -> 편결이 -> 머니
        ApplicationConfig applicationConfig = new ApplicationConfig();
        ConveniencePayService conveniencePayService =
                applicationConfig.conveniencePayServiceDiscountPayMethod();

        //결제 G25, 1000원
        PayRequest payRequest = new PayRequest(PayMethodType.CARD, ConvenienceType.G25, 50);
        PayResponse payResponse = conveniencePayService.pay(payRequest);
        System.out.println(payResponse);
        //취소 G25, 500원
        PayCancelRequest payCancelRequest
                = new PayCancelRequest(ConvenienceType.G25, 500, PayMethodType.MONEY);
        PayCancelResponse payCancelResponse = conveniencePayService.payCancel(payCancelRequest);
        System.out.println(payCancelResponse);
    }
}
