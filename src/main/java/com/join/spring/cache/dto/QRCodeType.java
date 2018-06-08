package com.join.spring.cache.dto;

/**
 * @description: 二维码类型
 * @author: chi
 * @Date: 11:01 2018/1/30/030
 */
public enum QRCodeType {
    BRANCH, //门店
    CONTRACT,  //合同
    PAYMENT,
    PROCUREMENT;  //收款费用

    public static int getExpireSeconds(QRCodeType qrCodeType){
        switch (qrCodeType){
            case BRANCH: return 1296000;
            case CONTRACT: return 60 * 5;
            case PAYMENT: return 20;
            case PROCUREMENT: return 60 * 5;
        }
        return -1;
    }

}
