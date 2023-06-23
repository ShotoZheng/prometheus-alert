package com.shoto.prometheus.annotations;

import java.lang.annotation.*;

/**
 * @author admin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Documented
public @interface PayMonitorCollect {
}
