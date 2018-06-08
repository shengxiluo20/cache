package com.join.spring.cache.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chi  2018-06-05 17:52
 **/
@Data
@AllArgsConstructor
public class QRCode implements Serializable {
    private String naturalKey;
    private Integer url;
    private QRCodeType qrCodeType = QRCodeType.BRANCH;
}
