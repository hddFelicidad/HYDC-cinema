package com.example.cinema.vo;

import com.example.cinema.po.Coupon;
import com.example.cinema.po.Ticket;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class OrderVO {
    /**
     * 订单id
     */
    private int id;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 订单生成时间
     */
    private Date time;
    /**
     * 订单状态
     */
    private String state;
    /**
     * 本次订单内购买的票
     */
    private List<Ticket> tickets;
    /**
     * 购买的座位
     */
    private String seats;
    /**
     * 使用的优惠劵
     */
    private Coupon usedCoupon;
    /**
     * 赠送的优惠劵
     */
    private List<Coupon> giftCoupons;
    /**
     * 订单原价
     */
    private double originMoney;
    /**
     * 订单实付价格
     */
    private double realMoney;
    /**
     *场次
     */
    private Date scheduleTime;
    /**
     *电影名称
     */
    private String movieName;
    /**
     *电影海报
     */
    private String posterUrl;

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

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getSeats() {
        return seats;
    }

    public void setUsedCoupon(Coupon usedCoupon) {
        this.usedCoupon = usedCoupon;
    }

    public Coupon getUsedCoupon() {
        return usedCoupon;
    }

    public List<Coupon> getGiftCoupons() {
        return giftCoupons;
    }

    public void setGiftCoupons(List<Coupon> giftCoupons) {
        this.giftCoupons = giftCoupons;
    }

    public void setOriginMoney(double originMoney) {
        this.originMoney = originMoney;
    }

    public double getOriginMoney() {
        return originMoney;
    }

    public void setRealMoney(double realMoney) {
        this.realMoney = realMoney;
    }

    public double getRealMoney() {
        return realMoney;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setScheduleTime(Date scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public Date getScheduleTime() {
        return scheduleTime;
    }


}
