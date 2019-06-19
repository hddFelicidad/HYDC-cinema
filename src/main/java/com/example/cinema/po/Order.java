package com.example.cinema.po;

import com.example.cinema.vo.OrderVO;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Order {
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
    private int state;
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

    /**
     *vip卡id（若为VIP卡购买）
     */
    private Integer VIPCardId;


    public OrderVO getVO() {
        OrderVO vo = new OrderVO();
        vo.setId(this.id);
        vo.setTime(this.time);
        String stateString;
        switch (state) {
            case 0:
                stateString = "待支付";
                break;
            case 1:
                stateString = "已完成";
                break;
            case 2:
                stateString = "已失效";
                break;
            case 3:
                stateString = "退款成功";
                break;
            default:
                stateString = "未完成";
        }
        vo.setState(stateString);
        vo.setTickets(this.tickets);
        vo.setUsedCoupon(this.usedCoupon);
        vo.setGiftCoupons(giftCoupons);
        vo.setOriginMoney(this.originMoney);
        vo.setRealMoney(this.realMoney);
        vo.setSeats(this.seats);
        vo.setMovieName(this.movieName);
        vo.setScheduleTime(this.scheduleTime);
        vo.setUserId(this.userId);
        vo.setPosterUrl(this.posterUrl);
        return vo;
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

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
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

    public void setVIPCardId(Integer vipCardId){ this.VIPCardId=vipCardId; }

    public int getVIPCardId(){ return VIPCardId; }
}
