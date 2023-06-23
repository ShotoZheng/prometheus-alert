package com.shoto.prometheus.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author admin
 */
@Data
public class AgainPayRequest implements Serializable {

    /**
     * 支付方式
     */
    private String payMethod;
    /**
     * 订单号
     */
    private String orderNo;
}
