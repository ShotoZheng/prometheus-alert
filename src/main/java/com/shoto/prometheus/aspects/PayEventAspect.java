package com.shoto.prometheus.aspects;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shoto.prometheus.config.PayMetricsProperties;
import com.shoto.prometheus.couters.CounterMonitorUtil;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Optional;

/**
 * @author admin
 */
@Slf4j
@Component
@Aspect
public class PayEventAspect {

    @Resource
    private PayMetricsProperties payMetricsProperties;

    @Pointcut("@annotation(com.shoto.prometheus.annotations.PayMonitorCollect)")
    public void collectCut() {
    }

    @Around("collectCut()")
    public Object invokeAround(ProceedingJoinPoint jp) throws Throwable {
        Object result = null;
        boolean isSuccess = false;
        try {
            result = jp.proceed();
            isSuccess = obtainPayResult(result);
        } finally {
            dealCollect(isSuccess ? CounterMonitorUtil.SUCCESS : CounterMonitorUtil.FAIL, jp, result);
        }
        return result;
    }

    private void dealCollect(String isSuccess, ProceedingJoinPoint jp, Object result) {
        String methodName = Strings.EMPTY;
        try {
            MethodSignature signature = (MethodSignature) jp.getSignature();
            methodName = signature.getName();
            // 上报支付 pv 事件
            String metricsName = payMetricsProperties.getPayEventPv();
            CounterMonitorUtil.countEvent(metricsName, "event", methodName, "isSuccess", isSuccess,
                    "payMethod", obtainPayMethod(jp));
        } catch (Exception ex) {
            log.error("dealCollect failed, method {}, exec result {}", methodName, JSONObject.toJSONString(result), ex);
        }
    }

    private boolean obtainPayResult(Object result) {
        try {
            if (Objects.isNull(result)) {
                return false;
            }
            JSONObject payRequestJb = JSONObject.parseObject(JSON.toJSONString(result));
            return Optional.ofNullable(payRequestJb)
                    .map(e -> e.getBoolean("isSuccess")).filter(Boolean.TRUE::equals).isPresent();
        } catch (Exception ex) {
            log.error("PayEventAspect obtainPayResult exec failed", ex);
        }
        return false;
    }

    private String obtainPayMethod(ProceedingJoinPoint jp) {
        try {
            Object[] args = jp.getArgs();
            if (args == null || args.length == 0) {
                return "";
            }
            JSONObject payRequestJb = JSONObject.parseObject(JSON.toJSONString(args[0]));
            Optional<String> optional = Optional.ofNullable(payRequestJb)
                    .map(e -> e.getString("payMethod")).filter(StringUtils::isNotEmpty);
            return optional.orElse("");
        } catch (Exception ex) {
            log.error("PayEventAspect obtainPayMethod exec failed", ex);
        }
        return "";
    }
}
