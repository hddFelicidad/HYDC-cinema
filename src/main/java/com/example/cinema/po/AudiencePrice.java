package com.example.cinema.po;

public class AudiencePrice {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 票价总额
     */
    private Double totalPrice;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
