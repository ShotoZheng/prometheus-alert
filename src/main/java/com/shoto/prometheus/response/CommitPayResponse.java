package com.shoto.prometheus.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author admin
 */
@Data
public class CommitPayResponse implements Serializable {

    /**
     * 是否成功
     */
    private Boolean isSuccess;
}
