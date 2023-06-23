package com.shoto.prometheus.controller;

import com.shoto.prometheus.couters.CounterMonitorUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author admin
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController {

    @RequestMapping(value = "/countEventReport", method = RequestMethod.GET)
    public String countEventReport() {
        CounterMonitorUtil.countEvent("trade_pay_event_all", "event", "commitPay", "isSuccess", "1");
        return "SUCCESS";
    }
}
