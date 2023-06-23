package com.shoto.prometheus.controller;

import com.alibaba.fastjson.JSONObject;
import com.shoto.prometheus.annotations.PayMonitorCollect;
import com.shoto.prometheus.request.AgainPayRequest;
import com.shoto.prometheus.request.CommitPayRequest;
import com.shoto.prometheus.response.AgainPayResponse;
import com.shoto.prometheus.response.CommitPayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 */
@Slf4j
@RequestMapping("/api/vi/pay")
@RestController
public class PayController {

    @PayMonitorCollect
    @RequestMapping(value = "/commitPay", method = RequestMethod.POST)
    public CommitPayResponse commitPay(@Validated @RequestBody CommitPayRequest request) {
        CommitPayResponse response = new CommitPayResponse();
        log.info("commitPay request: {}", JSONObject.toJSONString(request));
        response.setIsSuccess(Boolean.TRUE);
        return response;
    }

    @PayMonitorCollect
    @RequestMapping(value = "/againPay", method = RequestMethod.POST)
    public AgainPayResponse againPay(@Validated @RequestBody AgainPayRequest request) {
        AgainPayResponse response = new AgainPayResponse();
        log.info("againPay request: {}", JSONObject.toJSONString(request));
        response.setIsSuccess(Boolean.TRUE);
        return response;
    }
}
