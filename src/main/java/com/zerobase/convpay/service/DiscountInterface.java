package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayRequest;

public interface DiscountInterface {
    //할인된 금액을 받을 수 있게
    Integer getDiscountedAmount(PayRequest payRequest);
}
