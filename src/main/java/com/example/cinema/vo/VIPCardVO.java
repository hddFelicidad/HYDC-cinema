package com.example.cinema.vo;


import java.sql.Timestamp;

public class VIPCardVO {

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
     * 用户id
     */
    private int userId;

    /**
     * 会员卡id
     */
    private int id;

    /**
     * 会员卡余额
     */
    private double balance;

    /**
     * 办卡日期
     */
    private Timestamp joinDate;

    /**
     * 总消费金额
     */
    private double totalAmount;

    /**
     * 最后一次订单消费时间
     */
    private Timestamp lastOrderDate;


    public VIPCardVO() {

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Timestamp getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
    }

    public void setTotalAmount(double totalAmount){
        this.totalAmount=totalAmount;
    }

    public double getTotalAmount(){
        return totalAmount;
    }

    public void setLastOrderDate(Timestamp timestamp){
        this.lastOrderDate=lastOrderDate;
    }

    public Timestamp getLastOrderDate(){
        return lastOrderDate;
    }

    public double calculate(double amount) {
        return (int)(amount/targetMoney)*giftMoney+amount;

    }
}
