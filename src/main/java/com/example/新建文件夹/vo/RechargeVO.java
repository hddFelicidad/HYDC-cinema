package com.example.cinema.vo;

import java.sql.Timestamp;
import java.util.Date;

public class RechargeVO {
    /**
     * id
     */
    private int id;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 交易类型
     */
    public static final String tradeType="充值";
    /**
     * 交易时间
     */
    private Timestamp tradeTime;
    /**
     * 充值金额
     */
    private double rechargeMoney;
    /**
     * 赠送金额
     */
    private double giftMoney;
    /**
     * 会员卡余额
     */
    private double balance;

    public String getTradeType(){
        return tradeType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setTradeTime(Timestamp tradeTime) {
        this.tradeTime = tradeTime;
    }

    public Timestamp getTradeTime() {
        return tradeTime;
    }

    public void setGiftMoney(double giftMoney) {
        this.giftMoney = giftMoney;
    }

    public double getGiftMoney() {
        return giftMoney;
    }

    public void setRechargeMoney(double rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public double getRechargeMoney() {
        return rechargeMoney;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
}
