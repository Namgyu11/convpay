package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.*;
import com.zerobase.convpay.type.*;

public class ConveniencePayService { // 편의점 결제 서비스
    // payRequest 를 받아서 payResponse 던진다.
    private final MoneyAdapter moneyAdapter = new MoneyAdapter();
    private final CardAdapter cardAdapter = new CardAdapter();

    //결제

    public PayResponse pay(PayRequest payRequest) {
        PaymentInterface paymentInterface;
        if(payRequest.getPayMethodType() == PayMethodType.CARD){
            paymentInterface = cardAdapter;
        }else {
            paymentInterface =moneyAdapter;
        }
        PaymentResult payment = paymentInterface.payment(payRequest.getPayAmount());
        // fail fast
        // 단 하나의 성공 케이스를 마지막에 처리 Only one
        if (payment == PaymentResult.PAYMENT_FAIL) {
            return new PayResponse(PayResult.FAIL, 0);
        }
        // SUCCESS CASE
        return new PayResponse(PayResult.SUCCESS, payRequest.getPayAmount());


    }

    public PayCancelResponse payCancel(PayCancelRequest payCancelRequest) {
        PaymentInterface paymentInterface;

        if(payCancelRequest.getPayMethodType() == PayMethodType.CARD){
            paymentInterface = cardAdapter;
        }else {
            paymentInterface =moneyAdapter;
        }

        CancelPaymentResult cancelPaymentResult
                = paymentInterface.cancelPayment(payCancelRequest.getPayCancelAmount());

        if (cancelPaymentResult == CancelPaymentResult.CANCEL_PAYMENT_FAIL) {
            return new PayCancelResponse(PayCancelResult.PAY_CANCEL_FAIL, 0);
        }
        return new PayCancelResponse(PayCancelResult.PAY_CANCEL_SUCCESS,
                payCancelRequest.getPayCancelAmount());
    }
}
