package com.example.cinema.vo;

public class RefundForm {
    /**
     * 原价
     */
    private double originalPrice;

    /**
     * 收取的服务费
     */
    private double servicePrice;

    /**
     * 退款金额
     */
    private double refundPrice;

    public RefundForm(){
    }

    public double getOriginalPrice(){return originalPrice;}
    public void setOriginalPrice(double originalPrice){
        this.originalPrice=originalPrice;
    }

    public double getServicePrice(){return servicePrice;}
    public void setServicePrice(double servicePrice){
        this.servicePrice=servicePrice;
    }

    public double getRefundPrice(){return refundPrice;}
    public void setRefundPrice(double refundPrice){
        this.refundPrice=refundPrice;
    }
}
