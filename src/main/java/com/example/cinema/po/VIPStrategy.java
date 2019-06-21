package com.example.cinema.po;

import com.example.cinema.vo.VIPStrategyVO;

public class VIPStrategy {
    /**
     * VIP策略id
     */
    private int id;
    /**
     * 名字
     */
    private String name;
    /**
     * 目标金额
     */
    private double targetMoney;
    /**
     * 赠送金额
     */
    private double giftMoney;
    /**
     * 购买该vip卡所需金额
     */
    private double price;
    /**
     * 策略状态，0：上架状态，1：下架状态
     */
    private Integer state;

    public VIPStrategyVO toVO(){
        VIPStrategyVO vo=new VIPStrategyVO();
        vo.setId(this.id);
        vo.setName(this.name);
        vo.setPrice(this.price);
        vo.setGiftMoney(this.giftMoney);
        vo.setTargetMoney(this.targetMoney);
        vo.setState(this.state);
        return vo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(double targetMoney) {
        this.targetMoney = targetMoney;
    }

    public double getGiftMoney() {
        return giftMoney;
    }

    public void setGiftMoney(double giftMoney) {
        this.giftMoney = giftMoney;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
