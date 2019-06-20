package com.example.cinema.po;


public class MovieScheduleTime {
    /**
     * 电影id
     */
    private Integer movieId;
    /**
     * 排片次数
     */
    private Integer time;
    /**
     * 电影名称
     */
    private String name;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
