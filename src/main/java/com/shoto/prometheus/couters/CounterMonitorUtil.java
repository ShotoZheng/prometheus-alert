package com.shoto.prometheus.couters;

import com.alibaba.fastjson.JSONObject;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.extern.slf4j.Slf4j;

/**
 * @author admin
 */
@Slf4j
public class CounterMonitorUtil {

    public static final String FAIL = "0";
    public static final String SUCCESS = "1";

    public static void countEvent(String name, String... tags) {
        try {
            Counter counter = Metrics.counter(name, tags);
            counter.increment();
            log.info("countEvent:{}", JSONObject.toJSONString(counter));
        } catch (Exception e) {
            log.error("CounterMonitor countEvent failed:{}", e.getMessage());
        }
    }
}
