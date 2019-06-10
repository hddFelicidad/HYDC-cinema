package com.example.cinema.po;

import com.example.cinema.vo.RefundStrategyForm;

import java.util.List;

public class RefundStrategy {
    /**
     * 退票策略id
     */
    private Integer id;

    /**
     * 退票策略名称
     */
    private String name;

    /**
     * 退票策略描述
     */
    private  String description;

    /**
     * 退票限制时间
     */
    private Integer limitedTime;

    /**
     *扣除费用
     */
    private double bookingCharge;

    /**
     * 适用电影列表
     */
    private List<Movie> movieList;

    public RefundStrategy(){

    }

    public void setNewRefundStrategy(RefundStrategyForm refundStrategyFrom){
        this.id=refundStrategyFrom.getId();
        this.name=refundStrategyFrom.getName();
        this.description=refundStrategyFrom.getDescription();
        this.limitedTime=refundStrategyFrom.getLimitedTime();
        this.bookingCharge=refundStrategyFrom.getBookingCharge();

    }

    public Integer getId(){return id;}

    public void setId(Integer id){this.id=id;}

    public String getName(){return name;}

    public void setName(String name){this.name=name;}

    public String getDescription(){return description;}

    public void setDescription(String description){this.description=description;}

    public Integer getLimitedTime(){return limitedTime;}

    public void setLimitedTime(Integer limitedTime){this.limitedTime=limitedTime;}

    public double getBookingCharge(){return bookingCharge;}

    public void setBookingCharge(double bookingCharge){this.bookingCharge=bookingCharge;}

    public List<Movie> getMovieList(){return movieList;}

    public void setMovieList(List movieList){this.movieList = movieList;}

}
