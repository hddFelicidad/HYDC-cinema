package com.example.cinema.vo;

import com.example.cinema.po.Movie;
import com.example.cinema.po.RefundStrategy;

import java.util.ArrayList;
import java.util.List;

public class RefundStrategyVO {
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
    private List<MovieVO> movieVOList;

    public RefundStrategyVO(){

    }

    public RefundStrategyVO(RefundStrategy refundStrategy){
        this.id=refundStrategy.getId();
        this.name=refundStrategy.getName();
        this.description=refundStrategy.getDescription();
        this.limitedTime=refundStrategy.getLimitedTime();
        this.bookingCharge=refundStrategy.getBookingCharge();
        List<MovieVO> movieVOList=new ArrayList<>();
        for(Movie movie : refundStrategy.getMovieList()){
            movieVOList.add(new MovieVO(movie));
        }
        this.movieVOList=movieVOList;
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

    public List<MovieVO> getMovieVOList(){return movieVOList;}

    public void setMovieVOList(List movieVOList){this.movieVOList = movieVOList;}

}
